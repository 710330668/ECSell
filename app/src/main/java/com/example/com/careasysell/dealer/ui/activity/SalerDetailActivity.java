package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.response.XsUserDetailResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.TimeUtils;
import com.example.com.imageloader.LoaderManager;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/6.
 */
public class SalerDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.iv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private String token;
    private String userId;
    public XsUserDetailResponse xsUserDetailResponse;

    @Override
    public int bindLayout() {
        return R.layout.activity_saler_detail;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        userId = params.getString("userId");
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        getUserDetail();
    }

    @SuppressLint("CheckResult")
    private void getUserDetail() {
        Injection.provideApiService().getXsUserInfoByUserId(token, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XsUserDetailResponse>() {
                    @Override
                    public void accept(XsUserDetailResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            xsUserDetailResponse = response;
                            tvAccount.setText(response.getData().getAccount());
                            tvName.setText(response.getData().getUserName());
                            tvPhone.setText(response.getData().getPhone());
                            tvTime.setText(TimeUtils.millis2String(response.getData().getCreateDate()));
                            LoaderManager.getLoader().loadNet(ivHead,response.getData().getUserPic());
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

    @OnClick({R.id.iv_back, R.id.tv_modify_infor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_modify_infor:
                Bundle bundle = new Bundle();
                bundle.putString("userId",userId);
                bundle.putSerializable("response", (Serializable) xsUserDetailResponse);
                startActivity(ModifySalerInforActivity.class,bundle);
                break;
        }
    }

}
