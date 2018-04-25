package com.crawler.r.task;


import com.crawler.r.utils.CnblogsSpider;
import com.crawler.r.entity.TargetToken;
import com.crawler.r.service.TargetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TaskForBlog {


    @Autowired
    TargetTokenService tokenService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void starrt(){

        List<TargetToken> list = tokenService.findAllList();
        System.out.println(list.size());

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date())+"  爬取："+Thread.currentThread().getName());
        Spider.create(new CnblogsSpider()).addUrl(new String[]{"https://www.cnblogs.com/"}).thread(5).run();
    }

}
