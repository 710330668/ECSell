package com.cheeshou.cheeshou.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.UserListModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.XsUserResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.EndlessRecyclerOnScrollListener;
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
    private   int PAGE_SIZE = 12;
    private BaseAdapter baseAdapter;
    private int count;

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
        etQueryKey.addTextChangedListener(new EditChangedListener());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlUserManager.setLayoutManager(layoutManager);
        rlUserManager.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rlUserManager.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                baseAdapter.setLoadState(baseAdapter.LOADING);
                if(userLists.size() < count){
                    ++CURRENT_PAGE;
                    getUserList();
                }else{
                    baseAdapter.setLoadState(baseAdapter.LOADING_END);
                }
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserList();
    }

    @SuppressLint("CheckResult")
    private void getUserList() {
        if(userLists.size()>0){
            userLists.remove(userLists.size()-1);
        }
        Injection.provideApiService().getClientList(token,etQueryKey.getText().toString(), PAGE_SIZE+"",CURRENT_PAGE+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XsUserResponse>() {
                    @Override
                    public void accept(XsUserResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            count = response.getData().getCount();
                            List<XsUserResponse.DataBean.UserModelsBean> beans = response.getData().getUserModels();
                            if(beans.size() == 0){
                                baseAdapter.setLoadState(baseAdapter.LOADING_END);
                                return;
                            }
                            for (int i = 0; i < response.getData().getUserModels().size(); i++) {
                                UserListModel userListModel = new UserListModel(beans.get(i).getUserId(),beans.get(i).getUserPic(), response.getData().getUserModels().get(i).getUserName(), "账号:"+
                                        response.getData().getUserModels().get(i).getAccount(), TimeUtils.millis2String(response.getData().getUserModels().get(i).getCreateDate()),
                                        response.getData().getUserModels().get(i).getPhone());
                                ItemData itemData = new ItemData(0, SettingDelegate.USER_LIST_TYPE, userListModel);
                                userLists.add(itemData);
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            userLists.add(e);
                            baseAdapter.notifyDataSetChanged();
                            baseAdapter.setLoadState(baseAdapter.LOADING_COMPLETE);
                        }else if(response.getCode() == 402||response.getCode() == 401){
                            //token失效
                            SP.getInstance(C.USER_DB,UserManagerActivity.this).put(C.USER_ACCOUNT,"");
                            SP.getInstance(C.USER_DB,UserManagerActivity.this).put(C.USER_PASSWORD,"");
                            finishAllActivity();
                            startActivity(LoginActivity.class);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SP.getInstance(C.USER_DB,UserManagerActivity.this).put(C.USER_ACCOUNT,"");
                        SP.getInstance(C.USER_DB,UserManagerActivity.this).put(C.USER_PASSWORD,"");
                        finishAllActivity();
                        startActivity(LoginActivity.class);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d("TAG", "beforeTextChanged--------------->");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d("TAG", "onTextChanged--------------->");
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("TAG", "afterTextChanged--------------->");
        }
    };


    @OnClick({R.id.iv_back, R.id.tv_add_saler,R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_saler:
                startActivity(AddSalerActivity.class);
                break;
            case R.id.tv_search:
                CURRENT_PAGE = 1;
                userLists.clear();
                getUserList();
                break;
        }
    }
}
