package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/8/6.
 */
public class MyReportActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_my_report;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.rl_day_new, R.id.rl_day_store,R.id.rl_day_shouchu,R.id.rl_store_report,R.id.rl_month_shouchu,R.id.rl_month_new,R.id.rl_month_store})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_day_new:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_DAY_NEW);
                startActivity(ClientListActivity.class,bundle);
                break;
            case R.id.rl_day_store:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_DAY_SHOP);
                startActivity(ClientListActivity.class,bundle);
                break;
            case R.id.rl_month_new:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_MONTH_NEW);
                startActivity(ClientListActivity.class,bundle);
                break;
            case R.id.rl_month_store:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_MONTH_SHOP);
                startActivity(ClientListActivity.class,bundle);
                break;
            case R.id.rl_day_shouchu:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_DAY_SELL);
                startActivity(ReportCarListActivity.class,bundle);
                break;
            case R.id.rl_store_report:
                startActivity(StoreReportActivity.class);
                break;
            case R.id.rl_month_shouchu:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_MONTH_SELL);
                startActivity(ReportCarListActivity.class,bundle);
                break;
        }
    }
}