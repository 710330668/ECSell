package com.example.com.careasysell.dealer.ui.model;

public class PriceModel {
    private String minPrice;
    private String maxPrice;
    private String text;

    public PriceModel(String minPrice, String maxPrice, String text) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.text = text;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
