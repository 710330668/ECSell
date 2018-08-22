package com.cheeshou.cheeshou.options.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.options.model.ColorModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/7/26.
 */
public class ColorViewHolder extends BaseViewHolder {

    private TextView tvColorType;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public ColorViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        tvColorType = itemView.findViewById(R.id.tv_color);
    }

    @Override
    public void onBindViewHolder(Object data) {
        ItemData itemData = (ItemData) data;
        ColorModel model = (ColorModel) itemData.data;
        tvColorType.setText(model.getApprearceColor());
    }
}
