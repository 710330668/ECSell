package com.example.com.careasysell.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.PutCarModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

import org.w3c.dom.Text;

/**
 * Author ： DasonYu
 * Date ： 2018/7/31
 * Email Address : 15764240573@163.com
 */

public class PutAwayHolder extends BaseViewHolder<ItemData> {

    private ImageView mCarPoster;
    private TextView mCarTitle;
    private TextView mCarPrice;
    private TextView mCarPriceSuggestion;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public PutAwayHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mCarPoster = itemView.findViewById(R.id.iv_car_poster);
        mCarTitle = itemView.findViewById(R.id.tv_car_title);
        mCarPrice = itemView.findViewById(R.id.tv_car_price);
        mCarPriceSuggestion = itemView.findViewById(R.id.tv_car_price_suggestion);
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        PutCarModel data1 = (PutCarModel) data.getData();
        mCarTitle.setText(data1.getTitle());
        mCarPrice.setText(data1.getPrice());
        mCarPriceSuggestion.setText(data1.getPriceSuggestion());
    }
}
