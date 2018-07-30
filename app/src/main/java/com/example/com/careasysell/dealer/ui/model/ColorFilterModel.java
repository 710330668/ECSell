package com.example.com.careasysell.dealer.ui.model;

/**
 * Author ： DasonYu
 * Date ： 2018/7/30
 * Email Address : 15764240573@163.com
 */

public class ColorFilterModel {
    private String text;
    private String color;

    public ColorFilterModel(String text, String color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ColorFilterModel{" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
