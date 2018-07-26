package com.example.com.careasysell.options;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.model.CarsSeriesModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/7/26.
 */
public class ChooseModelActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_car_model)
    TextView tvCarModel;
    @BindView(R.id.rl_cars_model)
    RecyclerView rlCarsModel;

    private String carCombinate;
    private List<ItemData> carSeriesLists = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_choose_model;
    }

    @Override
    public void initParams(Bundle params) {
        carCombinate = params.getString("carCombinate");
        for (int i = 0; i < 5; i++) {
            CarsSeriesModel carsModel = new CarsSeriesModel("卡宴 18款 3.0T SE 混合 铂金");
            ItemData itemData = new ItemData(0, SettingDelegate.SERIES_TYPE, carsModel);
            carSeriesLists.add(itemData);
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        tvCarModel.setText(carCombinate);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlCarsModel.setLayoutManager(layoutManager);
        BaseAdapter baseAdapter = new BaseAdapter(carSeriesLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlCarsModel.setAdapter(baseAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
