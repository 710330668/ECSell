package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.ClientModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 71033 on 2018/8/6.
 */
public class ClientListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_client)
    RecyclerView rlClient;

    private List<ItemData> clientLists = new ArrayList<>();
    private String token;

    @Override
    public int bindLayout() {
        return R.layout.activity_client_list;
    }

    @Override
    public void initParams(Bundle params) {

        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);

        for (int i = 0; i < 5; i++) {
            ClientModel clientModel = new ClientModel("", "王皓", "账号:wanghao", "2017/2/20", "132321231");
            ItemData itemData = new ItemData(0, SettingDelegate.CLIENT_LIST_TYPE, clientModel);
            clientLists.add(itemData);
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlClient.setLayoutManager(layoutManager);
        rlClient.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        BaseAdapter baseAdapter = new BaseAdapter(clientLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlClient.setAdapter(baseAdapter);
//        Injection.provideApiService().getClientList(token,)
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
