package com.crawler.r.c;

import com.crawler.r.entity.TokenTransfers;
import com.crawler.utils.Constants;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
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

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        List<TokenTransfers> list = new ArrayList<>();
        TokenTransfers transfers = null;
        int i = 2;
        while (i <= 51) {

            transfers = new TokenTransfers();
            String transfersDate = page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr[" + i + "]/td[2]/html()").toString();
            String res = null;
            try {
                res = transfersDate.substring(transfersDate.indexOf("title=\"") + 7, transfersDate.lastIndexOf("AM") + 2);
            } catch (Exception e) {
                res = transfersDate.substring(transfersDate.indexOf("title=\"") + 7, transfersDate.lastIndexOf("PM") + 2);
            }

            String s_date = transfersDate.replace("<span rel=\"tooltip\" data-placement=\"right\" title=\"", "").replace("\">2 hrs 18 mins ago</span>", "");
            Date date = null;
            try {
                date = Constants.sdf.parse(res);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String from = page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr[" + i + "]/td[3]/span/a/text()").toString();
            String to = page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr[" + i + "]/td[5]/span/a/text()").toString();
            String quantity = page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr[" + i + "]/td[6]/text()").toString();

            transfers.setTransfersDate(date);
            transfers.setFromToken(from);
            transfers.setToToken(to);
            transfers.setQuantity(quantity);
            transfers.setCreateDate(new Date());
            transfers.setDel("0");

            list.add(transfers);
            i++;
        }
        page.putField("transfers",list);
        System.out.println("start====>4");
        System.out.println("end ------");
    }

    @Override
    public Site getSite() {
        return site;
    }


}
