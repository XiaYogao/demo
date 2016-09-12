package com.demo.base;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 封装返回数据
 */
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 5015549331166209862L;

    /** OK or ERROR */
    @JSONField(name = "state")
    private String state;
    /** 状态码 */
    @JSONField(name = "code")
    private String code;
    /** 提示消息 */
    @JSONField(name = "msg")
    private String msg;
    /** 返回数据 */
    @JSONField(name = "data")
    private T data;

    public ResultData(){}

    public ResultData(String state, String code, String msg, T data) {
        this.state = state;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
