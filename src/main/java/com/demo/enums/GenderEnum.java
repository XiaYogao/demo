package com.demo.enums;

import com.demo.base.IBaseEnum;

/**
 * 性别枚举类
 *
 * @author xiayongao@gmail.com
 * @date 2016/9/12 0012 下午 23:36
 * @since V1.0
 */
public enum GenderEnum implements IBaseEnum<GenderEnum, Integer> {

    MALE(1, "男"),
    FEMALE(0, "女"),
    SECRET(-1, "保密");

    private Integer code;
    private String name;

    GenderEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static GenderEnum toEnum(Integer code){
        for (GenderEnum gender : GenderEnum.values()) {
            if(gender.getCode().equals(code)){
                return gender;
            }
        }
        return null;
    }

    public static GenderEnum toEnum(String name){
        for (GenderEnum gender : GenderEnum.values()) {
            if(gender.getName().equals(name)){
                return gender;
            }
        }
        return null;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
