package com.demo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 *
 * @author sharygus@gmail.com
 * @date 2016/9/8 下午 16:51
 * @since V1.0
 */
public final class MD5EncryptUtil {

    // 用来将字节转换成16进制表示的字符
    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * md5加密字符串
     *
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5(String str) {
        str += "jYtie7u"; // FIXME 额外加密串，防止在线解密
        try {
            byte[] source = str.getBytes("utf-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char cstr[] = new char[16 * 2];
            for (int i = 0, k = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                cstr[k++] = hexDigits[byte0 >>> 4 & 0xf];
                cstr[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(cstr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(MD5EncryptUtil.md5("1"));// b111d3dbbaff3b39e2e3cd5cf17a1abe
        // e10adc3949ba59abbe56e057f20f883e
    }

}
