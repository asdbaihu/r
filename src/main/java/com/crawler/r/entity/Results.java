package com.crawler.r.entity;

import java.util.List;

/**
 * 数据展示结果集
 * Created by hcj on 2018/5/1.
 */
public class Results {

    /**
     * 名称
     */
    private String name;
    /**
     * 总量
     */
    private String amount;
    /**
     * l流通
     */
    private Double circulation;
    /**
     * 交易所存量
     */
    private Double stock;

    /**
     * 当前锁仓量
     */
    private Double locks;

    /**
     * 今日转入交易所
     */
    private Double intoTransfer;

    /**
     * 今日转入个人
     */
    private Double intoPeople;
    /**
     * 净转入交易所
     */
    private Double statically;

    /**
     * 比上一日
     */
    private String yesTodayT;
    /**
     * 比上一周
     */
    private String lastWeekT;

    /**
     * 比上一月
     */
    private String lastMonthT;

    /**
     * 比上一日
     */
    private String yesTodayP;
    /**
     * 比上一周
     */
    private String lastWeekP;
    /**
     * 比上一月
     */
    private String lastMonthP;

    /**
     * 转入信息
     */
    private List<String> result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Double getCirculation() {
        return circulation;
    }

    public void setCirculation(Double circulation) {
        this.circulation = circulation;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getLocks() {
        return locks;
    }

    public void setLocks(Double locks) {
        this.locks = locks;
    }

    public Double getIntoTransfer() {
        return intoTransfer;
    }

    public void setIntoTransfer(Double intoTransfer) {
        this.intoTransfer = intoTransfer;
    }

    public Double getIntoPeople() {
        return intoPeople;
    }

    public void setIntoPeople(Double intoPeople) {
        this.intoPeople = intoPeople;
    }

    public Double getStatically() {
        return statically;
    }

    public void setStatically(Double statically) {
        this.statically = statically;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public String getYesTodayT() {
        return yesTodayT;
    }

    public void setYesTodayT(String yesTodayT) {
        this.yesTodayT = yesTodayT;
    }

    public String getLastWeekT() {
        return lastWeekT;
    }

    public void setLastWeekT(String lastWeekT) {
        this.lastWeekT = lastWeekT;
    }

    public String getLastMonthT() {
        return lastMonthT;
    }

    public void setLastMonthT(String lastMonthT) {
        this.lastMonthT = lastMonthT;
    }

    public String getYesTodayP() {
        return yesTodayP;
    }

    public void setYesTodayP(String yesTodayP) {
        this.yesTodayP = yesTodayP;
    }

    public String getLastWeekP() {
        return lastWeekP;
    }

    public void setLastWeekP(String lastWeekP) {
        this.lastWeekP = lastWeekP;
    }

    public String getLastMonthP() {
        return lastMonthP;
    }

    public void setLastMonthP(String lastMonthP) {
        this.lastMonthP = lastMonthP;
    }
}
