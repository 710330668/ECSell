package com.example.com.careasysell.config;

import android.support.annotation.IntDef;

/**
 * Created by 71033 on 2018/7/19.
 */
public interface C {

    //正式
    String PRODUCT_BASE_URL = "http://api.yipurse.com/";


    //测试
    String TEST_BASE_URL = "http://39.104.136.205/";

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

    String USER_TYPE_CYS = "USER_CYS";
    String USER_TYPE_XS = "USER_XS";
    String USER_TYPE_JXS = "USER_JXS";
    String USER_TYPE_DTRY = "USER_DTRY";

    int INVENTORY_OPTION = 0;  //库存——车源
    int INVENTORY_DEALER = 1; //库存 - 经销
    int INVENTORY_MARKET = 2;  //库存 - 销售

    int ORDER_DEALER = 3;


    String SOURCE_ORDER = "order";  //订单

    String SOURCE_DAY_SELL = "day_order";  //日售出

    String SOURCE_MONTH_SELL = "month_order";  //月售出

    String SOURCE_DAY_NEW = "day_new";  //日新增

    String SOURCE_MONTH_NEW = "month_new";  //月新增

    String SOURCE_DAY_SHOP = "day_shop";  //日进店

    String SOURCE_MONTH_SHOP = "month_shop";  //月进店

    String  SOURCE_CAR_DETAIL_NATION = "nation_source";

    String SOURCE_CAR_DETAIL_STORE = "store";

    @IntDef({INVENTORY_OPTION, INVENTORY_DEALER, INVENTORY_MARKET})
    public @interface INVENTORY {
    }

    String USER_DB = "user_db";

    String USER_TOKEN = "token";

    String USER_TYPE = "user_type";

//    	"code":200,	"data":[
//    {
//        "dataCode":"NO_STORE",			"dataName":"未到店"
//    },		{
//        "dataCode":"YES_STORE",			"dataName":"已到店"
//    },		{
//        "dataCode":"RESERVE",			"dataName":"已预订"
//    },		{
//        "dataCode":"SUCCESS",			"dataName":"已成交"
//    },		{
//        "dataCode":"FAIL",			"dataName":"战败"
//    }
//    	],	"msg":"操作成功"

}
