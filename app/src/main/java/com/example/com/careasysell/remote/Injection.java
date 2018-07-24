package com.example.com.careasysell.remote;

import com.example.com.careasysell.MyApplication;

/**
 * Created by 71033 on 2018/7/19.
 */
public class Injection {

    private static ApiService apiService;


    static {
        apiService = MyApplication.getInstance().getApiService();
    }

    public static ApiService provideApiService() {
        return apiService;
    }
}
