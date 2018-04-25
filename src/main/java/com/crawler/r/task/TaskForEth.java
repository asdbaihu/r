package com.crawler.r.task;

import com.crawler.r.c.EtherScan;
import com.crawler.r.entity.TargetToken;
import com.crawler.r.service.TargetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.List;

@Component
public class TaskForEth {

    private static final String  url="https://etherscan.io/token/";

    @Autowired
    TargetTokenService tokenService;

    /**
     * 每分钟抓取一次 'token' 页面的数据
     *
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void startCrawler(){
        List<TargetToken> list = tokenService.findAllList();
        if (list.size()>0){
            for (TargetToken tt:list){
                String token=tt.getTargetToken();
                String urls=url+token;
                //抓去主token的页面数据
                Spider.create(new EtherScan()).addUrl(new String[]{urls}).thread(1).run();
            }
        }



    }

}
