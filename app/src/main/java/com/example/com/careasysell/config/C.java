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
    String TAG_PAGE_MARKET = "market";

    //库存管理 跳转搜索tag
    String TAG_PAGE_STORE_MANAGER = "TAG_PAGE_STORE_MANAGER";
    // 已上架
    String TAG_STATE_PUT_AWAY = "TAG_STATE_PUT_AWAY";
    // 已订购
    String TAG_STATE_RESERVE = "TAG_STATE_RESERVE";
    // 已售
    String TAG_STATE_SELL = "TAG_STATE_SELL";

}
