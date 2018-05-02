package com.crawler.rest;

import com.crawler.config.R;
import com.crawler.r.entity.*;
import com.crawler.r.service.TargetTokenService;
import com.crawler.r.service.TokenTransfersService;
import com.crawler.r.service.TokenUserService;
import com.crawler.r.service.UserHoldersService;
import com.crawler.task.c.TokenHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcj on 2018/5/1.
 */
@RestController
@RequestMapping(value = "/show")
public class RController {

    @Autowired
    private TokenTransfersService transfersService;
    @Autowired
    private UserHoldersService holdersService;
    @Autowired
    private TargetTokenService tokenService;
    @Autowired
    private TokenUserService userService;


    /**
     * 基本信息
     *
     * @return
     */
    @RequestMapping(value = "/info")
    public R<List<Results>> getToken() {
        List<TargetToken> list = tokenService.findAllList();
        R<List<Results>> r = new R<>();
        List<Results> resultses = new ArrayList<>();
        Results results = null;
        if (list.size() > 0) {
            try {
                // 所有用户
                List<TokenUser> users=userService.findAll();
                for (TargetToken token : list) {
                    results = new Results();
                    results.setName(token.getName());
                    results.setAmount(token.getHtml().replace("<b>", "").replace("</b>", ""));
                    results.setCirculation(null);
                    //今天的数据
                    List<TokenTransfers> list1 = transfersService.findListByTid(token.getId());
                    if (list1.size() > 0) {
                        for (TokenTransfers transfers : list1) {
                            //交易量
                            double a = Double.valueOf(transfers.getQuantity().replace(",", ""));
                            for (TokenUser user:users){
                                if (transfers.getToToken().equals(user.getAddress())){
                                    if (user.getTokenType().equals("0")) {//个人 今日转入
                                        double v1 = results.getIntoPeople() == null ? 0 : results.getIntoPeople();
                                        results.setIntoPeople(v1 + a);
                                    } else {//交易所 今日转入
                                        double v2 = results.getIntoTransfer() == null ? 0 : results.getIntoTransfer();
                                        results.setIntoTransfer(v2 + a);
                                    }
                                }
                            }
                        }
                    }
                    //昨天的数据对比
                    List<TokenTransfers> yesToday = transfersService.findListByTidYesToday(token.getId());
                    if (yesToday.size() > 0) {
                        //今天转入个人
                        Double a = results.getIntoPeople();
                        //今天转入交易所
                        Double b = results.getIntoTransfer();
                        //个人
                        double v1 = 0;
                        //交易所
                        double v2 = 0;
                        for (TokenTransfers transfers : yesToday) {
                            //昨天数据 的交易量
                            double c = Double.valueOf(transfers.getQuantity().replace(",", ""));
                            for (TokenUser user1:users){
                                if (transfers.getToToken().equals(user1.getAddress())){
                                    if (user1.getTokenType().equals("0")) {//昨天个人 今日转入
                                        v1 = v1 + c;
                                    } else {//昨天交易所 今日转入
                                        v2 = v2 + c;
                                    }
                                }
                            }
                        }
                        Double d = a - v1;
                        Double e = b - v2;
                        Double res = getP(d, v1);
                        Double res1 = getP(e, v2);
                        //个人百分比
                        results.setYesTodayP(res.toString() + "%");
                        //交易所百分比
                        results.setYesTodayT(res1.toString() + "%");
                    }
                    //上周的数据 （待处理）

                    //本周的数据（待处理）

                    //大额账户流动信息
                    List<TokenTransfers> list2=new ArrayList<>();
                    resultses.add(results);
                    r.setMsg("success");
                    r.setCode(R.SUCCESS);
                    r.setData(resultses);
                }
            } catch (Exception e) {
                r.setMsg("error");
                r.setCode(R.ERROR);
            }
        }
        return r;
    }

    /**
     * 计算百分比
     *
     * @param a
     * @param b
     * @return
     */
    private double getP(Double a, Double b) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(5);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        String result = numberFormat.format(a / b * 100);
        return Double.valueOf(result);
    }


}
