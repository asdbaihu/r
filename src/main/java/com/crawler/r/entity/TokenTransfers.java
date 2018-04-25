package com.crawler.r.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hcj on 2018/4/25.
 *
 * 交易信息
 */
@Entity
@Table(name = "token_transfers")
public class TokenTransfers implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date transfersDate;

    @Column
    private String fromToken;

    @Column
    private String toToken;

    @Column
    private String quantity;

    @Column
    private Date createDate;

    @Column
    private String del;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransfersDate() {
        return transfersDate;
    }

    public void setTransfersDate(Date transfersDate) {
        this.transfersDate = transfersDate;
    }

    public String getFromToken() {
        return fromToken;
    }

    public void setFromToken(String fromToken) {
        this.fromToken = fromToken;
    }

    public String getToToken() {
        return toToken;
    }

    public void setToToken(String toToken) {
        this.toToken = toToken;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
