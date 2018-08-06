package com.example.com.careasysell.options.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.common.adapter.BaseViewHolder;

/**
 * Created by 71033 on 2018/8/6.
 */
public class ClientViewHolder extends BaseViewHolder {

    private TextView tvName;
    private TextView tvDemand;
    private TextView tvFollow;
    private TextView tvTime;
    private TextView tvStatus;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public ClientViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        tvName = itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void onBindViewHolder(Object data) {
    }
}
