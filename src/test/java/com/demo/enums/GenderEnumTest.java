package com.demo.enums;

import org.junit.Test;

/**
 * @author xiayongao@gmail.com
 * @date 2016/9/12 0012 下午 23:42
 * @since V1.0
 */
public class GenderEnumTest {

    @Test
    public void testGender() {
        System.out.println(GenderEnum.MALE.getCode());
        System.out.println(GenderEnum.MALE.getName());

        System.out.println(GenderEnum.toEnum(-1));
        System.out.println(GenderEnum.toEnum("女"));

    }

}
