package com.demo.base;

/**
 * 响应状态美剧
 */
public enum ResponseState {


    SUCCESS("200", "SUCCESS"),
    ERROR("500", "ERROR")

    /* Other response status defined here */







    ;

    private String code;
    private String msg;

    ResponseState(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
