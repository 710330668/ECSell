package com.cheeshou.cheeshou.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.response.StoreManagerResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.recycler_store_manager)
    RecyclerView mRecycler;

    private List<ItemData> mData = new ArrayList<>();

    public int INVENTORY = ParamManager.getInstance(this).getChannelType();

    private static final String TAG = "StoreManagerActivity";

    private String token;
    private BaseAdapter mAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_inventory_manage;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.addItemDecoration(new SpaceItemDecoration(5));

        mAdapter = new BaseAdapter(mData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                StoreManagerResponse.DataBean data1 = (StoreManagerResponse.DataBean) data;
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", data1);
                startActivity(StoreManagerItemClickActivity.class, bundle);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });

        mRecycler.setAdapter(mAdapter);
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
                        if (response.getCode() == 200) {
                            initData(response.getData());
                        } else if (response.getCode() == 402 || response.getCode() == 401) {
                            //token失效
                            SP.getInstance(C.USER_DB, StoreManagerActivity.this).put(C.USER_ACCOUNT, "");
                            SP.getInstance(C.USER_DB, StoreManagerActivity.this).put(C.USER_PASSWORD, "");
                            finishAllActivity();
                            startActivity(LoginActivity.class);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SP.getInstance(C.USER_DB, StoreManagerActivity.this).put(C.USER_ACCOUNT, "");
                        SP.getInstance(C.USER_DB, StoreManagerActivity.this).put(C.USER_PASSWORD, "");
                        finishAllActivity();
                        startActivity(LoginActivity.class);
                    }
                });
    }


    @OnClick({R.id.et_search, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                switch (INVENTORY) {
                    //车源商 UE无操作
                    case C.INVENTORY_OPTION:
                        startActivity(StoreManagerItemClickActivity.class);
                        break;
                    //销售
                    case C.INVENTORY_MARKET:
                        startActivity(StoreManagerItemClickActivity.class);
                        break;
                    //经销商
                    case C.INVENTORY_DEALER:
                        startActivity(StoreManagerItemClickActivity.class);
                        break;
                }
                break;
            case R.id.iv_back:
                finish();
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

    public void initData(List<StoreManagerResponse.DataBean> data) {
        for (StoreManagerResponse.DataBean bean : data) {
            ItemData itemData = new ItemData(0, SettingDelegate.STORE_MANAGE_TYPE, bean);
            mData.add(itemData);
        }
        mAdapter.notifyDataSetChanged();
    }
}
