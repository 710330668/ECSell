package com.example.com.net;

import com.example.com.net.config.HttpConfig;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 * 类说明
 * </p>
 * Created by wpf on 2017/12/20.
 */

public class HttpClient {
    private static OkHttpClient.Builder okHttpBuilder;
    private static Retrofit.Builder retrofitBuilder;
    private static HostnameVerifier hostnameVerifier;//主机域名验证
    private static SSLSocketFactory sslSocketFactory;//SSL工厂
    private static OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private static String baseUrl = null;
    private static HashMap<String, String> urls = null;
    private static HttpClient instance;
    private static long timeOut = 5000L;
    private static Cache cache;
    private static String urlHeaderTag = "url_name";


    /**
     * 获取HttpClient的单例
     *
     * @return HttpClient的唯一对象
     */
    public static HttpClient getInstance(HttpConfig config) {
        initConfig(config);
        return HttpClient.HttpClientHolder.INTANCE;
    }

    public static OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }


    public static void initConfig(HttpConfig httpConfig) {
        okHttpBuilder = httpConfig.getOkHttpBuilder();
        baseUrl = httpConfig.getBaseUrl();
        urls = httpConfig.getOtherUrls();
        timeOut = httpConfig.getTimeout();
        cache = httpConfig.getHttpCache();
        hostnameVerifier = httpConfig.getHostnameVerifier();
        sslSocketFactory = httpConfig.getSslSocketFactory();
        urlHeaderTag = httpConfig.getUrlHeadreTag();

    }

    /**
     * 单例模式中的静态内部类写法
     */
    private static class HttpClientHolder {
        private static final HttpClient INTANCE = new HttpClient();
    }

    public HttpClient() {
        try {

            final X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new LoggerInterceptor());
            logInterceptor.setLevel(BuildConfig.DEBUG
                    ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);

            if (okHttpBuilder == null) {
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(timeOut, TimeUnit.MILLISECONDS)
                        .hostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        })
                        .addInterceptor(logInterceptor)
                        //TODO fix
                        .sslSocketFactory(sslSocketFactory, trustManager)
                        .addNetworkInterceptor(new CacheInterceptor())
                        .cache(cache)
                        .build();
            } else {
                okHttpClient = okHttpBuilder.build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取的Retrofit的实例， 引起Retrofit变化的因素只有静态变量BASE_URL的改变。
     */
    public Retrofit getRetrofit() {
        if (retrofit != null) {

            return retrofit;
        } else if (retrofitBuilder != null) {

            retrofit = retrofitBuilder.build();
        } else if (!"".equals(baseUrl)) {

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }


    public static Retrofit getDownloadRetrofit(String url,OkHttpClient client) {
        Retrofit retrofit = null;
        if (retrofitBuilder == null) {
            if (!"".equals(url) && retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(url)
                        .client(client)
                        .build();
            }
        } else {
            retrofit = retrofitBuilder.build();
        }


        return retrofit;
    }
}
