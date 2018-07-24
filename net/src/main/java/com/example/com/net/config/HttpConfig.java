package com.example.com.net.config;

import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *
 * Created by 710330 on 2018/4/18
 *
 * http配置
 */
public class HttpConfig {

    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit.Builder retrofitBuilder;
    //超时时间
    private long timeout = 5000L;//连接超时时间，默认5S
    //缓存
    private Cache httpCache;//Http缓存对象
    //https相关
    private HostnameVerifier hostnameVerifier;//主机域名验证
    private SSLSocketFactory sslSocketFactory;//SSL工厂
    //主URL
    private String baseUrl;
    //其它URL
    private HashMap<String,String> otherUrls;
    //header中url标识
    private String urlHeadreTag = "url_name";

    private static HttpConfig instance;

    public static HttpConfig getInstance() {
        if (instance == null) {
            synchronized (HttpConfig.class) {
                if (instance == null) {
                    instance = new HttpConfig();
                }
            }
        }
        return instance;
    }

    public HttpConfig() {
    }

    public OkHttpClient.Builder getOkHttpBuilder() {
        return okHttpBuilder;
    }

    public HttpConfig setOkHttpBuilder(OkHttpClient.Builder okHttpBuilder) {
        this.okHttpBuilder = okHttpBuilder;
        return this;
    }

    public Retrofit.Builder getRetrofitBuilder() {
        return retrofitBuilder;
    }

    public HttpConfig setRetrofitBuilder(Retrofit.Builder retrofitBuilder) {
        this.retrofitBuilder = retrofitBuilder;
        return this;
    }


    public long getTimeout() {
        return timeout;
    }

    public HttpConfig setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }


    public Cache getHttpCache() {
        return httpCache;
    }

    public HttpConfig setHttpCache(Cache httpCache) {
        this.httpCache = httpCache;
        return this;
    }

    public HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }

    public HttpConfig setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    public HttpConfig setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
        return this;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public HttpConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public HashMap<String, String> getOtherUrls() {
        return otherUrls;
    }

    public HttpConfig setOtherUrls(HashMap<String, String> otherUrls) {
        this.otherUrls = otherUrls;
        return this;
    }

    public String getUrlHeadreTag() {
        return urlHeadreTag;
    }

    public HttpConfig setUrlHeadreTag(String urlHeadreTag) {
        this.urlHeadreTag = urlHeadreTag;
        return this;
    }
}
