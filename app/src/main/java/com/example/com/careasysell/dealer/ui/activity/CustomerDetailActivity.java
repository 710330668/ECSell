package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.CustomerDetailWantModel;
import com.example.com.careasysell.dealer.ui.model.CustomerFollowModel;
import com.example.com.careasysell.dealer.ui.model.response.CustomerInfoResponse;
import com.example.com.careasysell.dealer.ui.model.response.CustomerResponse;
import com.example.com.careasysell.dealer.ui.model.response.CustomerStatusResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CustomerDetailActivity extends BaseActivity {

    @BindView(R.id.recycler_want_car)
    RecyclerView mRecyclerWantCar;

    @BindView(R.id.recycler_follow_list)
    RecyclerView mRecyclerFollow;

    @BindView(R.id.tv_not_arrive_shop)
    TextView mTvState1;
    @BindView(R.id.tv_arrive_shop)
    TextView mTvState2;
    @BindView(R.id.tv_reserve)
    TextView mTvState3;
    @BindView(R.id.tv_deal)
    TextView mTvState4;

    @BindView(R.id.view_line_1)
    View mLine1;
    @BindView(R.id.view_line_2)
    View mLine2;
    @BindView(R.id.view_line_3)
    View mLine3;
    @BindView(R.id.view_line_4)
    View mLine4;
    @BindView(R.id.view_line_5)
    View mLine5;
    @BindView(R.id.view_line_6)
    View mLine6;


    private static final String TAG = "CustomerDetailActivity";
    private List<ItemData> mData = new ArrayList<>();
    private List<ItemData> mFollowData = new ArrayList<>();
    private String customerId;
    private String token;
    private CustomerResponse.DataBean.ListsBean customer;

    @Override
    public int bindLayout() {
        return R.layout.activity_customer_detail;
    }

    @Override
    public void initParams(Bundle params) {
        customer = (CustomerResponse.DataBean.ListsBean) params.getSerializable("customer");
        customerId = customer.getCustomerId();
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecyclerWantCar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerWantCar.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerWantCar.setNestedScrollingEnabled(false);

        mRecyclerFollow.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerFollow.setNestedScrollingEnabled(false);

        switch (customer.getStatusName()) {
            case "已成交":
                mTvState4.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine6.setBackgroundColor(Color.parseColor("#FF5755"));
            case "预定":
                mTvState3.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine5.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine4.setBackgroundColor(Color.parseColor("#FF5755"));
            case "已到店":
                mTvState2.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine3.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine2.setBackgroundColor(Color.parseColor("#FF5755"));
            case "未到店":
                mTvState1.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine1.setBackgroundColor(Color.parseColor("#FF5755"));
                break;
        }
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
                Bundle bundle = new Bundle();
                bundle.putString("customerId", customerId);
                startActivity(EditCustomerMessageActivity.class, bundle);
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
