package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.CustomerDetailWantModel;
import com.example.com.careasysell.dealer.ui.model.CustomerFollowModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomerDetailActivity extends BaseActivity {

    @BindView(R.id.recycler_want_car)
    RecyclerView mRecyclerWantCar;

    @BindView(R.id.recycler_follow_list)
    RecyclerView mRecyclerFollow;

    private static final String TAG = "CustomerDetailActivity";
    private List<ItemData> mData = new ArrayList<>();
    private List<ItemData> mFollowData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_customer_detail;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecyclerWantCar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerWantCar.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerWantCar.setNestedScrollingEnabled(false);

        mRecyclerFollow.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerFollow.setNestedScrollingEnabled(false);
    }

    @Override
    public void doBusiness(Context mContext) {
        for (int i = 0; i < 3; i++) {
            CustomerDetailWantModel data = new CustomerDetailWantModel();
            data.setDeduct("销售提成2000元   ");
            data.setMessage("车源商名称 | 地区 | 全省");
            data.setPrice("16.8万");
            data.setState("在售");
            data.setTime("今天");
            data.setTitle("雪弗兰2013款 科鲁兹 1.6LSL天地版MT");
            mData.add(new ItemData(0, SettingDelegate.CUSTOMER_DETAIL_WANT_TYPE, data));
        }
        BaseAdapter adapter = new BaseAdapter(mData, new SettingDelegate());
        mRecyclerWantCar.setAdapter(adapter);

        for (int i = 0; i < 4; i++) {
            CustomerFollowModel data = new CustomerFollowModel();
            data.setDate("2018/07/12");
            data.setTime("16:24");
            data.setMessage("短信通知");
            data.setFrom("系统");
            mFollowData.add(new ItemData(0, SettingDelegate.CUSTOMER_DETAIL_FOLLOW_TYPE, data));
        }
        mRecyclerFollow.setAdapter(new BaseAdapter(mFollowData, new SettingDelegate()));
    }

    @OnClick({R.id.tv_message_edit, R.id.tv_customer_need_edit, R.id.tv_want_car_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_message_edit:
                startActivity(EditCustomerMessageActivity.class);
                break;
            case R.id.tv_customer_need_edit:
                startActivity(EditCustomerNeedActivity.class);
                break;
            case R.id.tv_want_car_edit:
                startActivity(CustomerCarWantActivity.class);
                break;

        }
    }


    public void getCustomerInfo() {

    }
}
