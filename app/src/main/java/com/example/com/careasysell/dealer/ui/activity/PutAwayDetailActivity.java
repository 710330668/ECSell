package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.PutCarModel;
import com.example.com.careasysell.options.CarDetailActivity;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PutAwayDetailActivity extends BaseActivity {


    @BindView(R.id.recycler_put_away_car)
    RecyclerView mRecyclerPutCar;
    @BindView(R.id.fm_put_away)
    FrameLayout mFmPutAway;
    private List<ItemData> mCarData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_put_away_detail;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        LinearLayoutManager fullyLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerPutCar.setLayoutManager(fullyLinearLayoutManager);
        mRecyclerPutCar.addItemDecoration(new SpaceItemDecoration(5));
    }

    @Override
    public void doBusiness(Context mContext) {
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            PutCarModel data = new PutCarModel();
            data.setPrice("车源价 16.8万");
            data.setTitle("雪佛兰2013款  科鲁  16LSL天地板MT");
            data.setPriceSuggestion("底价18.8万 丨 建议零售价 20万");
            ItemData e = new ItemData(0, SettingDelegate.PUT_AWAY_CAR_TYPE, data);
            mCarData.add(e);
        }
        BaseAdapter adapter = new BaseAdapter(mCarData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                startActivity(CarDetailActivity.class);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecyclerPutCar.setAdapter(adapter);
    }

    @OnClick(R.id.fm_put_away)
    public void onViewClicked(View view) {
        Toast.makeText(appContext, "上架成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
