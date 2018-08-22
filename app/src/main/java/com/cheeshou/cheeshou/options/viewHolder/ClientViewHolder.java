package com.cheeshou.cheeshou.options.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.ClientModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/8/6.
 */
public class ClientViewHolder extends BaseViewHolder {

    private TextView tvName;
    private TextView tvDemand;
    private TextView tvFollow;
    private TextView tvTime;
    private TextView tvStatus;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public ClientViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        tvName = itemView.findViewById(R.id.tv_name);
        tvDemand = itemView.findViewById(R.id.tv_demand);
        tvFollow = itemView.findViewById(R.id.tv_follow);
        tvTime = itemView.findViewById(R.id.tv_time);
        tvStatus = itemView.findViewById(R.id.tv_status);
    }

    @Override
    public void onBindViewHolder(Object data) {
        ItemData itemData = (ItemData) data;
        ClientModel model = (ClientModel) itemData.data;
        tvName.setText(model.getName());
        tvDemand.setText(model.getDemand());
        tvFollow.setText(model.getFollow());
        tvTime.setText(model.getTime());
        tvStatus.setText(model.getStatus());
    }
}
