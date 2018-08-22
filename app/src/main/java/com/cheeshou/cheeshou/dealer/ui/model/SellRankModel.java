package com.cheeshou.cheeshou.dealer.ui.model;

/**
 * Created by 71033 on 2018/8/8.
 */
public class SellRankModel {

    private String imgStr;

    private String name;

    private String clientNum;

    private String daodian;

    private String chengjiao;

    private String call;

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientNum() {
        return clientNum;
    }

    public void setClientNum(String clientNum) {
        this.clientNum = clientNum;
    }

    public String getDaodian() {
        return daodian;
    }

    public void setDaodian(String daodian) {
        this.daodian = daodian;
    }

    public String getChengjiao() {
        return chengjiao;
    }

    public void setChengjiao(String chengjiao) {
        this.chengjiao = chengjiao;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public SellRankModel(String imgStr, String name, String clientNum, String daodian, String chengjiao, String call) {
        this.imgStr = imgStr;
        this.name = name;
        this.clientNum = clientNum;
        this.daodian = daodian;
        this.chengjiao = chengjiao;
        this.call = call;
    }
}
