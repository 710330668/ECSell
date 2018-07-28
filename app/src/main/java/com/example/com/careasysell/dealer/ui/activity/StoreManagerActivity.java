package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 库存管理页面
 */
public class StoreManagerActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText mEditText;
    @BindView(R.id.ll_has_put_away)
    LinearLayout mLLHasPutAway;
    @BindView(R.id.ll_has_reserve)
    LinearLayout mLLHasReserve;
    @BindView(R.id.ll_has_sell)
    LinearLayout mLLHasSell;

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


    @OnClick({R.id.et_search,R.id.ll_has_put_away,R.id.ll_has_reserve,R.id.ll_has_sell})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.et_search:
                startActivity(StoreSearchActivity.class);
                break;
            case R.id.ll_has_put_away:
                bundle.putString(C.TAG_PAGE_STORE_MANAGER,C.TAG_STATE_PUT_AWAY);
                startActivity(StoreSearchActivity.class,bundle);
                break;
            case R.id.ll_has_reserve:
                bundle.putString(C.TAG_PAGE_STORE_MANAGER,C.TAG_STATE_RESERVE);
                startActivity(StoreSearchActivity.class,bundle);
                break;
            case R.id.ll_has_sell:
                bundle.putString(C.TAG_PAGE_STORE_MANAGER,C.TAG_STATE_SELL);
                startActivity(StoreSearchActivity.class,bundle);
                break;
            default:
        }
    }
}