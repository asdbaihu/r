package com.crawler.r.c;

import com.crawler.config.rabbitmq.RabbitMqSender;
import com.crawler.config.rabbitmq.util.RabbitMqEnum;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;

public class EtherScan  implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Resource
    private RabbitMqSender rabbitMqSender;

    @Override
    public void process(Page page) {

        String all= page.getHtml().xpath("/html/body/div[1]/div[4]/div[1]/div[1]/table/tbody/tr[1]/td[2]/text()").toString();
        System.out.println("总量：=====>" +all);
        System.out.println("start====>2");
    }

    @Override
    public Site getSite() {
        return site;
    }
}
