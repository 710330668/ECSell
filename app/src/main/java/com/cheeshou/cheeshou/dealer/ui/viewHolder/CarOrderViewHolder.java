package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.CarStateModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class CarOrderViewHolder extends BaseViewHolder<ItemData> {

    private TextView mRb;
    private ImageView mImage;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CarOrderViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mRb = ((TextView) itemView.findViewById(R.id.rl_all));
        mImage = ((ImageView) itemView.findViewById(R.id.img_sure));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ItemData data) {
        CarStateModel data1 = (CarStateModel) data.getData();
        mRb.setText(data1.getStateName());
        if (data1.isSelected()) {
            mRb.setTextColor(Color.parseColor("#FF5754"));
            mImage.setVisibility(View.VISIBLE);
        } else {
            mRb.setTextColor(Color.parseColor("#333333"));
            mImage.setVisibility(View.GONE);
        }
    }
}
