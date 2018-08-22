package com.cheeshou.cheeshou.market.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import com.cheeshou.cheeshou.market.ui.model.ShareRankModel;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.market.ui.model.ShareRankModel;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class MarketShareRankingsActivity extends BaseActivity {


    @BindView(R.id.rb_share_rank_today)
    RadioButton mRbToday;
    @BindView(R.id.rb_share_rank_the_week)
    RadioButton mRbTheWeek;
    @BindView(R.id.rb_share_rank_last_week)
    RadioButton mRbLaseWeek;
    @BindView(R.id.rb_share_rank_more)
    RadioButton mRbMorel;
    @BindView(R.id.recycler_share_rank)
    RecyclerView mRecycler;

    private List<ItemData> mData = new ArrayList<>();


    @Override
    public int bindLayout() {
        return R.layout.activity_market_share_rankings;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.addItemDecoration(new SpaceItemDecoration(2));
    }

    @Override
    public void doBusiness(Context mContext) {
        for (int i = 0; i < 15; i++) {
            ShareRankModel data = new ShareRankModel();
            data.setName("张达");
            data.setRank(i + 1);
            data.setShareCount(15 + "");
            data.setBrowerCount(20 + "");
            ItemData e = new ItemData(0, SettingDelegate.SHARE_RANK_TYPE, data);
            mData.add(e);
        }

        mRecycler.setAdapter(new BaseAdapter(mData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {

            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        }));

    }


    @OnCheckedChanged({R.id.rb_share_rank_today, R.id.rb_share_rank_the_week, R.id.rb_share_rank_last_week})
    public void onViewCheckChanged(RadioButton view, boolean isChecked) {
        if (isChecked) {
            switch (view.getId()) {
                case R.id.rb_share_rank_today:
                    break;
                case R.id.rb_share_rank_the_week:
                    break;
                case R.id.rb_share_rank_last_week:
                    break;
            }
        }
    }
}
