package com.example.com.careasysell.share.model;

/**
 * Author ： DasonYu
 * Date ： 2018/7/31
 * Email Address : 15764240573@163.com
 */

public class CarShareModel {
    private String imageUrl;
    private String carPriceSale;
    private String carPriceOriginal;
    private String carCostSave;
    private String carTitle;

    public String getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCarPriceSale() {
        return carPriceSale;
    }

    public void setCarPriceSale(String carPriceSale) {
        this.carPriceSale = carPriceSale;
    }

    public String getCarPriceOriginal() {
        return carPriceOriginal;
    }

    public void setCarPriceOriginal(String carPriceOriginal) {
        this.carPriceOriginal = carPriceOriginal;
    }

    public String getCarCostSave() {
        return carCostSave;
    }

    public void setCarCostSave(String carCostSave) {
        this.carCostSave = carCostSave;
    }
}
