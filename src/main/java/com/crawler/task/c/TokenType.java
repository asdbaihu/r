package com.crawler.task.c;

import com.crawler.utils.Constants;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 *  判断用户类型的 爬去方法
 * Created by hcj on 2018/4/28.
 */
public class TokenType implements PageProcessor {

    private Site site = Site.me()
            .setDomain(Constants.URL_S)
            .setSleepTime(1000)
            .setRetryTimes(30)
            .setCharset("utf-8")
            .setTimeOut(30000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        //交易量
        String transactions=page.getHtml().xpath("//*[@id=\"ContentPlaceHolder1_divSummary\"]/div[1]/table/tbody/tr[3]/td[2]/span/text()").toString();
        page.putField("transactions",transactions);

    }

    @Override
    public Site getSite() {
        return site;
    }

}
