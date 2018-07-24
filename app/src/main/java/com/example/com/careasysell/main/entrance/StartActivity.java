package com.example.com.careasysell.main.entrance;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/7/24.
 */
public class StartActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.ac_start;
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

    @OnClick({R.id.btn_options_traders, R.id.btn_dealers_traders, R.id.btn_sales_traders, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_options_traders:
                break;
            case R.id.btn_dealers_traders:
                break;
            case R.id.btn_sales_traders:
                break;
            case R.id.btn_share:
                break;
        }
    }
}
