package com.crawler.task;

import com.crawler.r.entity.*;
import com.crawler.r.service.*;
import com.crawler.task.c.TokenHolders;
import com.crawler.task.c.TokenType;
import com.crawler.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 临时数据表的数据判断用户类型 、并插入交易表，删除已完成的数据
 * Created by hcj on 2018/4/27.
 */
@Component
public class TaskForUser {

    private static Log log= LogFactory.getLog(TaskForUser.class);

    @Autowired
    TokenTransfersService transfersService;
    @Autowired
    TokenUserService tokenUserService;
    @Autowired
    TemporaryDataService dataService;

    /**
     * 每分钟抓取一次 'token' 页面的数据
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void startCrawlerForUser() {

        //获取所有临时数据
        List<TemporaryData> list = dataService.findList();

        if (list != null && list.size() > 0) {
            //保存的目标
            List<TokenTransfers> transferses=new ArrayList<>();
            TokenTransfers tt = null;

            List<TemporaryData> tData=new ArrayList<>();

            for (TemporaryData td : list) {
                // 做一层校验 ，避免因为执行时间较长第二次查处的集合里的数据已被删除
                TemporaryData data=dataService.findById(td.getId());
                //查询是最近时间的一条数据
                TokenTransfers transfers=transfersService.getByIdLimit(data.getTokenId());
                if (data!=null){
                    if (transfers!=null){
                        //校验交易时间是否已存在
                        if (transfers.getTransfersDate().getTime()<data.getTransfersDate().getTime()){
                            tData.add(td);
                        }
                    }else {
                        tData.add(td);
                    }
                }
            }
            for (TemporaryData td:tData){
                tt = new TokenTransfers();
                tt.setToken(td.getToken());
                tt.setTokenId(td.getTokenId());
                tt.setTransfersDate(td.getTransfersDate());
                tt.setFromToken(td.getFromToken());
                tt.setToToken(td.getToToken());
                tt.setQuantity(td.getQuantity());
                tt.setDel("0");
                tt.setFlag("0");
                tt.setCreateDate(new Date());
                transferses.add(tt);
            }
            //批量插入
            transfersService.saveList(transferses);
            //批量删除
            dataService.deleteList(list);
            //判断用户数据是否存在 ，不存在就去抓取下个页面判断用户的类型  个人 or 交易所
            for (TokenTransfers tts : transferses) {
                try{
                    // form的token
                    TokenUser tokenUser = tokenUserService.findByAddress(tts.getFromToken());
                    if (tokenUser == null) {
                        findType(tts.getFromToken());
                    }
                    //to的token
                    TokenUser tokenUser1 = tokenUserService.findByAddress(tts.getToToken());
                    if (tokenUser1 == null) {
                        findType(tts.getToToken());
                    }
                }catch (Exception e){
                    log.info("处理用户类型时出现异常，不做处理继续执行。。。。");
                }
            }
        }

    }

    /**
     * 获取token 的类型 个人或者交易所
     */
    private void findType(String token) {
        String i_url = Constants.URL_3 + token;
        Spider.create(new TokenType())
                .addUrl(new String[]{i_url})
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(ResultItems resultItems, Task task) {
                        String res = resultItems.get("transactions");
                        String tnxs = res.trim().replace(" txns", "").replace(" txn", "");
                        TokenUser tokenUser = new TokenUser();
                        tokenUser.setAddress(token);
                        tokenUser.setTxns(tnxs);
                        if (Integer.parseInt(tnxs) > 100000) {
                            tokenUser.setTokenType("1");
                        } else {
                            tokenUser.setTokenType("0");
                        }
                        tokenUser.setCreateDate(new Date());
                        tokenUserService.save(tokenUser);
                    }
                }).thread(1).run();
    }


}
