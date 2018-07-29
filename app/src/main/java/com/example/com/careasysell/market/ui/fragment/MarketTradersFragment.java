package com.example.com.careasysell.market.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.activity.StoreManagerActivity;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.common.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author ： DasonYu
 * Date ： 2018/7/28
 * Email Address : 15764240573@163.com
 */

public class MarketTradersFragment extends BaseFragment {
    Unbinder unbinder;

    public int INVENTORY = C.INVENTORY_MARKET;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_market_traders;
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

    @OnClick({R.id.rl_store_manager, R.id.rl_client_manager, R.id.rl_human_manager, R.id.rl_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_store_manager:
                ParamManager.channelType = INVENTORY;
                startActivity(StoreManagerActivity.class);
                break;
            case R.id.rl_client_manager:
                break;
            case R.id.rl_human_manager:
                break;
            case R.id.rl_report:
                break;
        }
    }
}
