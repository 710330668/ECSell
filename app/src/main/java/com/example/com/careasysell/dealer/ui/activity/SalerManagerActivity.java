package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.UserListModel;
import com.example.com.careasysell.dealer.ui.model.response.SalersListResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/14.
 */
public class SalerManagerActivity extends BaseActivity {

    @BindView(R.id.rl_user_manager)
    RecyclerView rlUserManager;

    private String token;
    private List<ItemData> userLists = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_sale_list;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlUserManager.setLayoutManager(layoutManager);
        rlUserManager.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Injection.provideApiService().findAllXsUserList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SalersListResponse>() {
                    @Override
                    public void accept(SalersListResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            for(int i =0;i<response.getData().size();i++){
                                UserListModel model = new UserListModel(response.getData().get(i).getUserId(),response.getData().get(i).getUserPic(),
                                        response.getData().get(i).getUserName(),response.getData().get(i).getAccount(), TimeUtils.millis2String(response.getData().get(i).getCreateDate()),
                                        response.getData().get(i).getPhone());
                                ItemData itemData = new ItemData(0, SettingDelegate.USER_LIST_TYPE, model);
                                userLists.add(itemData);
                            }
                            BaseAdapter baseAdapter = new BaseAdapter(userLists, new SettingDelegate(), new onItemClickListener() {
                                @Override
                                public void onClick(View v, Object data) {
                                    UserListModel model = (UserListModel) data;
                                    Intent intent = new Intent();
                                    intent.putExtra("saleName",model.getUserName());
                                    intent.putExtra("xsUserId",model.getUserId());
                                    setResult(RESULT_OK,intent);
                                    finish();
                                }

                                @Override
                                public boolean onLongClick(View v, Object data) {
                                    return false;
                                }
                            });
                            rlUserManager.setAdapter(baseAdapter);
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
