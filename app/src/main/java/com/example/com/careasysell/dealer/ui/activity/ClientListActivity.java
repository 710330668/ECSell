package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
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
import com.example.com.careasysell.dealer.ui.model.response.ToShopResponse;
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
public class ClientListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_client)
    RecyclerView rlClient;

    private List<ItemData> clientLists = new ArrayList<>();
    private String token;
    private   int CURRENT_PAGE = 1;
    private   int PAGE_SIZE = 6;
    private int count ;
    private BaseAdapter mDataAdapter;
    private String source;

    @Override
    public int bindLayout() {
        return R.layout.activity_client_list;
    }

    @Override
    public void initParams(Bundle params) {

        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        source = params.getString("source");

//
//        for (int i = 0; i < 5; i++) {
//            ClientModel clientModel = new ClientModel("", "王皓", "账号:wanghao", "2017/2/20", "132321231");
//            ItemData itemData = new ItemData(0, SettingDelegate.CLIENT_LIST_TYPE, clientModel);
//            clientLists.add(itemData);
//        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlClient.setLayoutManager(layoutManager);
        rlClient.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rlClient.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mDataAdapter.setLoadState(mDataAdapter.LOADING);
                if(clientLists.size() < count){
                    ++CURRENT_PAGE;
                    switch (source){
                        case C.SOURCE_DAY_NEW:
                            break;
                        case C.SOURCE_MONTH_NEW:
                            break;
                        case C.SOURCE_DAY_SHOP:
                            getDayShopClient();
                            break;
                        case C.SOURCE_MONTH_SHOP:
                            getMonthShopClient();
                            break;
                    }
                }else{
                    mDataAdapter.setLoadState(mDataAdapter.LOADING_END);
                }
            }
        });
        mDataAdapter = new BaseAdapter(clientLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlClient.setAdapter(mDataAdapter);

        switch (source){
            case C.SOURCE_DAY_NEW:
                break;
            case C.SOURCE_MONTH_NEW:
                break;
            case C.SOURCE_DAY_SHOP:
                getDayShopClient();
                break;
            case C.SOURCE_MONTH_SHOP:
                getMonthShopClient();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void getMonthShopClient() {
        if(clientLists.size()>0){
            clientLists.remove(clientLists.size()-1);
        }
        Injection.provideApiService().findMonthEnterCustomerInfo(token,CURRENT_PAGE+"",PAGE_SIZE+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ToShopResponse>() {
                    @Override
                    public void accept(ToShopResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            List<ToShopResponse.DataBean.ListsBean> lists  = response.getData().getLists();
                            count = response.getData().getCount();
                            for(int i =0 ;i<lists.size();i++){
                                ClientModel clientModel = new ClientModel(lists.get(i).getName(), "预算"+lists.get(i).getMinBudget()+"-"+lists.get(i).getMaxBudget()+"万|"+lists.get(i).getNeedTxt()+"等"+lists.get(i).getCarCount()+"辆车",
                                        lists.get(i).getProgressDate()+" "+ lists.get(i).getProgressContent(),
                                        TimeUtils.millis2String(lists.get(i).getCreateDate()) +"创建|销售"+lists.get(i).getUserName(), lists.get(i).getStatus());
                                ItemData itemData = new ItemData(0, SettingDelegate.CLIENT_LIST_TYPE, clientModel);
                                clientLists.add(itemData);
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            clientLists.add(e);
                        }
                        mDataAdapter.notifyDataSetChanged();
                        mDataAdapter.setLoadState(mDataAdapter.LOADING_COMPLETE);
                    }
                });
    }


    @SuppressLint("CheckResult")
    private void getDayShopClient() {
        if(clientLists.size()>0){
            clientLists.remove(clientLists.size()-1);
        }
        Injection.provideApiService().findDayEnterCustomerInfo(token,CURRENT_PAGE+"",PAGE_SIZE+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ToShopResponse>() {
                    @Override
                    public void accept(ToShopResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            List<ToShopResponse.DataBean.ListsBean> lists  = response.getData().getLists();
                            count = response.getData().getCount();
                            for(int i =0 ;i<lists.size();i++){
                                ClientModel clientModel = new ClientModel(lists.get(i).getName(), "预算"+lists.get(i).getMinBudget()+"-"+lists.get(i).getMaxBudget()+"万|"+lists.get(i).getNeedTxt()+"等"+lists.get(i).getCarCount()+"辆车",
                                        lists.get(i).getProgressDate()+" "+ lists.get(i).getProgressContent(),
                                        TimeUtils.millis2String(lists.get(i).getCreateDate()) +"创建|销售"+lists.get(i).getUserName(), lists.get(i).getStatus());
                                ItemData itemData = new ItemData(0, SettingDelegate.CLIENT_LIST_TYPE, clientModel);
                                clientLists.add(itemData);
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            clientLists.add(e);
                        }
                        mDataAdapter.notifyDataSetChanged();
                        mDataAdapter.setLoadState(mDataAdapter.LOADING_COMPLETE);
                    }
                });
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
