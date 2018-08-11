package com.example.com.careasysell.dealer.ui.viewHolder;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.CustomerModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class CustomerViewHolder extends BaseViewHolder<ItemData> {

    private TextView mName;
    private TextView mState;
    private TextView mNeed;
    private TextView mFollow;
    private TextView mMessage;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CustomerViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mName = ((TextView) itemView.findViewById(R.id.tv_customer_name));
        mState = ((TextView) itemView.findViewById(R.id.tv_customer_state));
        mNeed = ((TextView) itemView.findViewById(R.id.tv_buy_need));
        mFollow = ((TextView) itemView.findViewById(R.id.tv_latest_follow));
        mMessage = ((TextView) itemView.findViewById(R.id.tv_message));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        CustomerModel data1 = (CustomerModel) data.getData();
        mName.setText(data1.getName());
        mNeed.setText(data1.getNeed());
        mFollow.setText(data1.getFollowTime());
        mMessage.setText(data1.getMessage());
        switch (data1.getState()) {
            case 0:
                mState.setBackgroundColor(Color.parseColor("#FF5755"));
                break;
            case 1:
                mState.setBackgroundColor(Color.parseColor("#3E404F"));
                break;
        }
    }
}
