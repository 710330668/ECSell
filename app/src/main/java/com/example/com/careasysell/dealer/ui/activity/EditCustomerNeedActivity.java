
package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.common.BaseActivity;

import butterknife.OnClick;

public class EditCustomerNeedActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_edit_customer_need;
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

    @OnClick({R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            default:
        }
    }
}
