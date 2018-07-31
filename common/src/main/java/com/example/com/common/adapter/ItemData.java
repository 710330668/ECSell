package com.example.com.common.adapter;

/**
 * Created by 71033 on 2017/10/17.
 */

public class ItemData {

    public int tag;

    public int holderType;

    public String itemDesc;

    public Object data;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getHolderType() {
        return holderType;
    }

    public void setHolderType(int holderType) {
        this.holderType = holderType;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

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