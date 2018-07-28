package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.com.careasysell.R;
import com.example.com.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 库存管理页面
 */
public class StoreManagerActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText mEditText;

    @Override
    public int bindLayout() {
        return R.layout.activity_inventory_manage;
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


    @OnClick(R.id.et_search)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                startActivity(StoreSearchActivity.class);
                break;
            default:
        }
    }
}
