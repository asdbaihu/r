package com.crawler.r.entity;

import javax.persistence.*;

@Entity
@Table(name = "target_token")
public class TargetToken {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 目标
     */
    @Column(length = 256)
    private String targetToken;

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
}
