package com.crawler.task;

import com.crawler.r.entity.TargetToken;
import com.crawler.r.entity.UserHolders;
import com.crawler.r.service.TargetTokenService;
import com.crawler.r.service.UserHoldersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

/**
 *  计算用户持有百分比
 * Created by hcj on 2018/5/1.
 */
//@Component
public class TaskPercentage {

    @Autowired
    private UserHoldersService holdersService;
    @Autowired
    private TargetTokenService tokenService;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void calculation(){
        //获取持有百分比为空的数据
        List<UserHolders> list=holdersService.getListHolders();
        if (list.size()>0){
            for (UserHolders holders:list){
                try {
                    TargetToken token=tokenService.findById(holders.getTargetId());
                    String name=token.getName();
                    String m=holders.getBalance();
                    //余额
                    String q=m.replace(" "+name,"").replace(",","").trim();
                    if (token!=null){
                        String html=token.getHtml();
                        String n;
                        try {
                            n=html.substring(html.indexOf(0)+1,html.lastIndexOf("<b>")).replace(",","");
                        }catch (Exception e){
                           n=html.substring(html.indexOf(0)+1,html.lastIndexOf(name)).replace(",","");
                        }
                        Double c=Double.valueOf(n);
                        Double d=Double.valueOf(q);
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        numberFormat.setMaximumFractionDigits(5);
						numberFormat.setRoundingMode(RoundingMode.HALF_UP);
                        String result = numberFormat.format(d/c*100)+"%";
                        holders.setPercentage(result);
                        holdersService.save(holders);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
