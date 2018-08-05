package com.example.com.careasysell.remote;

import android.util.Log;
import android.view.ViewGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.viewHolder.PutAwayHolder;
import com.example.com.careasysell.dealer.ui.viewHolder.SearchHistoryDeleteViewHolder;
import com.example.com.careasysell.dealer.ui.viewHolder.SearchHistoryViewHolder;
import com.example.com.careasysell.dealer.ui.viewHolder.SearchResultViewHolder;
import com.example.com.careasysell.dealer.ui.viewHolder.StoreManagerViewHolder;
import com.example.com.careasysell.options.viewHolder.AreasViewHolder;
import com.example.com.careasysell.options.viewHolder.CarPhotoViewHolder;
import com.example.com.careasysell.options.viewHolder.CarsViewHolder;
import com.example.com.careasysell.options.viewHolder.ColorViewHolder;
import com.example.com.careasysell.options.viewHolder.FormalityViewHolder;
import com.example.com.careasysell.options.viewHolder.OptionTypeViewHolder;
import com.example.com.careasysell.options.viewHolder.SalesAreaViewHolder;
import com.example.com.careasysell.options.viewHolder.SeriesViewHolder;
import com.example.com.careasysell.options.viewHolder.VehicleHeatViewHolder;
import com.example.com.careasysell.share.viewholder.SharedCarHolder;
import com.example.com.careasysell.share.viewholder.SharedHumanHolder;
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
    public static final int AREAS_TYPE = 4;
    public static final int SALES_AREA_TYPE = 5;
    public static final int FORMALITY_TYPE = 6;
    public static final int SEARCH_RESULT_TYPE = 7;
    public static final int PUT_AWAY_CAR_TYPE = 8;
    public static final int SHARED_CAR_TYPE = 9;
    public static final int SHARED_HUMAN_TYPE = 10;
    public static final int SEARCH_HISTORY_TYPE = 11;
    public static final int DELETE_SEARCH_HISTORY_TYPE = 12;
    public static final int CAR_PHOTO_TYPE = 13;
    public static final int VEHICLE_HEAT_TYPE = 14;

    public static final int STORE_MANAGE_TYPE = 15;

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
            case AREAS_TYPE:
                return new AreasViewHolder(parent, getItemView(parent, viewType));
            case SALES_AREA_TYPE:
                return new SalesAreaViewHolder(parent, getItemView(parent, viewType));
            case FORMALITY_TYPE:
                return new FormalityViewHolder(parent, getItemView(parent, viewType));
            case SEARCH_RESULT_TYPE:
                return new SearchResultViewHolder(parent, getItemView(parent, viewType));
            case PUT_AWAY_CAR_TYPE:
                return new PutAwayHolder(parent, getItemView(parent, viewType));
            case SHARED_CAR_TYPE:
                return new SharedCarHolder(parent, getItemView(parent, viewType));
            case SHARED_HUMAN_TYPE:
                return new SharedHumanHolder(parent, getItemView(parent, viewType));
            case SEARCH_HISTORY_TYPE:
                return new SearchHistoryViewHolder(parent, getItemView(parent, viewType));
            case DELETE_SEARCH_HISTORY_TYPE:
                return new SearchHistoryDeleteViewHolder(parent, getItemView(parent, viewType));
            case CAR_PHOTO_TYPE:
                return new CarPhotoViewHolder(parent, getItemView(parent, viewType));
            case VEHICLE_HEAT_TYPE:
                return new VehicleHeatViewHolder(parent, getItemView(parent, viewType));
            case STORE_MANAGE_TYPE:
                return new StoreManagerViewHolder(parent, getItemView(parent, viewType));
            default:
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
            case AREAS_TYPE:
                return R.layout.item_areas;
            case SALES_AREA_TYPE:
                return R.layout.item_sales_area;
            case FORMALITY_TYPE:
                return R.layout.item_formality;
            case SEARCH_RESULT_TYPE:
                return R.layout.item_search_result;
            case PUT_AWAY_CAR_TYPE:
                return R.layout.item_put_away_car;
            case SHARED_CAR_TYPE:
                return R.layout.item_shared_car;
            case SHARED_HUMAN_TYPE:
                return R.layout.item_shared_human;
            case SEARCH_HISTORY_TYPE:
                return R.layout.item_search_history;
            case DELETE_SEARCH_HISTORY_TYPE:
                return R.layout.item_search_history_header;
            case CAR_PHOTO_TYPE:
                return R.layout.item_car_photo;
            case VEHICLE_HEAT_TYPE:
                return R.layout.item_vehicle_heat;
            case STORE_MANAGE_TYPE:
                return R.layout.item_store_manager;
            default:
        }
        return 0;
    }
}