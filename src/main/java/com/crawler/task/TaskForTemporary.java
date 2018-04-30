package com.crawler.task;

import com.crawler.r.entity.*;
import com.crawler.r.service.*;
import com.crawler.task.c.EtherIframe;
import com.crawler.task.c.EtherScan;
import com.crawler.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取要处理合约 token交易信息到临时表  ，更新合约的总量
 */
@Component
public class TaskForTemporary {

    @Autowired
    TargetTokenService tokenService;
    @Autowired
    TemporaryDataService dataService;

    /**
     * 每分钟抓取一次 '交易列表的数据到临时表
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void startCrawlerTable() {
        List<TargetToken> tokens = tokenService.findAllList();
        for (TargetToken target : tokens) {
            // 抓取 交易列表数据 只需要最新的数据 进行存储，要做时间判断
            String url = Constants.URL_2 + target.getTargetToken() + "&a=&mode=";
            Spider.create(new EtherIframe()).addUrl(new String[]{url}).addPipeline(new Pipeline() {
                @Override
                public void process(ResultItems resultItems, Task task) {
                    // 50条数据
                    List<TemporaryData> temporaryDatas = (List<TemporaryData>) resultItems.get("transfers");
                    List<TemporaryData> temp = new ArrayList<>();
                    for (int i = 0; i < temporaryDatas.size(); i++) {
                        //加入交易记录所属合约地址、id
                        temporaryDatas.get(i).setTokenId(target.getId());
                        temporaryDatas.get(i).setToken(target.getTargetToken());
                        temp.add(temporaryDatas.get(i));
                    }
                    dataService.saveList(temp);
                }
            }).thread(2).start();
        }
    }


    /**
     * 每天抓取更新一次合约的总量
     */
    @Scheduled(cron = "0 0/9 * * * ?")
    public void startCrawler() throws IOException {
        List<TargetToken> list = tokenService.findAllList();
        //更新 总量
        for (TargetToken token : list) {
            TargetToken tt = tokenService.findById(token.getId());
            Spider.create(new EtherScan()).addUrl(new String[]{Constants.URL_1 + tt.getTargetToken()}).addPipeline(new Pipeline() {
                @Override
                public void process(ResultItems resultItems, Task task) {
                    String all = resultItems.get("all");
                    String html = resultItems.get("html");
                    tt.setAllToken(all);
                    tt.setHtml(html);
                    tokenService.updateTar(tt);
                }
            }).thread(1).run();
        }
    }

}
