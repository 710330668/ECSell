package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.UserListModel;
import com.example.com.careasysell.dealer.ui.model.response.XsUserResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.EndlessRecyclerOnScrollListener;
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
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/6.
 */
public class UserManagerActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_user_manager)
    RecyclerView rlUserManager;
    @BindView(R.id.et_queryKey)
    EditText etQueryKey;

    private List<ItemData> userLists = new ArrayList<>();
    private String token;
    private   int CURRENT_PAGE = 1;
    private   int PAGE_SIZE = 6;
    private BaseAdapter baseAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_user_manager;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlUserManager.setLayoutManager(layoutManager);
        rlUserManager.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rlUserManager.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                ++CURRENT_PAGE;
                baseAdapter.setLoadState(baseAdapter.LOADING);
                getUserList();
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        baseAdapter = new BaseAdapter(userLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                UserListModel model = (UserListModel) data;
                Bundle bundle = new Bundle();
                bundle.putString("userId",model.getUserId());
                startActivity(SalerDetailActivity.class,bundle);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlUserManager.setAdapter(baseAdapter);
        getUserList();
    }

    @SuppressLint("CheckResult")
    private void getUserList() {
        Injection.provideApiService().getClientList(token,etQueryKey.getText().toString(), PAGE_SIZE+"",CURRENT_PAGE+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XsUserResponse>() {
                    @Override
                    public void accept(XsUserResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            List<XsUserResponse.DataBean.UserModelsBean> beans = response.getData().getUserModels();
                            if(beans.size() == 0){
                                baseAdapter.setLoadState(baseAdapter.LOADING_END);
                                return;
                            }
                            for (int i = 0; i < response.getData().getUserModels().size(); i++) {
                                UserListModel userListModel = new UserListModel(beans.get(i).getUserId(),"", response.getData().getUserModels().get(i).getUserName(), "账号:"+
                                        response.getData().getUserModels().get(i).getAccount(), TimeUtils.millis2String(response.getData().getUserModels().get(i).getCreateDate()),
                                        response.getData().getUserModels().get(i).getPhone());
                                ItemData itemData = new ItemData(0, SettingDelegate.USER_LIST_TYPE, userListModel);
                                userLists.add(itemData);
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            userLists.add(e);
                            baseAdapter.notifyDataSetChanged();
                            baseAdapter.setLoadState(baseAdapter.LOADING_COMPLETE);
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
