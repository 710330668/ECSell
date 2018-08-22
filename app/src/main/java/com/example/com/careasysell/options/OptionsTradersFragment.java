package com.example.com.careasysell.options;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.activity.StoreManagerActivity;
import com.example.com.careasysell.market.ui.MarketSearchActivity;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.common.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 71033 on 2018/7/24.
 */
public class OptionsTradersFragment extends BaseFragment {

    Unbinder unbinder;

    public int INVENTORY = C.INVENTORY_OPTION;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_option_traders;
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

    @OnClick({R.id.rl_kucun_manager, R.id.rl_car_redu, R.id.rl_release_option,R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_kucun_manager:
                //库存管理
                ParamManager.getInstance(getActivity()).setChannelType(INVENTORY);
                startActivity(StoreManagerActivity.class);
                break;
            case R.id.rl_car_redu:
                startActivity(VehicleheatActivity.class);
                break;
            case R.id.rl_release_option:
                //发布车源
                startActivity(ReleaseOptionActivity.class);
                break;
            case R.id.et_search:
                startActivity(MarketSearchActivity.class);
                break;
        }
    }
}
