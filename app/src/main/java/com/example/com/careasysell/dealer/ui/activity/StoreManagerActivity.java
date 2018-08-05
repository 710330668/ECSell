package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.response.StoreManagerResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 库存管理页面
 * 3条业务线 共用
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

    @C.INVENTORY
    public int INVENTORY = C.INVENTORY_OPTION;
    @BindView(R.id.ll_in_sale)
    LinearLayout llInSale;

    private String token;

    @Override
    public int bindLayout() {
        return R.layout.activity_inventory_manage;
    }

    @Override
    public void initParams(Bundle params) {
        INVENTORY = ParamManager.getInstance(this).getChannelType();
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context mContext) {
        Injection.provideApiService().getInventoryList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StoreManagerResponse>() {
                    @Override
                    public void accept(StoreManagerResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){

                        }
                    }
                });
        switch (INVENTORY) {
            case C.INVENTORY_OPTION:
                llInSale.setVisibility(View.VISIBLE);
                break;
            case C.INVENTORY_DEALER:
                llInSale.setVisibility(View.GONE);
                break;
            case C.INVENTORY_MARKET:
                llInSale.setVisibility(View.GONE);
                break;
        }
    }


    @OnClick({R.id.et_search, R.id.ll_in_sale, R.id.ll_has_put_away, R.id.ll_has_reserve, R.id.ll_has_sell})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.et_search:
                if (INVENTORY != C.INVENTORY_MARKET) {
                    startActivity(StoreSearchActivity.class);
                }else {
                    startActivity(NationSourceActivity.class);
                }
                break;
            case R.id.ll_in_sale:
                bundle.putString(C.TAG_PAGE_STORE_MANAGER, C.TAG_STATE_PUT_AWAY);
                startActivity(StoreSearchActivity.class, bundle);
                break;
            case R.id.ll_has_put_away:
                bundle.putString(C.TAG_PAGE_STORE_MANAGER, C.TAG_STATE_PUT_AWAY);
                startActivity(StoreSearchActivity.class, bundle);
                break;
            case R.id.ll_has_reserve:
                bundle.putString(C.TAG_PAGE_STORE_MANAGER, C.TAG_STATE_RESERVE);
                startActivity(StoreSearchActivity.class, bundle);
                break;
            case R.id.ll_has_sell:
                bundle.putString(C.TAG_PAGE_STORE_MANAGER, C.TAG_STATE_SELL);
                startActivity(StoreSearchActivity.class, bundle);
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
