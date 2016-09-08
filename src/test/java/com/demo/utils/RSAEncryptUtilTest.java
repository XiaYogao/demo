package com.demo.utils;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class RSAEncryptUtilTest {

	private String publicKey;  
    private String privateKey;  
  
    @Before  
    public void setUp() throws Exception {  
        Map<String, Object> keyMap = RSAEncryptUtil.initKey();  
  
        publicKey = RSAEncryptUtil.getPublicKey(keyMap);  
        privateKey = RSAEncryptUtil.getPrivateKey(keyMap);  
        System.err.println("公钥: \n\r" + publicKey);  
        System.err.println("私钥： \n\r" + privateKey);  
    }  
  
    @Test  
    public void test() throws Exception {  
        System.err.println("公钥加密——私钥解密");  
        String inputStr = "abc";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encodedData = RSAEncryptUtil.encryptByPublicKey(data, publicKey);  
  
        byte[] decodedData = RSAEncryptUtil.decryptByPrivateKey(encodedData,  
                privateKey);  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        Assert.assertEquals(inputStr, outputStr);  
  
    }  
  
    @Test  
    public void testSign() throws Exception {  
        System.err.println("私钥加密——公钥解密");  
        String inputStr = "sign";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encodedData = RSAEncryptUtil.encryptByPrivateKey(data, privateKey);  
  
        byte[] decodedData = RSAEncryptUtil  
                .decryptByPublicKey(encodedData, publicKey);  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        Assert.assertEquals(inputStr, outputStr);  
  
        System.err.println("私钥签名——公钥验证签名");  
        // 产生签名  
        String sign = RSAEncryptUtil.sign(encodedData, privateKey);  
        System.err.println("签名:\r" + sign);  
  
        // 验证签名  
        boolean status = RSAEncryptUtil.verify(encodedData, publicKey, sign);  
        System.err.println("状态:\r" + status);  
        Assert.assertTrue(status);  
  
    }  

}
