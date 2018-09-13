package com.cheeshou.cheeshou.remote;

import android.util.Log;
import android.view.ViewGroup;

import com.cheeshou.cheeshou.dealer.ui.viewHolder.CarOrderViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.CarStateViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.CustomerWantCarViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.FindSuccessCarHolder;
import com.cheeshou.cheeshou.options.viewHolder.AreasViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.CarPhotoViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.CarsViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.ClientViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.ColorViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.FormalityViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.OptionTypeViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.SalesAreaViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.SeriesViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.ShareCarPhotoAddViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.ShareCarPhotoViewHolder;
import com.cheeshou.cheeshou.options.viewHolder.VehicleHeatViewHolder;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.CustomerDetailWantHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.CustomerFollowHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.CustomerViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.PutAwayHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.SearchHistoryDeleteViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.SearchHistoryViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.SearchResultViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.SellRankViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.StoreManagerViewHolder;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.UserListViewHolder;
import com.cheeshou.cheeshou.market.ui.viewholder.MarketShareHeaderHolder;
import com.cheeshou.cheeshou.market.ui.viewholder.MarketShareHolder;
import com.cheeshou.cheeshou.market.ui.viewholder.MarketStoreShareHolder;
import com.cheeshou.cheeshou.market.ui.viewholder.ShareRankHolder;
import com.cheeshou.cheeshou.order.viewHolder.OrderListViewHolder;
import com.cheeshou.cheeshou.share.viewholder.SharedCarHolder;
import com.cheeshou.cheeshou.share.viewholder.SharedHumanHolder;
import com.example.com.common.adapter.BaseDelegate;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.util.RefreshFooterViewHolder;

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

    public static final int MARKET_SHARE_HEADER_TYPE = 16;
    public static final int MARKET_SHARE_TYPE = 17;

    public static final int SHARE_RANK_TYPE = 18;

    public static final int STORE_SHARE_GRID_TYPE = 19;

    private MarketShareHeaderHolder.ShareRankClickListener listener;

    private CarPhotoViewHolder.OnImageDeleteListener imageDeleteListener;
    private CustomerWantCarViewHolder.OnDeleteListener wantCatDeleteListener;
    private ShareCarPhotoAddViewHolder.OnImageAddListener addCarPhotoListener;

    public static final int USER_LIST_TYPE = 20;

    public static final int CLIENT_LIST_TYPE = 21;

    public static final int SELL_RANK_TYPE = 22;

    public static final int CUSTOMER_MANAGER_TYPE = 23;

    public static final int CUSTOMER_DETAIL_WANT_TYPE = 24;
    public static final int CUSTOMER_DETAIL_FOLLOW_TYPE = 25;

    public static final int ORDER_LIST_TYPE = 26;
    //汽车状态
    public static final int POPUP_WINDOW_CAR_STATE_TYPE = 27;
    //汽车排序
    public static final int POPUP_WINDOW_CAR_ORDER_TYPE = 28;

    public static final int CUSTOMER_WANT_CAR = 29;
    public static final int SHARE_CAR_PHOTO_TYPE = 30;
    public static final int SHARE_CAR_PHOTO_ADD_TYPE = 31;
    public static final int FIND_SUCCESS_CAR_LIST_TYPE = 32;

    public static final int FOOT_TYPE = 99;

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
                CarPhotoViewHolder carPhotoViewHolder = new CarPhotoViewHolder(parent, getItemView(parent, viewType));
                carPhotoViewHolder.setOnImageDeleteListener(imageDeleteListener);
                return carPhotoViewHolder;
            case VEHICLE_HEAT_TYPE:
                return new VehicleHeatViewHolder(parent, getItemView(parent, viewType));
            case STORE_MANAGE_TYPE:
                return new StoreManagerViewHolder(parent, getItemView(parent, viewType));
            case USER_LIST_TYPE:
                return new UserListViewHolder(parent, getItemView(parent, viewType));
            case MARKET_SHARE_HEADER_TYPE:
                MarketShareHeaderHolder marketShareHeaderHolder = new MarketShareHeaderHolder(parent, getItemView(parent, viewType));
                marketShareHeaderHolder.setListener(listener);
                return marketShareHeaderHolder;
            case MARKET_SHARE_TYPE:
                return new MarketShareHolder(parent, getItemView(parent, viewType));
            case SHARE_RANK_TYPE:
                return new ShareRankHolder(parent, getItemView(parent, viewType));
            case STORE_SHARE_GRID_TYPE:
                return new MarketStoreShareHolder(parent, getItemView(parent, viewType));
            case CLIENT_LIST_TYPE:
                return new ClientViewHolder(parent, getItemView(parent, viewType));
            case SELL_RANK_TYPE:
                return new SellRankViewHolder(parent, getItemView(parent, viewType));
            case FOOT_TYPE:
                return new RefreshFooterViewHolder(parent, getItemView(parent, viewType));
            case CUSTOMER_MANAGER_TYPE:
                return new CustomerViewHolder(parent, getItemView(parent, viewType));
            case CUSTOMER_DETAIL_WANT_TYPE:
                return new CustomerDetailWantHolder(parent, getItemView(parent, viewType));
            case CUSTOMER_DETAIL_FOLLOW_TYPE:
                return new CustomerFollowHolder(parent, getItemView(parent, viewType));
            case ORDER_LIST_TYPE:
                return new OrderListViewHolder(parent, getItemView(parent, viewType));
            case POPUP_WINDOW_CAR_STATE_TYPE:
                return new CarStateViewHolder(parent, getItemView(parent, viewType));
            case POPUP_WINDOW_CAR_ORDER_TYPE:
                return new CarOrderViewHolder(parent, getItemView(parent, viewType));
            case CUSTOMER_WANT_CAR:
                CustomerWantCarViewHolder customerWantCarViewHolder = new CustomerWantCarViewHolder(parent, getItemView(parent, viewType));
                customerWantCarViewHolder.setOnDeleteListener(wantCatDeleteListener);
                return customerWantCarViewHolder;
            case SHARE_CAR_PHOTO_TYPE:
                return new ShareCarPhotoViewHolder(parent, getItemView(parent, viewType));
            case SHARE_CAR_PHOTO_ADD_TYPE:
                ShareCarPhotoAddViewHolder shareCarPhotoAddViewHolder = new ShareCarPhotoAddViewHolder(parent, getItemView(parent, viewType));
                return shareCarPhotoAddViewHolder;
            case FIND_SUCCESS_CAR_LIST_TYPE:
                return new FindSuccessCarHolder(parent, getItemView(parent, viewType));
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
            case MARKET_SHARE_HEADER_TYPE:
                return R.layout.item_market_share_header;
            case MARKET_SHARE_TYPE:
                return R.layout.item_market_share;
            case SHARE_RANK_TYPE:
                return R.layout.item_share_rank;
            case USER_LIST_TYPE:
                return R.layout.item_user_manager;
            case STORE_SHARE_GRID_TYPE:
                return R.layout.item_market_store_share_layout;
            case CLIENT_LIST_TYPE:
                return R.layout.item_client;
            case SELL_RANK_TYPE:
                return R.layout.item_sell_rank;
            case FOOT_TYPE:
                return R.layout.layout_refresh_footer;
            case CUSTOMER_MANAGER_TYPE:
                return R.layout.item_customer_recycler;
            case CUSTOMER_DETAIL_WANT_TYPE:
                return R.layout.item_customer_detail_want_car;
            case CUSTOMER_DETAIL_FOLLOW_TYPE:
                return R.layout.item_layout_customer_follow;
            case ORDER_LIST_TYPE:
                return R.layout.item_order_list;
            case POPUP_WINDOW_CAR_STATE_TYPE:
                return R.layout.popup_window_item_state_layout;
            case POPUP_WINDOW_CAR_ORDER_TYPE:
                return R.layout.popup_window_item_order_layout;
            case CUSTOMER_WANT_CAR:
                return R.layout.customer_want_car_item_layout;
            case SHARE_CAR_PHOTO_TYPE:
                return R.layout.item_car_photo;
            case SHARE_CAR_PHOTO_ADD_TYPE:
                return R.layout.item_car_photo;
            case FIND_SUCCESS_CAR_LIST_TYPE:
                return R.layout.item_find_success_car;
            default:
        }
        return 0;
    }

    public void setShareHeaderClickListener(MarketShareHeaderHolder.ShareRankClickListener listener) {
        this.listener = listener;
    }

    public void setOnImageDeleteListener(CarPhotoViewHolder.OnImageDeleteListener listener) {
        this.imageDeleteListener = listener;
    }

    public void setCustomerWantCarDeleteListener(CustomerWantCarViewHolder.OnDeleteListener listener) {
        this.wantCatDeleteListener = listener;
    }

    public void setShareCarPhotoAddListener(ShareCarPhotoAddViewHolder.OnImageAddListener listener) {
//        this.addCarPhotoListener = listener;
//        Log.e("setShareCarPhotoAddListener", "setShareCarPhotoAddListener: " + (this.addCarPhotoListener == null));
    }
}