package com.example.com.careasysell.market.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.careasysell.market.ui.model.MarketShareHeaderModel;
import com.example.com.careasysell.market.ui.model.MarketShareModel;
import com.example.com.careasysell.market.ui.viewholder.MarketShareHeaderHolder;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MarketShareActivity extends BaseActivity {

    @BindView(R.id.recycler_mine_share)
    RecyclerView mRecycler;

    private static final String TAG = "MarketShareActivity";

    private List<ItemData> mData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_market_share;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.addItemDecoration(new SpaceItemDecoration(5));
    }

    @Override
    public void doBusiness(Context mContext) {
        MarketShareHeaderModel data = new MarketShareHeaderModel();
        data.setBrowerCount("5");
        data.setShareCount("5");
        data.setShareRankings("5");
        data.setCompanyName("山东易购汽车销售服务公司");
        data.setHumanNmae("朱江");
        mData.add(new ItemData(0, SettingDelegate.MARKET_SHARE_HEADER_TYPE, data));

        for (int i = 0; i < 10; i++) {
            MarketShareModel data1 = new MarketShareModel();
            data1.setShareTitle("雪弗兰2013款 科鲁兹 1.6LSL天地版MT");
            data1.setShareCount(5);
            data1.setShareTime("2018-06-04");
            ArrayList<String> imgUrl = new ArrayList<>();
            for (int j = 0; j < 1; j++) {
                imgUrl.add("");
            }
            data1.setImgUrl(imgUrl);
            mData.add(new ItemData(0, SettingDelegate.MARKET_SHARE_TYPE, data1));
        }
        SettingDelegate delegate = new SettingDelegate();
        delegate.setShareHeaderClickListener(new MarketShareHeaderHolder.ShareRankClickListener() {
            @Override
            public void onShareClick() {
                startActivity(MarketShareRankingsActivity.class);
            }
        });
        mRecycler.setAdapter(new BaseAdapter(mData, delegate));
    }

    @OnClick({R.id.img_back})
    public void onViewClicked() {
        this.finish();
    }
}
