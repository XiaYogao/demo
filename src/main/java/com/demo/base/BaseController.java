package com.demo.base;

import com.alibaba.fastjson.JSON;

/**
 * 基础控制器
 */
public class BaseController {

    /**
     * 成功返回
     *
     * @return ResultData的JSON
     */
    protected String success() {
        ResultData<Void> retData = new ResultData<Void>(State.SUCCESS.val(), ResponseState.SUCCESS.code(), ResponseState.SUCCESS.msg(), null);
        return JSON.toJSONString(retData);
    }

    /**
     * 成功返回 （带封装数据）
     *
     * @param data 封装数据
     * @param <T>  类型
     * @return ResultData的JSON
     */
    protected <T> String success(T data) {
        ResultData<T> retData = new ResultData<T>(State.SUCCESS.val(), ResponseState.SUCCESS.code(), ResponseState.SUCCESS.msg(), data);
        return JSON.toJSONString(retData);
    }

    /**
     * 错误返回
     *
     * @return ResultData的JSON
     */
    protected String error() {
        ResultData<Void> retData = new ResultData<Void>(State.ERROR.val(), ResponseState.ERROR.code(), ResponseState.ERROR.msg(), null);
        return JSON.toJSONString(retData);
    }

    /**
     * 错误返回（带封装数据）
     *
     * @param data 封装数据（一般是错误对象）
     * @param <T>  类型
     * @return ResultData的JSON
     */
    protected <T> String error(T data) {
        ResultData<T> retData = new ResultData<T>(State.ERROR.val(), ResponseState.ERROR.code(), ResponseState.ERROR.msg(), data);
        return JSON.toJSONString(retData);
    }

    /**
     * 封装空JSON返回
     *
     * @return 空JSON
     */
    protected String renderNull() {
        return "{}";
    }

    /**
     * 直接封装JSON返回
     *
     * @param data 需要封装的对象
     * @param <T>  对象类型
     * @return 封装对象的JSON
     */
    protected <T> String renderJSON(T data) {
        return JSON.toJSONString(data);
    }


    /**
     * 状态枚举类
     */
    private enum State {

        SUCCESS("OK"),
        ERROR("ERROR");

        private String state;

        State(String state) {
            this.state = state;
        }

        public String val() {
            return this.state;
        }
    }
}
