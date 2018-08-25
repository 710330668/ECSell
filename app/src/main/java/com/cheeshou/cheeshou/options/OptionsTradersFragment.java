package com.cheeshou.cheeshou.options;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.activity.StoreManagerActivity;
import com.cheeshou.cheeshou.dealer.ui.activity.StoreManagerItemClickActivity;
import com.cheeshou.cheeshou.market.ui.MarketSearchActivity;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.example.com.common.BaseFragment;
import com.example.com.common.util.SP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 71033 on 2018/7/24.
 */
public class OptionsTradersFragment extends BaseFragment {

    Unbinder unbinder;

    public int INVENTORY = ParamManager.getInstance(getContext()).getChannelType();
    @BindView(R.id.tv_company)
    TextView tvCompany;

    private String companyName;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_option_traders;
    }

    @Override
    public void setView(View rootView) {

    }

    @Override
    public void initData(Bundle arguments) {
        companyName = SP.getInstance(C.USER_DB, getActivity()).getString(C.USER_COMPANYNAME);
    }

    @Override
    public void onLazyLoad() {
        tvCompany.setText(companyName);
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

    @OnClick({R.id.rl_kucun_manager, R.id.rl_car_redu, R.id.rl_release_option, R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_kucun_manager:
                //库存管理
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
                startActivity(StoreManagerItemClickActivity.class);
                break;
        }
    }
}
