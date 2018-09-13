package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.response.FindSuccessCarResponse;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class FindSuccessCarHolder extends BaseViewHolder<ItemData> {

    private TextView mCarName;
    private TextView mCarProvince;
    private TextView mCarPrice;
    private ImageView mCarChecked;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public FindSuccessCarHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mCarName = ((TextView) itemView.findViewById(R.id.tv_car_name));
        mCarProvince = ((TextView) itemView.findViewById(R.id.tv_province_name));
        mCarPrice = ((TextView) itemView.findViewById(R.id.tv_latest_follow_price));
        mCarChecked = ((ImageView) itemView.findViewById(R.id.img_checked));
    }

    @Override
    public void onBindViewHolder(ItemData data, int position) {
        FindSuccessCarResponse.DataBean data1 = (FindSuccessCarResponse.DataBean) data.getData();
        mCarName.setText(data1.getVname());
        mCarProvince.setText(data1.getProvinceName());
        mCarPrice.setText("成交价" + data1.getOrderPrice() + "万");
        mCarChecked.setImageResource(data1.isChecked() ? R.mipmap.checked_true : R.drawable.bg_cb_put_away_normal);
    }
}
