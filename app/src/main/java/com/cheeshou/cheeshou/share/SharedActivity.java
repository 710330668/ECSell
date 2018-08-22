package com.cheeshou.cheeshou.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cheeshou.cheeshou.options.CarDetailActivity;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.share.model.CarShareModel;
import com.cheeshou.cheeshou.share.model.HumanShareModel;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SharedActivity extends BaseActivity {

    @BindView(R.id.recycler_shared_car)
    RecyclerView mRecyclerShared;

    private List<ItemData> mData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_shared;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecyclerShared.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void doBusiness(Context mContext) {
        initData();
    }

    private void initData() {
        HumanShareModel data = new HumanShareModel();
        data.setJobPosition("销售总监");
        data.setCompany("山东易购汽车销售服务公司");
        data.setName("朱江");
        ItemData itemData = new ItemData(0, SettingDelegate.SHARED_HUMAN_TYPE, data);
        mData.add(itemData);
        for (int i = 0; i < 3; i++) {
            CarShareModel carShareModel = new CarShareModel();
            carShareModel.setCarTitle("上海大众-途观L2018款 330TSI 自动两驱 风尚版");
            carShareModel.setCarPriceSale("20万");
            carShareModel.setCarCostSave("降价6.10万");
            carShareModel.setCarPriceOriginal("26.10万");
            ItemData e = new ItemData(0, SettingDelegate.SHARED_CAR_TYPE, carShareModel);
            mData.add(e);
        }

        BaseAdapter adapter = new BaseAdapter(mData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                if (data instanceof CarShareModel) {
                    startActivity(CarDetailActivity.class);
                }
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecyclerShared.setAdapter(adapter);
    }


    @OnClick({R.id.ll_call, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_call:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "11111111111"));//跳转到拨号界面，同时传递电话号码
                startActivity(intent);
                break;
            case R.id.iv_back:
                this.finish();
                break;
            default:
        }
    }
}
