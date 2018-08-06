package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.com.careasysell.R;
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

    @OnClick({R.id.iv_back, R.id.rl_new, R.id.rl_store,R.id.rl_shouchu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_new:
                startActivity(ClientListActivity.class);
                break;
            case R.id.rl_store:
                break;
            case R.id.rl_shouchu:
                startActivity(ReportCarListActivity.class);
                break;
        }
    }
}
