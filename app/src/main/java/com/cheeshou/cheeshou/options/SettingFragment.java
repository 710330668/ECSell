package com.cheeshou.cheeshou.options;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cheeshou.cheeshou.MyApplication;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.usercenter.DealershipActivity;
import com.cheeshou.cheeshou.usercenter.ModifyPasswordActivity;
import com.cheeshou.cheeshou.usercenter.UserInforActivity;
import com.cheeshou.cheeshou.usercenter.model.UserInforModel;
import com.cheeshou.cheeshou.utils.DataCleanManager;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.CommonDialog;
import com.cheeshou.cheeshou.view.MyImageView;
import com.example.com.common.BaseFragment;
import com.example.com.common.util.SP;
import com.example.com.imageloader.LoaderManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.lly_option)
    LinearLayout llyOption;
    @BindView(R.id.lly_dealer)
    LinearLayout llyDealer;
    @BindView(R.id.lly_market)
    LinearLayout llyMarket;
    @BindView(R.id.rl_clean_cache)
    RelativeLayout rlCleanCache;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.iv_head)
    MyImageView ivHead;
    private String token;
    private String cacheNum;
    private String userPic;
    private String companyName;

    @C.INVENTORY
    public int INVENTORY;

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
        userPic = SP.getInstance(C.USER_DB, getActivity()).getString(C.USER_PIC);
        companyName = SP.getInstance(C.USER_DB, getActivity()).getString(C.USER_COMPANYNAME);
        INVENTORY = ParamManager.getInstance(getActivity()).getChannelType();
        try {
            cacheNum = DataCleanManager.getTotalCacheSize(MyApplication.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLazyLoad() {
        //获取个人信息
        tvCache.setText(cacheNum);
        tvCompany.setText(companyName);
        LoaderManager.getLoader().loadNet(ivHead,userPic);
        getUserInfor();
        switch (INVENTORY) {
            case C.INVENTORY_OPTION:
                llyOption.setVisibility(View.VISIBLE);
                llyDealer.setVisibility(View.GONE);
                llyMarket.setVisibility(View.GONE);
                break;
            case C.INVENTORY_DEALER:
                llyOption.setVisibility(View.GONE);
                llyDealer.setVisibility(View.VISIBLE);
                llyMarket.setVisibility(View.GONE);
                break;
            case C.INVENTORY_MARKET:
                llyOption.setVisibility(View.GONE);
                llyDealer.setVisibility(View.GONE);
                llyMarket.setVisibility(View.VISIBLE);
                break;
        }
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
                                switch (response.getData().getUserType()) {
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

    @OnClick({R.id.iv_car_infor, R.id.rl_dealer, R.id.rl_user_infor, R.id.rl_clean_cache, R.id.btn_logout, R.id.rl_modify_password, R.id.rl_dealer_modify_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_car_infor:
                break;
            case R.id.rl_dealer:
                startActivity(DealershipActivity.class);
                break;
            case R.id.rl_user_infor:
                startActivity(UserInforActivity.class);
                break;
            case R.id.rl_clean_cache:
                showCacheDialog("清除缓存", "确定清除缓存？", "取消", "确定");
                break;
            case R.id.btn_logout:
                showDialog("退出登录", "确定退出登录？", "取消", "确定");
                break;
            case R.id.rl_modify_password:
                startActivity(ModifyPasswordActivity.class);
                break;
            case R.id.rl_dealer_modify_password:
                startActivity(ModifyPasswordActivity.class);
                break;
        }
    }

    private void showCacheDialog(String title, String content, String cancel, String confirm) {
        final CommonDialog dialog = new CommonDialog(getActivity(), title, content, confirm, cancel);
        dialog.show();
        dialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "缓存清理中,请耐心等待..", Toast.LENGTH_SHORT).show();
                DataCleanManager.cleanInternalCache(MyApplication.getContext());
                Handler mHandler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        tvCache.setText("0MB");
                        Toast.makeText(getActivity(), "缓存已清理完毕", Toast.LENGTH_SHORT).show();
                    }
                };


                mHandler.postDelayed(r, 5000);//延时100毫秒
                dialog.dismiss();
            }

            @Override
            public void doConfirm(String etContent) {

            }

            @Override
            public void doCancel() {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
    }


    private void showDialog(String title, String content, String cancel, String confirm) {
        final CommonDialog dialog = new CommonDialog(getActivity(), title, content, confirm, cancel);
        dialog.show();
        dialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
                startActivity(LoginActivity.class);
                getActivity().finish();
                dialog.dismiss();
            }

            @Override
            public void doConfirm(String etContent) {

            }

            @Override
            public void doCancel() {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
    }
}
