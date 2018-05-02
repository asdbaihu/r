package com.crawler.config;

import java.io.Serializable;

/**
 * 接口调用状态及数据返回
 * @author hcj
 */
public class R<T> implements Serializable{

    public static final Integer SUCCESS = 0;
    public static final Integer ERROR = -1;
    public static final Integer LOGIN_ERROR = 1;

    private Integer code;
    private String msg;
    private T data;


    public static Integer getSUCCESS() {
        return SUCCESS;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
