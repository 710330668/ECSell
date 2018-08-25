package com.cheeshou.cheeshou.dealer.ui.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.UserListModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.example.com.imageloader.LoaderManager;

/**
 * Created by 71033 on 2018/8/6.
 */
public class UserListViewHolder extends BaseViewHolder<ItemData> {

    private ImageView imageView;
    private TextView userName;
    private TextView userAccount;
    private TextView phone;
    private TextView timeStamp;

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public UserListViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        imageView = itemView.findViewById(R.id.iv_user);
        userName = itemView.findViewById(R.id.tv_username);
        userAccount = itemView.findViewById(R.id.tv_account);
        phone = itemView.findViewById(R.id.tv_phone);
        timeStamp = itemView.findViewById(R.id.tv_timestamp);
    }

    @Override
    public void onBindViewHolder(ItemData data,int position) {
        UserListModel model = (UserListModel) data.getData();
        userName.setText(model.getUserName());
        userAccount.setText(model.getAccount());
        phone.setText(model.getPhone());
        timeStamp.setText(model.getTimestamp());
        LoaderManager.getLoader().loadNet(imageView,model.getImageUrl());
    }
}
