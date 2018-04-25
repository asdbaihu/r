package com.crawler.r.utils;

import com.crawler.r.c.EtherScan;
import com.crawler.r.entity.Article;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;


public class CnblogsSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);


    //  //*[@id="post_list"]/div[1]/div[2]/div/a

    @Override
    public void process(Page page) {

        List<Article> articles=new ArrayList<>();
        Article article=null;
        boolean t=true;
        int i=1;
        while (t){
            article=new Article();
            page.putField("author", page.getHtml().xpath("//*[@id=\"post_list\"]/div["+i+"]/div/div/a/text()").toString());
            article.setAuthor(page.getResultItems().get("author"));
            page.putField("con", page.getHtml().xpath("//*[@id=\"post_list\"]/div["+i+"]/div/p/text()").toString());
            article.setContent(page.getResultItems().get("con"));
            articles.add(article);
            page.putField("title", page.getHtml().xpath("//*[@id=\"post_list\"]/div["+i+"]/div/h3/a/text()").toString());
            if (page.getResultItems().get("title") == null) {
                page.setSkip(true);
                t=false;
            }else {
                i++;
                article.setTitle(page.getResultItems().get("title"));
            }

        }
        System.out.println("长度："+articles.size());
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args){

        Spider.create(new CnblogsSpider()).addUrl(new String[]{"https://www.cnblogs.com/"}).thread(5).run();
    }

}
