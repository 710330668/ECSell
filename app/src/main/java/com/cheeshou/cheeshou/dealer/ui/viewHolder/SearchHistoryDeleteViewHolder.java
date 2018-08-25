package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cheeshou.cheeshou.R;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Author ： DasonYu
 * Date ： 2018/8/1
 * Email Address : 15764240573@163.com
 */

public class SearchHistoryDeleteViewHolder extends BaseViewHolder<ItemData> {

    private RelativeLayout mTvHistory;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public SearchHistoryDeleteViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mTvHistory = ((RelativeLayout) itemView.findViewById(R.id.rv_delete_history));
    }

    @Override
    public void onBindViewHolder(ItemData data,int position) {
    }
}
