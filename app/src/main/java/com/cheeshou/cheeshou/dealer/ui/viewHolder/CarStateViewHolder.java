package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.CarStateModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class CarStateViewHolder extends BaseViewHolder<ItemData> {

    private TextView mRb;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CarStateViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mRb = ((TextView) itemView.findViewById(R.id.rl_all));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ItemData data,int position) {
        CarStateModel data1 = (CarStateModel) data.getData();
        mRb.setText(data1.getStateName());
        if (data1.isSelected()) {
            mRb.setBackgroundResource(R.drawable.bg_radiobutton_red);
            mRb.setTextColor(R.color.color_FF5754);
        } else {
            mRb.setBackgroundResource(R.drawable.bg_radiobutton);
            mRb.setTextColor(R.color.color_333333);
        }
    }
}
