package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private CheckBox mCarPutAway;

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
        mCarPutAway = ((CheckBox) itemView.findViewById(R.id.cb_car_put_away));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        final SearchResultModel resultModel = (SearchResultModel) data.data;

        mCarPoster.setImageResource(R.mipmap.ic_launcher);

        mCarTitle.setText(resultModel.getTitle());
        mCarSubTitle.setText(resultModel.getSubTitle());
        mCarState.setText(resultModel.getState());
        try {
            mCarDate.setText(formatDate("yyyy/MM/dd", Long.parseLong(resultModel.getDate())));
        } catch (Exception e) {

        }
//        mCarDate.setText(resultModel.getDate());
        mCarPrice.setText(resultModel.getPrice());
        mCarDeduct.setText(resultModel.getDeduct());
        mCarPutAway.setChecked(resultModel.isPut());

        mCarDate.setVisibility(resultModel.isOpenPutEntrance() ? View.GONE : View.VISIBLE);
        mCarState.setVisibility(resultModel.isOpenPutEntrance() ? View.GONE : View.VISIBLE);
        mCarPutAway.setVisibility(resultModel.isOpenPutEntrance() ? View.VISIBLE : View.GONE);

        if (!TextUtils.isEmpty(resultModel.getImageUrl())) {
            ImageLoader.getInstance().displayImage(resultModel.getImageUrl(), mCarPoster);
        }
//
//        mCarPutAway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                resultModel.setPut(mCarPutAway.isChecked());
//            }
//        });
    }

    private String formatDate(String format, long date) {
        Date date1 = new Date(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String format1 = simpleDateFormat.format(date1);
        return format1;
    }
}
