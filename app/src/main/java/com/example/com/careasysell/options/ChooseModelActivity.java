package com.example.com.careasysell.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.options.model.CarsSeriesModel;
import com.example.com.careasysell.options.model.response.CarsModelResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.NotifyCallBackManager;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.careasysell.view.KeyMapDailog;
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
public class ChooseModelActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_car_model)
    TextView tvCarModel;
    @BindView(R.id.rl_cars_model)
    RecyclerView rlCarsModel;

    private String carCombinate;
    private List<ItemData> carSeriesLists = new ArrayList<>();
    private String carFullName;
    private String audiId;
    private String token;
    private BaseAdapter baseAdapter;
    private InputMethodManager imm;


    @Override
    public int bindLayout() {
        return R.layout.activity_choose_model;
    }

    @Override
    public void initParams(Bundle params) {
        carCombinate = params.getString("carCombinate");
        audiId = params.getString("audiId");
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        tvCarModel.setText(carCombinate);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlCarsModel.setLayoutManager(layoutManager);
        rlCarsModel.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        baseAdapter = new BaseAdapter(carSeriesLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                //全称
                CarsSeriesModel model = (CarsSeriesModel) data;
                carFullName = carCombinate + "|" + model.getSeriesName();
                ParamManager.getInstance(ChooseModelActivity.this).setCarId(model.getId());
                ParamManager.getInstance(ChooseModelActivity.this).setCarFullName(carFullName);
                NotifyCallBackManager.getInstance().onCloseCallBack();
                finish();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlCarsModel.setAdapter(baseAdapter);
        getCarsModel();
    }

    @SuppressLint("CheckResult")
    private void getCarsModel() {
        Injection.provideApiService().getCarsModel(token, audiId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarsModelResponse>() {
                    @Override
                    public void accept(CarsModelResponse response) throws Exception {
                        try {
                            if (response.getCode() == 200) {
                                for (int i = 0; i < response.getData().size(); i++) {
                                    CarsSeriesModel carsModel = new CarsSeriesModel(response.getData().get(i).getVName(), response.getData().get(i).getId() + "");
                                    ItemData itemData = new ItemData(0, SettingDelegate.SERIES_TYPE, carsModel);
                                    carSeriesLists.add(itemData);
                                }
                                baseAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {

                        }

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_zidingyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_zidingyi:
                KeyMapDailog dialog =new KeyMapDailog("自定义车型", new KeyMapDailog.SendBackListener() {
                    @Override
                    public void sendBack(String inputText) {
                        //TODO  点击发表后业务逻辑
                        carFullName = carCombinate + "|" + inputText;
                        ParamManager.getInstance(ChooseModelActivity.this).setCarFullName(carFullName);
                        NotifyCallBackManager.getInstance().onCloseCallBack();
                        finish();
                    }
                });
                dialog.show(getSupportFragmentManager(), "dialog");
                break;
        }
    }
}
