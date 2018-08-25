package com.cheeshou.cheeshou.options.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.options.model.CarsModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

/**
 * Created by 71033 on 2018/7/26.
 */
public class CarsViewHolder extends BaseViewHolder {

    private TextView tvCarsType;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CarsViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        tvCarsType = itemView.findViewById(R.id.tv_cars);
    }

    @Override
    public void onBindViewHolder(Object data,int position) {
        ItemData itemData = (ItemData) data;
        CarsModel model = (CarsModel) itemData.data;
        tvCarsType.setText(model.getCarsName());
    }
}
