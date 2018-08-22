package com.cheeshou.cheeshou.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.options.model.response.ModifyCarInforResponse;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.options.model.response.ModifyCarInforResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.ToastUtils;
import com.example.com.imageloader.LoaderManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/1.
 */
public class ModifyCarInfActivity extends BaseActivity {

    @BindView(R.id.et_saleCarPrice)
    EditText etSaleCarPrice;
    @BindView(R.id.et_insuranceRebates)
    EditText etInsuranceRebates;
    @BindView(R.id.et_loanRebates)
    EditText etLoanRebates;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.iv_car_img)
    ImageView ivCarImg;
    @BindView(R.id.tv_car_name)
    TextView tvCarName;
    @BindView(R.id.tv_car_price)
    TextView tvCarPrice;
    @BindView(R.id.tv_suggest_price)
    TextView tvSuggestPrice;
    private String token;
    private String saleId;

    @Override
    public int bindLayout() {
        return R.layout.activity_modify_car;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        saleId = params.getString("saleId");
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context mContext) {
        Injection.provideApiService().findSaleCarInfoBySaleId(token, saleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ModifyCarInforResponse>() {
                    @Override
                    public void accept(ModifyCarInforResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            LoaderManager.getLoader().loadNet(ivCarImg,response.getData().getImgThumUrl());
                            tvCarName.setText(response.getData().getVname());
                            tvCarPrice.setText("车源价"+" "+response.getData().getCarPrice()+"万");
                            tvSuggestPrice.setText("建议售价 "+response.getData().getGuidPrice()+"万");
                            etSaleCarPrice.setText(response.getData().getSaleCarPrice()+"万");
                            etInsuranceRebates.setText(response.getData().getInsuranceRebates()+"%");
                            etLoanRebates.setText(response.getData().getLoanRebates()+"%");
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

    @OnClick({R.id.iv_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                //保存销售车辆信息
                updateSaleCarInfo();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void updateSaleCarInfo() {
        Injection.provideApiService().updateSaleCarInfo(token, saleId, etSaleCarPrice.getText().toString(),
                etInsuranceRebates.getText().toString(), etLoanRebates.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EasyResponse>() {
                    @Override
                    public void accept(EasyResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        ToastUtils.showShort(ModifyCarInfActivity.this,response.getMsg());
                        if(response.getCode()==200){
                            finish();
                        }
                    }
                });
    }
}
