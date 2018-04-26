package com.crawler.r.task;

import com.crawler.r.c.EtherIframe;
import com.crawler.r.c.EtherScan;
import com.crawler.r.entity.TargetToken;
import com.crawler.r.service.TargetTokenService;
import com.crawler.r.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.List;

@Component
public class TaskForEth {


//     @Scheduled(cron = "0/10 * * * * ?")

    @Autowired
    TargetTokenService tokenService;

    /**
     * 每分钟抓取一次 'token' 页面的数据
     *
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void startCrawler(){

//        https://etherscan.io/token/generic-tokentxns2?contractAddress=0x23352036e911a22cfc692b5e2e196692658aded9&a=&mode=
        List<TargetToken> list = tokenService.findAllList();
        if (list.size()>0){
            for (TargetToken tt:list){
                String token=tt.getTargetToken();
                String urls= Constants.URL+token;
                System.out.println("start====>1");
                //抓去主token的页面数据
                Spider.create(new EtherScan()).addUrl(new String[]{urls}).thread(2).run();
                System.out.println("start====>3");
                String i_url=Constants.URL+"generic-tokentxns2?contractAddress="+token+"&a=&mode=";

                Spider.create( new EtherIframe()).addUrl(new String[]{i_url}).thread(2).run();
                System.out.println("start====>5");
            }
        }

    }

}
