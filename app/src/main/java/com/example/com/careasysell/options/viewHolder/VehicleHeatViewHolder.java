package com.example.com.careasysell.options.viewHolder;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.model.VehicleHeatModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class VehicleHeatViewHolder extends BaseViewHolder<ItemData> {

    private TextView mCarRankings;
    private ImageView mCarPoster;
    private TextView mCarTitle;
    private TextView mCarState;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public VehicleHeatViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mCarRankings = ((TextView) itemView.findViewById(R.id.tv_rankings));
        mCarPoster = ((ImageView) itemView.findViewById(R.id.img_car_poster));
        mCarTitle = ((TextView) itemView.findViewById(R.id.tv_car_title));
        mCarState = ((TextView) itemView.findViewById(R.id.tv_car_state));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        VehicleHeatModel data1 = (VehicleHeatModel) data.data;
        mCarRankings.setText(data1.getCarRankings() + "");
        switch (data1.getCarRankings()) {
            case 1:
                mCarRankings.setTextColor(Color.parseColor("#FFFFFF"));
                mCarRankings.setBackgroundResource(R.drawable.bg_vehicle_rankings_firstt);
                break;
            case 2:
                mCarRankings.setTextColor(Color.parseColor("#FFFFFF"));
                mCarRankings.setBackgroundResource(R.drawable.bg_vehicle_rankings_secod);
                break;
            case 3:
                mCarRankings.setTextColor(Color.parseColor("#FFFFFF"));
                mCarRankings.setBackgroundResource(R.drawable.bg_vehicle_rankings_third);
                break;
            default:
                mCarRankings.setTextColor(Color.parseColor("#333333"));
                mCarRankings.setBackgroundResource(R.drawable.bg_vehicle_rankings_default);
        }
        mCarTitle.setText(data1.getCarTitle());
        mCarState.setText(mCarState.getContext().getString(R.string.vehicleheat_item_car_state, data1.getPutAwayTime(), data1.getShareTime(), data1.getShareTime()));
    }
}
