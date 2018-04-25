package com.crawler.r.c;

import com.crawler.r.entity.TokenTransfers;
import com.crawler.r.service.TargetTokenService;
import com.crawler.r.service.TokenTransfersService;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EtherScan  implements PageProcessor {

    @Autowired
    TargetTokenService tokenService;
    @Autowired
    TokenTransfersService transfersService;

    private static EtherScan etherScan;

    private void init(){
        etherScan.transfersService=this.transfersService;
        etherScan.tokenService=this.tokenService;
    }
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        String all= page.getHtml().xpath("/html/body/div[1]/div[4]/div[1]/div[1]/table/tbody/tr[1]/td[2]/text()").toString();
        System.out.println("总量：=====>" +all);

//
        List<TokenTransfers> list = new ArrayList<>();
        TokenTransfers transfers=null;
        int i=2;
        while (i<=51){
            i++;
            transfers=new TokenTransfers();
            String transfersDate=page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr["+i+"]/td[2]/span/text()").toString();
            String from=page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr["+i+"]/td[3]/span/a/text()").toString();
            String to=page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr["+i+"]/td[5]/span/a/text()").toString();
            String quantity=page.getHtml().xpath("//*[@id=\"maindiv\"]/table/tbody/tr["+i+"]/td[6]/text()").toString();

//            transfers.setTransfersDate(transfersDate);
            transfers.setFromToken(from);
            transfers.setToToken(to);
            transfers.setQuantity(quantity);
            transfers.setCreateDate(new Date());
            transfers.setDel("0");
            list.add(transfers);
        }
        etherScan.transfersService.save(list);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
