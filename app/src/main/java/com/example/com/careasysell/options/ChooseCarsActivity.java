package com.example.com.careasysell.options;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.contract.ICarSell;
import com.example.com.careasysell.options.model.CarsModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.NotifyCallBackManager;
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
public class ChooseCarsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_cars)
    RecyclerView rlCars;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_car_brand)
    TextView tvCarBrand;
    private List<ItemData> carLists = new ArrayList<>();
    private String carBrand;
    private ICarSell.IPagerClose iPagerClose;

    @Override
    public int bindLayout() {
        return R.layout.activity_choose_cars;
    }

    @Override
    public void initParams(Bundle params) {
        carBrand = params.getString("carBrand");
        for (int i = 0; i < 5; i++) {
            CarsModel carsModel = new CarsModel("卡宴");
            ItemData itemData = new ItemData(0, SettingDelegate.CARS_TYPE, carsModel);
            carLists.add(itemData);
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        tvCarBrand.setText(carBrand);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlCars.setLayoutManager(layoutManager);
        rlCars.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        BaseAdapter baseAdapter = new BaseAdapter(carLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                CarsModel model = (CarsModel) data;
                String carsName = model.getCarsName();
                String carCombinate = carBrand + "|" + carsName;
                Bundle bundle = new Bundle();
                bundle.putString("carCombinate",carCombinate);
                startActivity(ChooseModelActivity.class,bundle);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlCars.setAdapter(baseAdapter);
        NotifyCallBackManager.getInstance().registPagerCloseCallBack(iPagerClose = new ICarSell.IPagerClose() {
            @Override
            public void close() {
                NotifyCallBackManager.getInstance().removeCloseCallBack(iPagerClose);
                finish();
            }
        });
    }


    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
