package com.example.com.careasysell.dealer.ui.model;

/**
 * Author ： DasonYu
 * Date ： 2018/7/28
 * Email Address : 15764240573@163.com
 */

public class SearchResultModel {

    //    图片
    private String imageUrl;
    //    标题
    private String title;
    //    副标题
    private String subTitle;
    //    价位
    private String price;
    //    提成
    private String deduct;
    //    状态
    private String state;
    //    日期
    private String date;
    //    上架
    private boolean isPut;
    //    上架入口
    private String id;

    private boolean openPutEntrance;

    public boolean isOpenPutEntrance() {
        return openPutEntrance;
    }

    public void setOpenPutEntrance(boolean openPutEntrance) {
        this.openPutEntrance = openPutEntrance;
    }

    public boolean isPut() {
        return isPut;
    }

    public void setPut(boolean put) {
        isPut = put;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
