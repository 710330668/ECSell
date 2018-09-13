package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerResponse;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerViewHolder extends BaseViewHolder<ItemData> {

    private TextView mName;
    private TextView mState;
    private TextView mNeed;
    private TextView mFollow;
    private TextView mMessage;
    private static final String TAG = "CustomerViewHolder";

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CustomerViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mName = ((TextView) itemView.findViewById(R.id.tv_customer_name));
        mState = ((TextView) itemView.findViewById(R.id.tv_customer_state));
        mNeed = ((TextView) itemView.findViewById(R.id.tv_buy_need));
        mFollow = ((TextView) itemView.findViewById(R.id.tv_latest_follow));
        mMessage = ((TextView) itemView.findViewById(R.id.tv_message));
    }

    @Override
    public void onBindViewHolder(ItemData data, int position) {
        CustomerResponse.DataBean.ListsBean data1 = (CustomerResponse.DataBean.ListsBean) data.getData();
        mName.setText(data1.getName());
        mNeed.setText(mNeed.getContext().getResources().getString(R.string.customer_need, data1.getMinBudget(), data1.getMaxBudget(), data1.getBrandName(), data1.getCarCount()));
        mFollow.setText(formatData(data1.getProgressDate(), "MM/dd") + " " + data1.getProgressContent());
        mMessage.setText(formatData(data1.getCreateDate(), "yyyy/MM/dd") + "创建|销售 " + data1.getUserName());
        switch (data1.getStatusName()) {
            case "已到店":
                mState.setBackgroundColor(Color.parseColor("#FF5755"));
                break;
            case "未到店":
                mState.setBackgroundColor(Color.parseColor("#3E404F"));
                break;
        }
        mState.setText(data1.getStatusName());
    }

    public String formatData(long time, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String format1 = simpleDateFormat.format(new Date(time));
        return format1;
    }
}
