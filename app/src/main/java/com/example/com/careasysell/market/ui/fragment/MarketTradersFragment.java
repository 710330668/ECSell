package com.example.com.careasysell.market.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.activity.CustomerManagerActivity;
import com.example.com.careasysell.dealer.ui.activity.MyReportActivity;
import com.example.com.careasysell.dealer.ui.activity.StoreManagerActivity;
import com.example.com.careasysell.market.ui.MarketSearchActivity;
import com.example.com.careasysell.market.ui.MarketShareActivity;
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

    @OnClick({R.id.rl_store_manager, R.id.rl_client_manager, R.id.rl_my_share, R.id.rl_report, R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_store_manager:
                ParamManager.getInstance(getActivity()).setChannelType(INVENTORY);
                startActivity(StoreManagerActivity.class);
                break;
            case R.id.rl_client_manager:
                //客户管理
                startActivity(CustomerManagerActivity.class);
                break;
            case R.id.rl_report:
                startActivity(MyReportActivity.class);
                break;
            case R.id.et_search:
                startActivity(MarketSearchActivity.class);
                break;
            case R.id.rl_my_share:
                startActivity(MarketShareActivity.class);
                break;
            default:
        }
    }
}
