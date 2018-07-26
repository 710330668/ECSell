package com.example.com.careasysell.options.model;

import com.example.com.careasysell.utils.PinYinUtils;

/**
 * Created by 71033 on 2018/7/26.
 */
public class CarBrandModel {

    //姓名
    private String name;
    //拼音
    private String pinyin;
    //拼音首字母
    private String headerWord;

    public CarBrandModel(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinyin(name);
        headerWord = pinyin.substring(0, 1);
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderWord() {
        return headerWord;
    }
}
