package com.crawler.r.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  要爬去的目标存放地址
 */
@Entity
@Table(name = "target_token")
public class TargetToken implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 目标 合约地址
     */
    @Column(length = 256)
    private String targetToken;

    @Column(length = 256)
    private String allToken;

    /**
     * 名称
     */
    @Column
    private String name;

    /**
     * 开启爬去数据标志 0 开启，1关闭
     */
    @Column
    private String flag;

    @Column
    private String html;

    /**
     * 删除标记
     */
    @Column
    private String del;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetToken() {
        return targetToken;
    }

    public void setTargetToken(String targetToken) {
        this.targetToken = targetToken;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getAllToken() {
        return allToken;
    }

    public void setAllToken(String allToken) {
        this.allToken = allToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
