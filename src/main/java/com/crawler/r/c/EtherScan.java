package com.crawler.r.c;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class EtherScan  implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.putField("test", page.getHtml().xpath("//*[@id=\"ContentPlaceHolder1_mainboxes\"]/div[1]/div/h4/font/font/text()").toString());
        String a=page.getResultItems().get("test");
        System.out.println(a);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
