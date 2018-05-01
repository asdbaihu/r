package com.crawler.task.c;

import com.crawler.r.entity.TemporaryData;
import com.crawler.r.entity.TokenTransfers;
import com.crawler.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hcj on 2018/4/26.
 */
@Service
public class EtherIframe implements PageProcessor {

    private static Log log = LogFactory.getLog(EtherIframe.class);

    private Site site = Site.me()
            .setDomain(Constants.URL_S)
            .setSleepTime(1000)
            .setRetryTimes(30)
            .setCharset("utf-8")
            .setTimeOut(30000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    public void process(Page page) {
        // pageSize  总页面大小
//        int pageSize=Integer.parseInt(page.getHtml().xpath("//*[@id=\"Div1\"]/span/b[2]/text()").toString());
//        page.putField("pageSize",pageSize);

        List<TemporaryData> list = new ArrayList<>();
        TemporaryData transfers = null;
        int i = 2;
        boolean next=true;
        while (next) {

            transfers = new TemporaryData();
            String transfersDate = page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr[" + i + "]/td[2]/html()").toString();
            if (transfersDate==null){
                next=false;
                continue;
            }
            try {
                String res = transfersDate.substring(transfersDate.indexOf("title=\"") + 7, transfersDate.lastIndexOf("AM\"") + 2);
                transfers.setTransfersDate(res);
                log.info(i + ":AM---->res  =======================>" + res);
            } catch (Exception e) {
                String res = transfersDate.substring(transfersDate.indexOf("title=\"") + 7, transfersDate.lastIndexOf("PM\"") + 2);
                transfers.setTransfersDate(res);
                log.info(i + ":PM---->res  =======================>" + res);
            }
            String from = page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr[" + i + "]/td[3]/span/a/text()").toString();
            String to = page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr[" + i + "]/td[5]/span/a/text()").toString();
            String quantity = page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr[" + i + "]/td[6]/text()").toString();

            transfers.setFromToken(from);
            transfers.setToToken(to);
            transfers.setQuantity(quantity);
            transfers.setCreateDate(new Date());
            list.add(transfers);
            i++;
        }
        page.putField("transfers", list);
    }

    @Override
    public Site getSite() {
        return site;
    }


}
