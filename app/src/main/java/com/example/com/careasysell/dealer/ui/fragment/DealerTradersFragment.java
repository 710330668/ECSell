package com.example.com.careasysell.dealer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.activity.AllSourceSearchActivity;
import com.example.com.careasysell.dealer.ui.activity.MyReportActivity;
import com.example.com.careasysell.dealer.ui.activity.NationSourceActivity;
import com.example.com.careasysell.dealer.ui.activity.StoreManagerActivity;
import com.example.com.careasysell.dealer.ui.activity.UserManagerActivity;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.common.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author ： DasonYu
 * Date ： 2018/7/26
 * Email Address : 15764240573@163.com
 */

public class DealerTradersFragment extends BaseFragment {
    Unbinder unbinder;

    public int INVENTORY = C.INVENTORY_DEALER;


    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_dealer_traders;
    }

    @Override
    public void setView(View rootView) {

    }

    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void unVisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_country_options, R.id.rl_store_manager, R.id.rl_human_manager, R.id.rl_client_manager, R.id.rl_order_manager, R.id.rl_report,R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_country_options:
                //全国车源
                startActivity(NationSourceActivity.class);
                break;
            case R.id.rl_store_manager:
                //库存管理
                ParamManager.getInstance(getActivity()).setChannelType(INVENTORY);
                startActivity(StoreManagerActivity.class);
                break;
            case R.id.rl_human_manager:
                //人员管理
                startActivity(UserManagerActivity.class);
                break;
            case R.id.rl_order_manager:
                //订单管理
                break;
            case R.id.rl_client_manager:
                //客户管理
                break;
            case R.id.rl_report:
                //报表
                startActivity(MyReportActivity.class);
                break;
            case R.id.et_search:
                startActivity(AllSourceSearchActivity.class);
                break;
            default:


        }
    }
}
