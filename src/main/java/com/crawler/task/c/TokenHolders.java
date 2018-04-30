package com.crawler.task.c;

import com.crawler.utils.Constants;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.Date;

/**
 *  判断用户类型的方法
 * Created by hcj on 2018/4/27.
 */
public class TokenHolders implements PageProcessor {

    private Site site = Site.me()
            .setDomain(Constants.URL_S)
            .setSleepTime(1000)
            .setRetryTimes(30)
            .setCharset("utf-8")
            .setTimeOut(30000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {

        String balance=page.getHtml().xpath("//*[@id=\"ContentPlaceHolder1_divSummary\"]/div[1]/table/tbody/tr[3]/td[2]/text()").toString();
        page.putField("balance",balance);

    }

    @Override
    public Site getSite() {
        return site;
    }


}
