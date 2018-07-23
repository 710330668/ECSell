package com.example.com.common.adapter;

/**
 * Created by 71033 on 2017/10/17.
 */

public class ItemData {

    public int tag;

    public int holderType;

    public String itemDesc;

    public Object data;

    public ItemData(int tag, int holderType) {
        this.tag = tag;
        this.holderType = holderType;
    }

    public ItemData(int tag, int holderType, String itemDesc) {
        this.tag = tag;
        this.holderType = holderType;
        this.itemDesc = itemDesc;
    }

    public ItemData(int tag, int holderType, Object data) {
        this.tag = tag;
        this.holderType = holderType;
        this.data = data;
    }
}