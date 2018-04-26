package com.crawler.r.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by hcj on 2018/4/27.
 */
@Component
public class TaskForUser {

    // 主要抓取用户地址的信息  持有量，交易记录大小  判断 该地址是否 平交易所台或者个人地址

    /**
     * 每分钟抓取一次 'token' 页面的数据
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void startCrawler() {

    }

}
