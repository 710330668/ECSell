package com.cheeshou.cheeshou.options.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.options.model.SalesAreaModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/7/26.
 */
public class SalesAreaViewHolder extends BaseViewHolder {

    private TextView tvSalesArea;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public SalesAreaViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        tvSalesArea = itemView.findViewById(R.id.tv_sales_area);
    }

    @Override
    public void onBindViewHolder(Object data,int position) {
        ItemData itemData = (ItemData) data;
        SalesAreaModel model = (SalesAreaModel) itemData.data;
        tvSalesArea.setText(model.getSalesAreaName());
    }
}
