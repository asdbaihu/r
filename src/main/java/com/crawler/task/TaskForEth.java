package com.crawler.task;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.crawler.r.entity.TokenUser;
import com.crawler.r.entity.UserHolders;
import com.crawler.r.service.TokenUserService;
import com.crawler.r.service.UserHoldersService;
import com.crawler.task.c.EtherIframe;
import com.crawler.task.c.EtherScan;
import com.crawler.r.entity.TargetToken;
import com.crawler.r.entity.TokenTransfers;
import com.crawler.r.service.TargetTokenService;
import com.crawler.r.service.TokenTransfersService;
import com.crawler.task.c.TokenHolders;
import com.crawler.task.c.TokenType;
import com.crawler.utils.Constants;
import com.crawler.utils.IpGet;
import com.crawler.utils.httpclient.HttpClientUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 更新用户持有的该地址的总量
 */
//@Component
public class TaskForEth {

    private static Log log = LogFactory.getLog(TaskForEth.class);

    @Autowired
    TokenTransfersService transfersService;
    @Autowired
    TokenUserService tokenUserService;
    @Autowired
    UserHoldersService holdersService;


    /**
     * 每分钟处理一次交易信息的数据判断用户的持有量
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void startCrawlerTable() {

        List<TokenTransfers> transferses = transfersService.findByFlag();
        if (transferses != null && transferses.size() > 0) {
            for (TokenTransfers tt : transferses) {
                try {
                    //更新 from地址 持有剩余
                    balance(tt.getTokenId(), tt.getToken(), tt.getFromToken());
                    //更新 to地址 持有剩余
                    balance(tt.getTokenId(), tt.getToken(), tt.getToToken());
                    // 更新已经处理过的数据标记（flag） 为 1
                    tt.setFlag("1");
                    transfersService.update(tt);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("更新持有量出错。。。。");
                }
            }
        }
    }

    /**
     * 用户持有量
     *
     * @param tt  合约地址
     * @param utt 持有者地址
     */
    private void balance(Long id, String tt, String utt) {
        String url = Constants.URL_1 + tt + "?a=" + utt;
        Spider.create(new TokenHolders())
                .addUrl(new String[]{url})
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(ResultItems resultItems, Task task) {
                        String balance = resultItems.get("balance");
                        // 根据合约表的主键和用户token地址查找 用户对当前合约的持有量数据
                        UserHolders userHolders = holdersService.findByTargetIdAndUAddress(id, utt);
                        if (userHolders == null) {
                            UserHolders holders = new UserHolders();
                            holders.setTargetId(id);
                            holders.setBalance(balance);
                            holders.settAddress(tt);
                            holders.setuAddress(utt);
                            holders.setCreateDate(new Date());
                            holders.setFlag("0");
                            holdersService.save(holders);
                        } else {
                            userHolders.setBalance(balance);
                            holdersService.save(userHolders);
                        }
                    }
                }).thread(1).run();
    }


    /**
     * 处理 用户类型
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void startCrawlerUsers() {
        // 获取待处理的用户持有信息
        List<UserHolders> list = holdersService.selectByFlag();
        if (list.size() > 0) {
            //判断用户数据是否存在 ，不存在就去抓取下个页面判断用户的类型  个人 or 交易所
            for (UserHolders holders : list) {
                try {
                    // 校验用户信息是否存在
                    TokenUser tokenUser = tokenUserService.findByAddress(holders.getuAddress());
                    if (tokenUser == null) {
                        findType(holders.getuAddress());
                    }
                    holders.setFlag("1");
                    holdersService.save(holders);
                } catch (Exception e) {
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
