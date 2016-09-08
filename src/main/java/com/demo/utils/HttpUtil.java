package com.demo.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

/**
 * HTTP工具类
 *
 * @author sharygus@gmail.com
 * @date 2016/9/8 下午 15:42
 * @since V1.0
 */
public class HttpUtil {

    // 私密连接工厂
    private static SSLConnectionSocketFactory socketFactory;

    /**
     * 重写验证方法：取消检测SSL
     */
    private static TrustManager manager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException { }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException { }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // return new X509Certificate[0];
            return null;
        }
    };


    /**
     * HTTP get请求
     *
     * @param url    请求地址
     * @param cookie Cookie
     * @param refer  跳转地址
     * @return 请求结果
     */
    public static String httpGet(String url, String cookie, String refer) {
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();

        HttpGet get = new HttpGet(url);

        if (null != cookie)
            get.setHeader("Cookie", cookie);
        if (null != refer)
            get.setHeader("Referer", refer);

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP post请求
     *
     * @param url    请求地址
     * @param values 请求参数
     * @param cookie Cookie
     * @param refer  跳转页面
     * @return 请求结果
     */
    public static String httpPost(String url, List<NameValuePair> values, String cookie, String refer) {
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();

        HttpPost post = new HttpPost(url);

        if (null != cookie)
            post.setHeader("Cookie", cookie);
        if (null != refer)
            post.setHeader("Referer", refer);

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values, "UTF-8");
            post.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(post);
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 启用SSL
     */
    private static void enableSSL() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{manager}, null);
            socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    /**
     * HTTPS get请求
     * @param url 请求地址
     * @param cookie Cookie
     * @param refer 跳转页面
     * @return 请求数据
     */
    public static String httpsGet(String url, String cookie, String refer) {
        enableSSL();
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        Registry socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config).build();

        HttpGet get = new HttpGet(url);
        if(null != cookie)
            get.setHeader("Cookie", cookie);
        if(null != refer)
            get.setHeader("Referer", refer);

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            return  response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTPS post请求
     * @param url 请求地址
     * @param values 请求参数
     * @param cookie Cookie
     * @param refer 跳转页面
     * @return 请求数据
     */
    public static String httpsPost(String url, List<NameValuePair> values, String cookie, String refer){
        enableSSL();
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        Registry socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config).build();

        HttpPost post = new HttpPost(url);
        if(null != cookie)
            post.setHeader("Cookie", cookie);
        if(null != refer)
            post.setHeader("Referer", refer);

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values, "UTF-8");
            post.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(post);
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
