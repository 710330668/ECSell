package com.example.com.careasysell.utils;

import android.content.Context;

/**
 * Created by 71033 on 2018/7/29.
 */
public class ParamManager {

    private static ParamManager instance;

    private Context context;

    public int channelType ;

    public static ParamManager getInstance(Context context){

        if(instance == null){
            synchronized (ParamManager.class){
                if(instance == null){
                    instance = new ParamManager(context);
                }
            }
        }

        return instance;

    }

    public ParamManager(Context context){
        this.context = context;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }
}
