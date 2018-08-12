package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.common.BaseActivity;

import butterknife.OnClick;

public class CreateNewCustomerActivity extends BaseActivity {
    @Override
    public int bindLayout() {
        return R.layout.activity_create_new_customer;
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

    @OnClick({R.id.ll_contacts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_contacts:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.PICK");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setType("vnd.android.cursor.dir/phone_v2");
                startActivity(intent);
                break;
            default:
        }
    }
}
