package com.example.com.careasysell.market.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.market.ui.model.MarketShareHeaderModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class MarketShareHeaderHolder extends BaseViewHolder<ItemData> {

    private TextView mHumanName;
    private TextView mCompanyName;
    private TextView mShareRankings;
    private TextView mShareCount;
    private TextView mBrowerCounts;
    private RelativeLayout mRvShare;

    private ShareRankClickListener listener;


    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public MarketShareHeaderHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mHumanName = ((TextView) itemView.findViewById(R.id.human_name));
        mCompanyName = ((TextView) itemView.findViewById(R.id.company_name));
        mShareRankings = ((TextView) itemView.findViewById(R.id.tv_share_rankings));
        mShareCount = ((TextView) itemView.findViewById(R.id.tv_share_count));
        mBrowerCounts = ((TextView) itemView.findViewById(R.id.tv_brower_count));
        mRvShare = ((RelativeLayout) itemView.findViewById(R.id.rv_share_ranking));

    }

    @Override
    public void onBindViewHolder(ItemData data) {
        MarketShareHeaderModel data1 = (MarketShareHeaderModel) data.getData();
        mHumanName.setText(data1.getHumanNmae());
        mCompanyName.setText(data1.getCompanyName());
        mShareRankings.setText(data1.getShareRankings());
        mShareCount.setText(data1.getShareCount());
        mBrowerCounts.setText(data1.getBrowerCount());

        mRvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onShareClick();
                }
            }
        });
    }

    public interface ShareRankClickListener {
        void onShareClick();
    }

    public void setListener(ShareRankClickListener listener) {
        this.listener = listener;
    }
}
