package com.demo.base;

/**
 * 基础枚举接口
 * T:code类型
 * @author sharygus@gmail.com
 * @date 2016/9/12 0012 下午 23:24
 * @since V1.0
 */
public interface IBaseEnum<E extends Enum<?>, T> {

    /** 获得编码 */
    T getCode();
    /** 获得名称 */
    String getName();

}
