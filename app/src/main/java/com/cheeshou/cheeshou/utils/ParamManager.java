package com.cheeshou.cheeshou.utils;

import android.content.Context;

import com.cheeshou.cheeshou.dealer.ui.model.CustomerWantCarModel;

/**
 * Created by 71033 on 2018/7/29.
 */
public class ParamManager {

    private static ParamManager instance;

    private Context context;

    public int channelType;

    public String carFullName;

    private String carId;

    private CustomerWantCarModel model;
    private String createCustomerWantCarId;

    public String getCreateCustomerWantCarId() {
        return createCustomerWantCarId;
    }

    public void setCreateCustomerWantCarId(String createCustomerWantCarId) {
        this.createCustomerWantCarId = createCustomerWantCarId;
    }

    public static ParamManager getInstance(Context context) {

        if (instance == null) {
            synchronized (ParamManager.class) {
                if (instance == null) {
                    instance = new ParamManager(context);
                }
            }
        }

        return instance;

    }

    public ParamManager(Context context) {
        this.context = context;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public String getCarFullName() {
        return carFullName;
    }

    public void setCarFullName(String carFullName) {
        this.carFullName = carFullName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public CustomerWantCarModel getModel() {
        if (model == null) {
            return new CustomerWantCarModel();
        }
        return model;
    }

    public void setModel(CustomerWantCarModel model) {
        this.model = model;
    }
}
