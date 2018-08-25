package com.example.com.common.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.com.common.R;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/8/8.
 */
public class RefreshFooterViewHolder extends BaseViewHolder<ItemData> {

    ProgressBar pbLoading;
    TextView tvLoading;
    LinearLayout llEnd;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public RefreshFooterViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        pbLoading = (ProgressBar) itemView.findViewById(R.id.pb_loading);
        tvLoading = (TextView) itemView.findViewById(R.id.tv_loading);
        llEnd = (LinearLayout) itemView.findViewById(R.id.ll_end);
    }

    @Override
    public void onBindViewHolder(ItemData data,int position) {
    }

    public void changeStatus(int v1,int v2, int v3){
        pbLoading.setVisibility(v1);
        tvLoading.setVisibility(v2);
        llEnd.setVisibility(v3);
    }
}