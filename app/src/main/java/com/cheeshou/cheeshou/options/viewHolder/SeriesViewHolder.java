package com.cheeshou.cheeshou.options.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.options.model.CarsSeriesModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/7/26.
 */
public class SeriesViewHolder extends BaseViewHolder {

    private TextView tvSeriesType;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public SeriesViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        tvSeriesType = itemView.findViewById(R.id.tv_cars_series);
    }

    @Override
    public void onBindViewHolder(Object data) {
        ItemData itemData = (ItemData) data;
        CarsSeriesModel model = (CarsSeriesModel) itemData.data;
        tvSeriesType.setText(model.getSeriesName());
    }

}
