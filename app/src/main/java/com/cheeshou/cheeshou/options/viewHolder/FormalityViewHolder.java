package com.cheeshou.cheeshou.options.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.options.model.FormalityModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/7/26.
 */
public class FormalityViewHolder extends BaseViewHolder {

    private TextView tvFormality;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public FormalityViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        tvFormality = itemView.findViewById(R.id.tv_formality);
    }

    @Override
    public void onBindViewHolder(Object data) {
        ItemData itemData = (ItemData) data;
        FormalityModel model = (FormalityModel) itemData.data;
        tvFormality.setText(model.getFormalityName());
    }
}
