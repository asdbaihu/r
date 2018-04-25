package com.crawler.r.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "target_token")
public class TargetToken implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 目标
     */
    @Column(length = 256)
    private String targetToken;

    @Column(length = 256)
    private String allToken;

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
}
