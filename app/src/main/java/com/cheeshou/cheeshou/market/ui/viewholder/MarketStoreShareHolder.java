package com.cheeshou.cheeshou.market.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cheeshou.cheeshou.R;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.example.com.imageloader.LoaderManager;

public class MarketStoreShareHolder extends BaseViewHolder<ItemData> {

    private ImageView mImagePoster;
    private ImageView mImageChecked;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public MarketStoreShareHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mImagePoster = ((ImageView) itemView.findViewById(R.id.image_poster));
        mImageChecked = ((ImageView) itemView.findViewById(R.id.img_check_true));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        String data1 = (String) data.getData();
        LoaderManager.getLoader().loadNet(mImagePoster, data1);
    }
}
