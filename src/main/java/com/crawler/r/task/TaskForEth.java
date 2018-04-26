package com.crawler.r.task;

import com.crawler.r.c.EtherIframe;
import com.crawler.r.c.EtherScan;
import com.crawler.r.entity.TargetToken;
import com.crawler.r.entity.TokenTransfers;
import com.crawler.r.service.TargetTokenService;
import com.crawler.r.service.TokenTransfersService;
import com.crawler.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class TaskForEth {

    @Autowired
    TargetTokenService tokenService;
    @Autowired
    TokenTransfersService transfersService;

    /**
     * 每分钟抓取一次 'token' 页面的数据
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void startCrawler() {
        List<TargetToken> list = tokenService.findAllList();
        if (list.size() > 0) {
            for (TargetToken tt : list) {
                String token = tt.getTargetToken();
                String urls = Constants.URL + token;
                System.out.println("start====>1");
                //抓去主token的总数量
                Spider.create(new EtherScan()).addUrl(new String[]{urls}).addPipeline(new Pipeline() {
                    @Override
                    public void process(ResultItems resultItems, Task task) {
                        String all = resultItems.get("all");
                    }
                }).thread(2).run();
                // 抓取 交易列表数据  判断当前数据库的最近交易时间  是否等于当前页时间，！= 就需要开启抓取下一页，（循环）
                String i_url = Constants.URL + "generic-tokentxns2?contractAddress=" + token + "&a=&mode=";
                Spider.create(new EtherIframe()).addUrl(new String[]{i_url}).addPipeline(new Pipeline() {
                    @Override
                    public void process(ResultItems resultItems, Task task) {
                        List<TokenTransfers> transfers = (List<TokenTransfers>) resultItems.get("transfers");
                        transfersService.save(transfers);
                    }
                }).thread(2).run();
            }
        }
    }

}
