package com.cheeshou.cheeshou.order.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.order.response.OrderListResponse;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.util.TimeUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Author ： DasonYu
 * Date ： 2018/7/28
 * Email Address : 15764240573@163.com
 */

public class OrderListViewHolder extends BaseViewHolder<ItemData> {

    private ImageView mCarPoster;
    private TextView mCarTitle;
    private TextView mOrderSubTitle;
    private TextView mOrderPrice;
    private TextView mOrderDate;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public OrderListViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mCarPoster = ((ImageView) itemView.findViewById(R.id.img_car_poster));
        mCarTitle = ((TextView) itemView.findViewById(R.id.tv_car_title));
        mOrderSubTitle = ((TextView) itemView.findViewById(R.id.tv_order_sub_title));
        mOrderPrice = ((TextView) itemView.findViewById(R.id.tv_order_price));
        mOrderDate = ((TextView) itemView.findViewById(R.id.tv_order_date));
    }

    @Override
    public void onBindViewHolder(ItemData data,int position) {
        OrderListResponse.DataBean.ListsBean resultModel = (OrderListResponse.DataBean.ListsBean) data.data;

        mCarPoster.setImageResource(R.mipmap.ic_launcher);

        mCarTitle.setText(resultModel.getVname());
        mOrderSubTitle.setText("客户"+resultModel.getCustomerName() + "" + "销售"+resultModel.getUserName());
        mOrderDate.setText(TimeUtils.millis2String(resultModel.getComDate()));
        mOrderPrice.setText(resultModel.getOrderPrice()+"万");


        if(!TextUtils.isEmpty(resultModel.getImgThumUrl())){
            ImageLoader.getInstance().displayImage(resultModel.getImgThumUrl(),mCarPoster);
        }

    }
}
