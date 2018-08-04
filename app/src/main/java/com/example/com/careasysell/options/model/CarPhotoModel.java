package com.example.com.careasysell.options.model;

import android.graphics.Bitmap;

/**
 * Created by 71033 on 2018/8/4.
 */
public class CarPhotoModel {

    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public CarPhotoModel(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
