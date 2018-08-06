package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.UserListModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/8/6.
 */
public class UserManagerActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_user_manager)
    RecyclerView rlUserManager;

    private List<ItemData> userLists = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_user_manager;
    }

    @Override
    public void initParams(Bundle params) {

        for (int i = 0; i < 5; i++) {
            UserListModel userListModel = new UserListModel("", "王皓", "账号:wanghao", "2017/2/20", "132321231");
            ItemData itemData = new ItemData(0, SettingDelegate.USER_LIST_TYPE, userListModel);
            userLists.add(itemData);
        }

    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlUserManager.setLayoutManager(layoutManager);
        rlUserManager.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        BaseAdapter baseAdapter = new BaseAdapter(userLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                startActivity(SalerDetailActivity.class);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlUserManager.setAdapter(baseAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_back, R.id.tv_add_saler})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_saler:
                startActivity(AddSalerActivity.class);
                break;
        }
    }
}
