package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.response.StoreManagerResponse;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class StoreManagerViewHolder extends BaseViewHolder<ItemData> {

    private TextView mCarStatus;
    private TextView mCarNumber;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public StoreManagerViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mCarStatus = ((TextView) itemView.findViewById(R.id.tv_car_status));
        mCarNumber = ((TextView) itemView.findViewById(R.id.tv_car_number));
    }

    @Override
    public void onBindViewHolder(ItemData data,int position) {
        StoreManagerResponse.DataBean data1 = (StoreManagerResponse.DataBean) data.getData();
        mCarStatus.setText(data1.getCarStatusName());
        mCarNumber.setText(data1.getCarCount() + "辆");
    }
}
