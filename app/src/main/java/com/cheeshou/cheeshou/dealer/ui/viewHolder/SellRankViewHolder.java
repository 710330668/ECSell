package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.SellRankModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.example.com.imageloader.LoaderManager;

/**
 * Created by 71033 on 2018/8/8.
 */
public class SellRankViewHolder extends BaseViewHolder<ItemData> {

    private ImageView ivHead;
    private TextView tvName;
    private TextView tvClient;
    private TextView tvDaodian;
    private TextView tvChengjiao;
    private TextView tvCall;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public SellRankViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        ivHead = itemView.findViewById(R.id.iv_head);
        tvName = itemView.findViewById(R.id.tv_name);
        tvClient = itemView.findViewById(R.id.tv_client);
        tvDaodian = itemView.findViewById(R.id.tv_daodian);
        tvChengjiao = itemView.findViewById(R.id.tv_chengjiao);
        tvCall = itemView.findViewById(R.id.tv_call);
    }

    @Override
    public void onBindViewHolder(ItemData data,int position) {
        SellRankModel model = (SellRankModel) data.data;
        tvName.setText(model.getName());
        tvClient.setText(model.getClientNum());
        tvDaodian.setText(model.getDaodian());
        tvChengjiao.setText(model.getChengjiao());
        tvCall.setText(model.getCall());
        LoaderManager.getLoader().loadNet(ivHead,model.getImgStr());
    }
}
