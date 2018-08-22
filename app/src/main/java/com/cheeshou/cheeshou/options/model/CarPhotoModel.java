package com.cheeshou.cheeshou.options.model;

import android.graphics.Bitmap;

/**
 * Created by 71033 on 2018/8/4.
 */
public class CarPhotoModel {

    private Bitmap bitmap;
    //分享显示图片的url，非bitmap
    private String imageUrl;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public CarPhotoModel(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public CarPhotoModel(Bitmap bitmap, String imageUrl) {
        this.bitmap = bitmap;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
