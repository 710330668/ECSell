package com.cheeshou.cheeshou.options.model;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 71033 on 2018/8/4.
 */
public class CarPhotoModel implements Parcelable {


    private Bitmap bitmap;
    //分享显示图片的url，非bitmap
    private String imageUrl;


    protected CarPhotoModel(Parcel in) {
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        imageUrl = in.readString();
    }

    public static final Creator<CarPhotoModel> CREATOR = new Creator<CarPhotoModel>() {
        @Override
        public CarPhotoModel createFromParcel(Parcel in) {
            return new CarPhotoModel(in);
        }

        @Override
        public CarPhotoModel[] newArray(int size) {
            return new CarPhotoModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bitmap, flags);
        dest.writeString(imageUrl);
    }
}
