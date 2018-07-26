package com.example.com.careasysell.remote;

import android.view.ViewGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.viewHolder.CarsViewHolder;
import com.example.com.careasysell.options.viewHolder.ColorViewHolder;
import com.example.com.careasysell.options.viewHolder.OptionTypeViewHolder;
import com.example.com.careasysell.options.viewHolder.SeriesViewHolder;
import com.example.com.common.adapter.BaseDelegate;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/7/26.
 */

public class SettingDelegate extends BaseDelegate<ItemData> {

    public static final int OPTION_TYPE = 0;
    public static final int COLOR_TYPE = 1;
    public static final int CARS_TYPE = 2;
    public static final int SERIES_TYPE = 3;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case OPTION_TYPE:
                return new OptionTypeViewHolder(parent, getItemView(parent, viewType));
            case COLOR_TYPE:
                return new ColorViewHolder(parent, getItemView(parent, viewType));
            case CARS_TYPE:
                return new CarsViewHolder(parent, getItemView(parent, viewType));
            case SERIES_TYPE:
                return new SeriesViewHolder(parent, getItemView(parent, viewType));
        }
        return null;
    }

    @Override
    public int getItemViewType(ItemData data) {
        return data.holderType;
    }

    @Override
    public int getLayoutId(int viewType) {
        switch (viewType) {
            case OPTION_TYPE:
                return R.layout.item_options_type;
            case COLOR_TYPE:
                return R.layout.item_colors_type;
            case CARS_TYPE:
                return R.layout.item_cars_type;
            case SERIES_TYPE:
                return R.layout.item_cars_series;
            default:
        }
        return 0;
    }
}