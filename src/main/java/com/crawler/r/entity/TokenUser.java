package com.crawler.r.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  用户 token 表 地址唯一，对应不同的合约交易信息  一对多
 * Created by hcj on 2018/4/27.
 */
@Entity
@Table(name = "token_user")
public class TokenUser implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /**
     *   唯一地址
     */
    @Column
    private String address;

    /**
     * 交易总量
     */
    @Column
    private String txns;

    /**
     * 0 个人  1 交易所
     */
    @Column
    private String tokenType;

    /**
     * ]创建时间
     */
    @Column
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTxns() {
        return txns;
    }

    public void setTxns(String txns) {
        this.txns = txns;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
