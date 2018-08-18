package com.example.com.careasysell.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.options.contract.ICarSell;
import com.example.com.careasysell.options.model.CarsModel;
import com.example.com.careasysell.options.model.response.CarsResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.NotifyCallBackManager;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
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
    private String carBrand, bindId;
    private ICarSell.IPagerClose iPagerClose;
    private String token;
    private BaseAdapter baseAdapter;
    private String paramsString;

    @Override
    public int bindLayout() {
        return R.layout.activity_choose_cars;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        carBrand = params.getString("carBrand");
        bindId = params.getString("brandId");
        paramsString = params.getString("params");
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        tvCarBrand.setText(carBrand);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlCars.setLayoutManager(layoutManager);
        rlCars.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        baseAdapter = new BaseAdapter(carLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                CarsModel model = (CarsModel) data;
                String carsName = model.getCarsName();
                String audiId = model.getCarsId();
                String carCombinate = carBrand + "|" + carsName;
                if ("filter".equals(paramsString)) {
                    Intent intent = new Intent();
                    intent.putExtra("carCombinate", carCombinate);
                    intent.putExtra("audiId", audiId);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("carCombinate", carCombinate);
                    bundle.putString("audiId", audiId);
                    startActivity(ChooseModelActivity.class, bundle);
                }
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlCars.setAdapter(baseAdapter);
        getCars();
        NotifyCallBackManager.getInstance().registPagerCloseCallBack(iPagerClose = new ICarSell.IPagerClose() {
            @Override
            public void close() {
                finish();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void getCars() {
        Injection.provideApiService().getCars(token, bindId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarsResponse>() {
                    @Override
                    public void accept(CarsResponse response) throws Exception {
                        try {
                            if (response.getCode() == 200) {
                                for (int i = 0; i < response.getData().size(); i++) {
                                    CarsModel carsModel = new CarsModel(response.getData().get(i).getCarSeries(), response.getData().get(i).getId() + "");
                                    ItemData itemData = new ItemData(0, SettingDelegate.CARS_TYPE, carsModel);
                                    carLists.add(itemData);
                                    baseAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (Exception e) {

                        }

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
