package com.cheeshou.cheeshou.market.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MarketStoreShareActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.recycler_share_img)
    RecyclerView mRecycler;
    @BindView(R.id.tv_go_to_share)
    TextView mGoToShare;

    List<ItemData> mData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_market_store_share;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void doBusiness(Context mContext) {
        for (int i = 0; i < 5; i++) {
            String imageUrl = "";
            mData.add(new ItemData(0, SettingDelegate.STORE_SHARE_GRID_TYPE, imageUrl));
        }

        mRecycler.setAdapter(new BaseAdapter(mData, new SettingDelegate()));
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        this.finish();
    }
}