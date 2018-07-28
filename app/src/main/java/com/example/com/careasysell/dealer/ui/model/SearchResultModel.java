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
}
