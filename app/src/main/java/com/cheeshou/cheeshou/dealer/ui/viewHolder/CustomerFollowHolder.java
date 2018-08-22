package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerFollowModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class CustomerFollowHolder extends BaseViewHolder<ItemData> {

    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mTvMessage;
    private TextView mTvFrom;
    private ImageView mImgCircle;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CustomerFollowHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mTvDate = ((TextView) itemView.findViewById(R.id.tv_date));
        mTvTime = ((TextView) itemView.findViewById(R.id.tv_time));
        mTvMessage = ((TextView) itemView.findViewById(R.id.tv_message));
        mTvFrom = ((TextView) itemView.findViewById(R.id.tv_from));
        mImgCircle = ((ImageView) itemView.findViewById(R.id.img_circle));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        CustomerFollowModel data1 = (CustomerFollowModel) data.getData();
        mTvDate.setText(data1.getDate());
        mTvTime.setText(data1.getTime());
        mTvMessage.setText(data1.getMessage());
        mTvFrom.setText(data1.getFrom());

        if (getAdapterPosition() == 0) {
            mImgCircle.setImageResource(R.drawable.circle_red);
        } else {
            mImgCircle.setImageResource(R.drawable.circle_gary);
        }

    }
}
