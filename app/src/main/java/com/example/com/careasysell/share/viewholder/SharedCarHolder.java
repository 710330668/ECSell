package com.example.com.careasysell.share.viewholder;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.share.model.CarShareModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Author ： DasonYu
 * Date ： 2018/7/31
 * Email Address : 15764240573@163.com
 */

public class SharedCarHolder extends BaseViewHolder<ItemData> {

    private static final String TAG = "SharedCarHolder";

    private ImageView mCarPoster;
    private TextView mCarTitle;
    private TextView mCarPriceSale;
    private TextView mCarPriceOriginal;
    private TextView mCarCostSave;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public SharedCarHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mCarPoster = ((ImageView) itemView.findViewById(R.id.img_shared_car_poster));
        mCarTitle = ((TextView) itemView.findViewById(R.id.tv_shared_car_title));
        mCarPriceSale = ((TextView) itemView.findViewById(R.id.tv_price_sale));
        mCarPriceOriginal = ((TextView) itemView.findViewById(R.id.tv_price_original));
        mCarCostSave = ((TextView) itemView.findViewById(R.id.tv_cost_save));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        CarShareModel data1 = (CarShareModel) data.getData();
        mCarTitle.setText(data1.getCarTitle());
        mCarPriceSale.setText(data1.getCarPriceSale());
        mCarCostSave.setText(data1.getCarCostSave());
        mCarPriceOriginal.setText(data1.getCarPriceOriginal());
        mCarPriceOriginal.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
