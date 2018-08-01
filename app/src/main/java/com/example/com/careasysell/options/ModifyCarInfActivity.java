package com.example.com.careasysell.options;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/8/1.
 */
public class ModifyCarInfActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_modify_car;
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

    @OnClick({R.id.iv_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                break;
        }
    }
}
