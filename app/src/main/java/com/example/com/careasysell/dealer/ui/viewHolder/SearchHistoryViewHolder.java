package com.example.com.careasysell.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.SearchHistoryModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Author ： DasonYu
 * Date ： 2018/8/1
 * Email Address : 15764240573@163.com
 */

public class SearchHistoryViewHolder extends BaseViewHolder<ItemData> {

    private TextView mTvHistory;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public SearchHistoryViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mTvHistory = ((TextView) itemView.findViewById(R.id.tv_search_history));
    }

    @Override
    public void onBindViewHolder(ItemData data) {
        SearchHistoryModel data1 = (SearchHistoryModel) data.getData();
        mTvHistory.setText(data1.getSearchHistory());
    }
}
