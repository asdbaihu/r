package com.crawler.task.c;

import com.crawler.config.rabbitmq.RabbitMqSender;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import javax.annotation.Resource;
import java.util.List;

public class EtherScan  implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(2000);

    @Override
    public void process(Page page) {
        //*[@id="ContentPlaceHolder1_divSummary"]/div[1]/table/tbody/tr[1]/td[2]/text()[1]
        String all= page.getHtml().xpath("//*[@id=\"ContentPlaceHolder1_divSummary\"]/div[1]/table/tbody/tr[1]/td[2]/text(0)").toString();
        String html= page.getHtml().xpath("//*[@id=\"ContentPlaceHolder1_divSummary\"]/div[1]/table/tbody/tr[1]/td[2]/html()").toString();
        page.putField("all",all);
        page.putField("html",html);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
