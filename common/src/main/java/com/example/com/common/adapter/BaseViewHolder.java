package com.example.com.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 71033 on 2017/10/17.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public BaseViewHolder(ViewGroup parent, View view) {
        super(view);
        findViews();
    }

    /**
     * find all views
     */
    public abstract void findViews();

    /**
     * bind view holder
     *
     * @param data
     */
    public abstract void onBindViewHolder(T data);

    /**
     * holder click enable
     *
     * @return
     */
    public boolean enable() {
        return true;
    }

}