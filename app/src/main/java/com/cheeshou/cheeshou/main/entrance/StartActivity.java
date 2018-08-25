package com.cheeshou.cheeshou.main.entrance;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.cheeshou.cheeshou.MainTabActivity;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.share.SharedActivity;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.MainTabActivity;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.share.SharedActivity;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.example.com.common.BaseActivity;

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


    @OnClick({R.id.btn_options_traders, R.id.btn_dealers_traders, R.id.btn_sales_traders, R.id.btn_share})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.btn_options_traders:
                ParamManager.getInstance(this).setChannelType(C.INVENTORY_OPTION);
                startActivity(MainTabActivity.class, bundle);
                finish();
                break;
            case R.id.btn_dealers_traders:
                ParamManager.getInstance(this).setChannelType(C.INVENTORY_DEALER);
                startActivity(MainTabActivity.class, bundle);
                finish();
                break;
            case R.id.btn_sales_traders:
                ParamManager.getInstance(this).setChannelType(C.INVENTORY_MARKET);
                startActivity(MainTabActivity.class, bundle);
                finish();
                break;
            case R.id.btn_share:
                startActivity(SharedActivity.class);
                finish();
                break;
            default:
        }
    }
}
