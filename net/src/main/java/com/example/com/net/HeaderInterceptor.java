package com.example.com.net;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * <p>
 * Header拦截器
 * </p>
 * Created by wpf on 2017/12/10.
 */

public class HeaderInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers headers = buildHeaders(request);
        Request newRequest = request.newBuilder().headers(headers).build();

        return chain.proceed(newRequest);

    }

    private Headers buildHeaders(Request request) throws IOException {
        HashMap headers = new HashMap();
        Headers headers1 = request.headers();
        for(String s : headers1.names()){
            headers.put(s,headers1.get(s));
        }
        return Headers.of(headers);
    }

    /**
     * RequestBody 转换成 String格式
     *
     * @param requestBody
     * @return
     * @throws IOException
     */
    private String transformBody(RequestBody requestBody) throws IOException {
        String body = "";
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            body = buffer.readString(charset);
        }
        return body;
    }
}
