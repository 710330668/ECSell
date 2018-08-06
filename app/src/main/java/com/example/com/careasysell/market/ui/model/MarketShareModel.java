package com.example.com.careasysell.market.ui.model;

import java.util.ArrayList;
import java.util.List;

public class MarketShareModel {

    private String shareTime;
    private int shareCount;
    private String shareTitle;
    private List<String> imgUrl;

    public String getShareTime() {
        return shareTime;
    }

    public void setShareTime(String shareTime) {
        this.shareTime = shareTime;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        if (imgUrl == null) {
            this.imgUrl = new ArrayList<>();
        } else {
            this.imgUrl = imgUrl;

        }
    }
}
