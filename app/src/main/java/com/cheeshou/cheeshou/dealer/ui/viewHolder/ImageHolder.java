package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerDetailResponse;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.example.com.imageloader.LoaderManager;

public class ImageHolder extends BaseViewHolder<ItemData> {

    private ImageView viewById;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public ImageHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        viewById = ((ImageView) itemView.findViewById(R.id.img_poster));
    }

    @Override
    public void onBindViewHolder(ItemData data, int position) {
        CustomerDetailResponse.DataBean.ImageBean data1 = (CustomerDetailResponse.DataBean.ImageBean) data.getData();
        LoaderManager.getLoader().loadNet(viewById, data1.getImgUrl());
    }
}
