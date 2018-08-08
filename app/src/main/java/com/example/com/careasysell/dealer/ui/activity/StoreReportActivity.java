package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.SellRankModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/8/8.
 */
public class StoreReportActivity extends BaseActivity {

    @BindView(R.id.rl_sell_rank)
    RecyclerView rlSellRank;

    private List<ItemData> sellRankLists = new ArrayList<>();


    @Override
    public int bindLayout() {
        return R.layout.activity_store_report;
    }

    @Override
    public void initParams(Bundle params) {
        for (int i = 0; i < 5; i++) {
            SellRankModel sellRankModel = new SellRankModel("", "王皓", "账号:wanghao", "2017/2/20", "132321231","");
            ItemData itemData = new ItemData(0, SettingDelegate.SELL_RANK_TYPE, sellRankModel);
            sellRankLists.add(itemData);
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlSellRank.setLayoutManager(layoutManager);
        rlSellRank.setNestedScrollingEnabled(false);
        rlSellRank.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        BaseAdapter baseAdapter = new BaseAdapter(sellRankLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                startActivity(SalerDetailActivity.class);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlSellRank.setAdapter(baseAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.rv_top_layout, R.id.rb_today, R.id.rb_the_week, R.id.rb_last_month, R.id.rb_last_year, R.id.rb_last_custom, R.id.rg_radio_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rv_top_layout:
                break;
            case R.id.rb_today:
                break;
            case R.id.rb_the_week:
                break;
            case R.id.rb_last_month:
                break;
            case R.id.rb_last_year:
                break;
            case R.id.rb_last_custom:
                break;
            case R.id.rg_radio_button:
                break;
        }
    }
}
