package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerDetailWantModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class CustomerDetailWantHolder extends BaseViewHolder<ItemData> {

    private ImageView mPoster;
    private TextView mTitle;
    private TextView mMessage;
    private TextView mState;
    private TextView mPrice;
    private TextView mDeduct;
    private TextView mTime;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CustomerDetailWantHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mPoster = ((ImageView) itemView.findViewById(R.id.img_poster));
        mTitle = ((TextView) itemView.findViewById(R.id.tv_title));
        mMessage = ((TextView) itemView.findViewById(R.id.tv_message));
        mState = ((TextView) itemView.findViewById(R.id.tv_state));
        mPrice = ((TextView) itemView.findViewById(R.id.tv_price));
        mDeduct = ((TextView) itemView.findViewById(R.id.tv_deduct));
        mTime = ((TextView) itemView.findViewById(R.id.tv_time));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        CustomerDetailWantModel data1 = (CustomerDetailWantModel) data.getData();
        mTime.setText(data1.getTime());
        mTitle.setText(data1.getTitle());
        mMessage.setText(data1.getMessage());
        mPrice.setText(data1.getPrice());
        mDeduct.setText(data1.getDeduct());
        mState.setText(data1.getState());
    }
}
