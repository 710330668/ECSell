package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerWantCarModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;

public class CustomerWantCarViewHolder extends BaseViewHolder<ItemData> {

    private TextView mTvCarName;
    private TextView mTvDelete;
    private OnDeleteListener listener;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CustomerWantCarViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        mTvCarName = ((TextView) itemView.findViewById(R.id.tv_car_name));
        mTvDelete = ((TextView) itemView.findViewById(R.id.tv_delete_car));
    }

    @Override
    public void onBindViewHolder(ItemData data, final int position) {
        CustomerWantCarModel data1 = (CustomerWantCarModel) data.getData();
        mTvCarName.setText(data1.getName());
        mTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(position);
            }
        });
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.listener = listener;
    }

    public interface OnDeleteListener {
        void onDeleteClick(int position);
    }
}
