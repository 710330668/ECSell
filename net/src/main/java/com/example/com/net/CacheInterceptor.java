package com.example.com.net;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 71033 on 2018/4/17.
 */
public class CacheInterceptor implements Interceptor {
    private static final String TAG = "CacheInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        //取出header 判断是否支持缓存 缓存时间-->对应的值  //TODO:与url的header冲突 待优化
        String cache = request.cacheControl().toString();
        Log.e(TAG, "intercept: " + cache );
        if (!TextUtils.isEmpty(cache)) {
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for cache seconds
                    .header("Cache-Control", cache)
                    .build();
        } else {
            return response;
        }
    }
}
