package com.crawler.task;

import com.crawler.r.entity.*;
import com.crawler.r.service.*;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 临时数据表的数据判断用户类型 、并插入交易表，删除已完成的数据
 * Created by hcj on 2018/4/27.
 */
@Component
public class TaskForTransfers {

    private static Log log= LogFactory.getLog(TaskForTransfers.class);

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
    public void startCrawlerForUser() throws ParseException {

        //获取所有临时数据
        List<TemporaryData> list = dataService.findList();

        if (list != null && list.size() > 0) {
            //保存的目标
            List<TokenTransfers> transferses=new ArrayList<>();
            TokenTransfers tt = null;

            for (TemporaryData td : list) {
                // 做一层校验 ，避免因为执行时间较长第二次查处的集合里的数据已被删除
                TemporaryData data=dataService.findById(td.getId());
                //查询是最近时间的一条数据
                Date date = Constants.sdf.parse(td.getTransfersDate());
                String d=Constants.sdf_1.format(date);
                TokenTransfers transfers=transfersService.getByIdAndDate(data.getTokenId(),d,td.getFromToken(),td.getToToken());
                if (data!=null){
                    if (transfers==null){
                        tt = new TokenTransfers();
                        tt.setToken(td.getToken());
                        tt.setTokenId(td.getTokenId());
                        tt.setTransfersDate(date);
                        tt.setFromToken(td.getFromToken());
                        tt.setToToken(td.getToToken());
                        tt.setQuantity(td.getQuantity());
                        tt.setDel("0");
                        tt.setFlag("0");
                        tt.setCreateDate(new Date());
                        transfersService.save(tt);
                    }
                }
            }
            //批量删除
            dataService.deleteList(list);
        }

    }

}
