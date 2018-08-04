package com.example.com.careasysell.options;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.usercenter.model.UserInforModel;
import com.example.com.common.BaseFragment;
import com.example.com.common.util.SP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/7/24.
 */
public class SettingFragment extends BaseFragment {

    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    Unbinder unbinder;
    private String token;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void setView(View rootView) {

    }

    @Override
    public void initData(Bundle arguments) {
        token = SP.getInstance(C.USER_DB, getActivity()).getString(C.USER_TOKEN);
    }

    @Override
    public void onLazyLoad() {
        //获取个人信息
        getUserInfor();
    }


    @SuppressLint("CheckResult")
    private void getUserInfor() {
        Injection.provideApiService().getUserInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInforModel>() {
                    @Override
                    public void accept(UserInforModel response) throws Exception {
                        try {
                            if (response.getCode() == 200) {
                                tvAccount.setText(response.getData().getUserName());
                                switch (response.getData().getUserType()){
                                    case C.USER_TYPE_DTRY:
                                        tvType.setText("地推人员");
                                        break;
                                    case C.USER_TYPE_CYS:
                                        tvType.setText("车源商");
                                        break;
                                    case C.USER_TYPE_JXS:
                                        tvType.setText("经销商");
                                        break;
                                    case C.USER_TYPE_XS:
                                        tvType.setText("销售");
                                        break;
                                }
                            }
                        } catch (Exception e) {

                        }

                    }
                });
    }

    @Override
    public void unVisible() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
