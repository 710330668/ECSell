package com.cheeshou.cheeshou.share.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.share.model.HumanShareModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Author ： DasonYu
 * Date ： 2018/7/31
 * Email Address : 15764240573@163.com
 */

public class SharedHumanHolder extends BaseViewHolder<ItemData> {

    private TextView mCompanyName;
    private TextView mJobPosition;
    private TextView mHumanName;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public SharedHumanHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mCompanyName = ((TextView) itemView.findViewById(R.id.company_name));
        mJobPosition = ((TextView) itemView.findViewById(R.id.job_position));
        mHumanName = ((TextView) itemView.findViewById(R.id.human_name));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        HumanShareModel data1 = (HumanShareModel) data.getData();
        mCompanyName.setText(data1.getCompany());
        mJobPosition.setText(data1.getJobPosition());
        mHumanName.setText(data1.getName());
    }
}
