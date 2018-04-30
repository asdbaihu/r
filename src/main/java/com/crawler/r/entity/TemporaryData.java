package com.crawler.r.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hcj on 2018/4/29.
 */
@Entity
@Table(name = "temporary_data")
public class TemporaryData {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * token合约 id  例如 FDZ 的id
     */
    @Column
    private Long tokenId;

    /**
     * 合约地址
     */
    @Column
    private String token;

    /**
     * 交易时间
     */
    @Column
    private Date transfersDate;

    /**
     * from
     */
    @Column
    private String fromToken;

    /**
     * to
     */
    @Column
    private String toToken;

    /**
     * 交易量
     */
    @Column
    private String quantity;

    @Column
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
