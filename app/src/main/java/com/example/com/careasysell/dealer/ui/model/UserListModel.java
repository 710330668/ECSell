package com.example.com.careasysell.dealer.ui.model;

/**
 * Created by 71033 on 2018/8/6.
 */
public class UserListModel {

    private String userId;

    private String imageUrl;

    private String userName ;

    private String account;

    private String timestamp;

    private String phone;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserListModel(String userId,String imageUrl, String userName, String account, String timestamp, String phone) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.userName = userName;
        this.account = account;
        this.timestamp = timestamp;
        this.phone = phone;
    }
}
