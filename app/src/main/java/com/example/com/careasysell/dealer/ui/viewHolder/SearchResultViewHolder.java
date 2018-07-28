package com.example.com.careasysell.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Author ： DasonYu
 * Date ： 2018/7/28
 * Email Address : 15764240573@163.com
 */

public class SearchResultViewHolder extends BaseViewHolder<ItemData> {

    private ImageView mCarPoster;
    private TextView mCarTitle;
    private TextView mCarSubTitle;
    private TextView mCarPrice;
    private TextView mCarDeduct;
    private TextView mCarState;
    private TextView mCarDate;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public SearchResultViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mCarPoster = ((ImageView) itemView.findViewById(R.id.img_car_poster));
        mCarTitle = ((TextView) itemView.findViewById(R.id.tv_car_title));
        mCarSubTitle = ((TextView) itemView.findViewById(R.id.tv_car_sub_title));
        mCarPrice = ((TextView) itemView.findViewById(R.id.tv_car_price));
        mCarDeduct = ((TextView) itemView.findViewById(R.id.tv_car_deduct));
        mCarState = ((TextView) itemView.findViewById(R.id.tv_car_state));
        mCarDate = ((TextView) itemView.findViewById(R.id.tv_car_date));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        SearchResultModel resultModel = (SearchResultModel) data.data;
        mCarPoster.setImageResource(R.mipmap.ic_launcher);

        mCarTitle.setText(resultModel.getTitle());
        mCarSubTitle.setText(resultModel.getSubTitle());
        mCarState.setText(resultModel.getState());
        mCarDate.setText(resultModel.getDate());
        mCarPrice.setText(resultModel.getPrice());
        mCarDeduct.setText(resultModel.getDeduct());
    }
}
