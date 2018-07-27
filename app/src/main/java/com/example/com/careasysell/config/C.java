package com.example.com.careasysell.config;

/**
 * Created by 71033 on 2018/7/19.
 */
public interface C {

    //正式
    String PRODUCT_BASE_URL = "http://api.yipurse.com/";


    //测试
    String TEST_BASE_URL = "http://test.dun.yipurse.cn:8888/api/";

    //http缓存大小
    int httpCacheSize = 10 * 1024 * 1024;

    //http超时时间
    long httpTimeOut = 5000L;


    //首页

    String TAG_PAGE_DUANJIE = "首页";
    String TAG_PAGE_SETTING = "设置";


    //页面tag
    String TAG_PAGE_MAIN = "TAG_PAGE_MAIN";
    String TAG_PAGE_OPTIONS = "options";
    String TAG_PAGE_DEALER = "dealer";

}
