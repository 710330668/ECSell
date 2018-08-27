package com.cheeshou.cheeshou.market.ui.viewholder;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.market.ui.model.ShareRankModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.example.com.imageloader.LoaderManager;

public class ShareRankHolder extends BaseViewHolder<ItemData> {

    private TextView mShareRank;
    private ImageView mShareImg;
    private TextView mShareName;
    private TextView mShareState;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public ShareRankHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mShareRank = ((TextView) itemView.findViewById(R.id.tv_rank));
        mShareImg = ((ImageView) itemView.findViewById(R.id.img_poster));
        mShareName = ((TextView) itemView.findViewById(R.id.tv_name));
        mShareState = ((TextView) itemView.findViewById(R.id.tv_share_rank_state));
    }

    @Override
    public void onBindViewHolder(ItemData data, int position) {
        ShareRankModel data1 = (ShareRankModel) data.getData();
        switch (position) {
            case 0:
                mShareRank.setBackgroundResource(R.drawable.bg_vehicle_rankings_firstt);
                mShareRank.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 1:
                mShareRank.setBackgroundResource(R.drawable.bg_vehicle_rankings_secod);
                mShareRank.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 2:
                mShareRank.setBackgroundResource(R.drawable.bg_vehicle_rankings_third);
                mShareRank.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            default:
                mShareRank.setBackgroundResource(R.drawable.bg_vehicle_rankings_default);
                mShareRank.setTextColor(Color.parseColor("#333333"));
        }
        LoaderManager.getLoader().loadNet(mShareImg, data1.getImgUrl());
        mShareRank.setText((position + 1) + "");
        mShareName.setText(data1.getName());
        mShareState.setText(mShareState.getContext().getString(R.string.market_share_rank, data1.getShareCount(), data1.getBrowerCount()));

    }
}
