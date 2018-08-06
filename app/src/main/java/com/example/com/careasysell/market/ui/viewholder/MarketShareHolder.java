package com.example.com.careasysell.market.ui.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.market.ui.model.MarketShareModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

import java.util.List;

public class MarketShareHolder extends BaseViewHolder<ItemData> {

    private LinearLayout mShareContent;
    private TextView mShareCount;
    private TextView mShareTime;
    private Context mContext;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public MarketShareHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mShareContent = ((LinearLayout) itemView.findViewById(R.id.share_item_content));
        mShareCount = ((TextView) itemView.findViewById(R.id.share_item_count));
        mShareTime = ((TextView) itemView.findViewById(R.id.share_item_time));
        mContext = mShareContent.getContext();
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        MarketShareModel data1 = (MarketShareModel) data.getData();
        mShareCount.setText(data1.getShareCount() + "人看过");
        mShareTime.setText(data1.getShareTime());
        List<String> imgUrl = data1.getImgUrl();
        switch (imgUrl.size()) {
            case 0:
                addShareTitle(data1.getShareTitle());
                break;
            case 1:
                addSharePoster(imgUrl.get(0));
                addShareTitle(data1.getShareTitle());
                break;
            default:
                for (int i = 0; i < imgUrl.size(); i++) {
                    if (i < 6) {
                        addSharePoster(imgUrl.get(i));
                    } else {
                        return;
                    }
                }
        }
    }

    public void addSharePoster(String imgUrl) {
        ImageView imageView = new ImageView(mShareContent.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mContext.getResources().getDimensionPixelOffset(R.dimen.dimens_48dp), mContext.getResources().getDimensionPixelOffset(R.dimen.dimens_36dp));
        layoutParams.leftMargin = mContext.getResources().getDimensionPixelOffset(R.dimen.dimens_5dp);
        layoutParams.rightMargin = mContext.getResources().getDimensionPixelOffset(R.dimen.dimens_5dp);
        imageView.setImageResource(R.mipmap.ic_launcher);
        mShareContent.addView(imageView, layoutParams);
    }

    public void addShareTitle(String text) {
        TextView textView = new TextView(mShareContent.getContext());
        textView.setLines(1);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#333333"));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = mContext.getResources().getDimensionPixelOffset(R.dimen.dimens_9dp);
        layoutParams.rightMargin = mContext.getResources().getDimensionPixelOffset(R.dimen.dimens_5dp);
        mShareContent.addView(textView, layoutParams);
    }
}
