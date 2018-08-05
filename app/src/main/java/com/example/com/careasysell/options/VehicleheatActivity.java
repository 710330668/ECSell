package com.example.com.careasysell.options;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.model.VehicleHeatModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class VehicleheatActivity extends BaseActivity {

    @BindView(R.id.recycler_vehicle_heat)
    RecyclerView mRecycler;
    @BindView(R.id.img_back)
    ImageView mTvBack;
    @BindView(R.id.rb_today)
    RadioButton mRbToday;
    @BindView(R.id.rb_the_week)
    RadioButton mRbTheWeek;
    @BindView(R.id.rb_last_week)
    RadioButton mRbLastWeek;
    @BindView(R.id.rb_choose_date)
    RadioButton mRbChooseData;

    private List<ItemData> mData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_vehicleheat;
    }

    @Override
    public void initParams(Bundle params) {
        for (int i = 0; i < 10; i++) {
            VehicleHeatModel data = new VehicleHeatModel();
            data.setBrowsTime("15");
            data.setShareTime("20");
            data.setPutAwayTime("25");
            data.setCarRankings(i + 1);
            data.setCarTitle("雪弗兰2013款 科鲁兹 1.6LSL天地版MT");
            mData.add(new ItemData(0, SettingDelegate.VEHICLE_HEAT_TYPE, data));
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.addItemDecoration(new SpaceItemDecoration(10));
    }

    @Override
    public void doBusiness(Context mContext) {
        BaseAdapter<ItemData> adapter = new BaseAdapter<>(mData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {

            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecycler.setAdapter(adapter);

        mRbToday.setChecked(true);
    }

    @OnClick({R.id.img_back, R.id.rb_choose_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.rb_choose_date:
                break;
            default:
        }
    }

    @OnCheckedChanged({R.id.rb_today, R.id.rb_the_week, R.id.rb_last_week})
    public void onCheckdChanged(RadioButton view,boolean isChecked) {
        switch (view.getId()) {
            case R.id.rb_today:
                refreshData();
                break;
            case R.id.rb_the_week:
                refreshData();
                break;
            case R.id.rb_last_week:
                refreshData();
                break;
            default:
        }
    }

    /**
     * 刷新页面信息
     */
    public void refreshData() {

    }
}
