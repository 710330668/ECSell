package com.example.com.careasysell.options.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.model.OptionTypeModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/7/26.
 */
public class OptionTypeViewHolder extends BaseViewHolder {

    private TextView tvOptionType;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public OptionTypeViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        tvOptionType = itemView.findViewById(R.id.tv_type);
    }

    @Override
    public void onBindViewHolder(Object data) {
        ItemData itemData = (ItemData) data;
        OptionTypeModel model = (OptionTypeModel) itemData.data;
        tvOptionType.setText(model.getOptionType());
    }
}
