package com.cheeshou.cheeshou.config;

import android.support.annotation.IntDef;

public class Environment {

    public static final int DEBUG = 0; //测试
    public static final int TOBE_PRODUCT = 1; //准生产
    public static final int PRODUCT = 2; //生产

    // 定义注解
    @IntDef({DEBUG, TOBE_PRODUCT, PRODUCT})
    public @interface ENV {
    }

}
