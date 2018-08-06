package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/8/6.
 */
public class ReportCarListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_car)
    RecyclerView rlCar;

    private List<ItemData> mSearchResultData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_report_car_list;
    }

    @Override
    public void initParams(Bundle params) {
        for (int i = 0; i < 10; i++) {
            SearchResultModel data = new SearchResultModel();
            data.setDate("2018/06/24");
            data.setDeduct("销售提成2000");
            data.setPrice("16.8万");
            data.setState("已上架");
            data.setSubTitle("分享20次|浏览140次");
            data.setTitle("雪佛兰2013款  科鲁兹  16LSL天地板MT");
            ItemData e = new ItemData(0, SettingDelegate.SEARCH_RESULT_TYPE, data);
            mSearchResultData.add(e);
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        rlCar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rlCar.addItemDecoration(new SpaceItemDecoration(5));
    }

    @Override
    public void doBusiness(Context mContext) {
        BaseAdapter mDataAdapter = new BaseAdapter(mSearchResultData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                startActivity(CarDetailActivity.class);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlCar.setAdapter(mDataAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
