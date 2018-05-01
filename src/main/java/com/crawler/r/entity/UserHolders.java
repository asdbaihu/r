package com.crawler.r.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hcj on 2018/4/28.
 */
@Entity
@Table(name = "holders")
public class UserHolders implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 合约地址 id
     */
    @Column
    private Long targetId;

    /**
     * 持有者地址
     */
    @Column
    private String uAddress;

    /**
     *  对应合约的地址
     */
    @Column
    private String tAddress;

    /**
     * 持有剩余量
     */
    @Column
    private String balance;

    /**
     * 持有比率  需要后期任务更新持有量
     */
    @Column
    private String percentage;

    @Column
    private Date createDate;

    /**
     * 数据处理标记  0 待处理
     */
    @Column
    private String flag;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress;
    }


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
