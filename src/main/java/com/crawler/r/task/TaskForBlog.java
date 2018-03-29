package com.crawler.r.task;


import com.crawler.r.c.CnblogsSpider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskForBlog {


    @Scheduled(cron = "0/10 * * * * ?")
    public void starrt(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date())+"  爬取："+Thread.currentThread().getName());
        Spider.create(new CnblogsSpider()).addUrl(new String[]{"https://www.cnblogs.com/"}).thread(5).run();
    }

}
