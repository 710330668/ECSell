package com.cheeshou.cheeshou.usercenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.activity.ModifySalerInforActivity;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.usercenter.model.DealerShipResponse;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.imageloader.LoaderManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/5.
 */
public class DealershipActivity extends BaseActivity {

    @BindView(R.id.iv_car_mark)
    ImageView ivCarMark;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_dealership_name)
    TextView tvDealershipName;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_home)
    TextView tvHome;
    private String token;

    @Override
    public int bindLayout() {
        return R.layout.activity_dealership_infor;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context mContext) {
        Injection.provideApiService().findMyCompanyInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DealerShipResponse>() {
                    @Override
                    public void accept(DealerShipResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            LoaderManager.getLoader().loadNet(ivCarMark,response.getData().getComPic());
                            tvAccountName.setText(response.getData().getAccount());
                            tvDealershipName.setText(response.getData().getComName());
                            tvContact.setText(response.getData().getHeadName());
                            tvPhone.setText(response.getData().getHeadPhone());
                            tvHome.setText(response.getData().getProvinceName()+response.getData().getCityName());
                        }else if(response.getCode() == 402||response.getCode() == 401){
                            //token失效
                            SP.getInstance(C.USER_DB,DealershipActivity.this).put(C.USER_ACCOUNT,"");
                            SP.getInstance(C.USER_DB,DealershipActivity.this).put(C.USER_PASSWORD,"");
                            finishAllActivity();
                            startActivity(LoginActivity.class);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SP.getInstance(C.USER_DB,DealershipActivity.this).put(C.USER_ACCOUNT,"");
                        SP.getInstance(C.USER_DB,DealershipActivity.this).put(C.USER_PASSWORD,"");
                        finishAllActivity();
                        startActivity(LoginActivity.class);
                    }
                });
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
