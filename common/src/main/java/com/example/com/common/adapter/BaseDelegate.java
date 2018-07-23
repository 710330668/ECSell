package com.example.com.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 71033 on 2017/10/17.
 * 只负责viewHolder的创建viewHolder工作
 */

public abstract class BaseDelegate<T> {

    /**
     * @param parent
     * @param viewType
     * @return
     */
    public abstract BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * @param data
     * @return
     */
    public abstract int getItemViewType(T data);

    /**
     * @param viewType
     * @return
     */
    public abstract int getLayoutId(int viewType);

    /**
     * @param parent
     * @param viewType
     * @return
     */
    public View getItemView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
    }
}