package com.demo.utils;

import org.junit.Test;

/**
 * HTTP test class
 *
 * @author sharygus@gmail.com
 * @date 2016/9/9 下午 14:38
 * @since V1.0
 */
public class HttpUtilTest {

    @Test
    public void testHttpGet() {
        System.out.println(HttpUtil.httpGet("http://wwww.baidu.com", null, null));
    }

    @Test
    public void testHttpsGet() {
        System.out.println(HttpUtil.httpsGet("https://wwww.baidu.com", null, null));
    }
}
