package com.cheeshou.cheeshou.usercenter;

import android.content.Context;
import android.os.Bundle;

import com.cheeshou.cheeshou.R;
import com.example.com.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/8/6.
 */
public class UserInforActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_user_infor;
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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
