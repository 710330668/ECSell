package com.cheeshou.cheeshou.market.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.market.ui.model.MarketShareHeaderModel;
import com.cheeshou.cheeshou.market.ui.model.MarketShareModel;
import com.cheeshou.cheeshou.market.ui.response.MyShareResponse;
import com.cheeshou.cheeshou.market.ui.response.ShareStateResponse;
import com.cheeshou.cheeshou.market.ui.viewholder.MarketShareHeaderHolder;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MarketShareActivity extends BaseActivity {

    @BindView(R.id.recycler_mine_share)
    RecyclerView mRecycler;

    private static final String TAG = "MarketShareActivity";

    private List<ItemData> mData = new ArrayList<>();
    private String token;
    private String mName;
    private String mCompany;
    private String mAddress;
    private BaseAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_market_share;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        mAddress = SP.getInstance(C.USER_DB, this).getString(C.USER_ADDRESS);
        mName = SP.getInstance(C.USER_DB, this).getString(C.USER_NAME);
        mCompany = SP.getInstance(C.USER_DB, this).getString(C.USER_COMPANYNAME);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.addItemDecoration(new SpaceItemDecoration(5));
    }

    @Override
    public void doBusiness(Context mContext) {
        MarketShareHeaderModel data = new MarketShareHeaderModel();
        data.setBrowerCount("");
        data.setShareCount("");
        data.setShareRankings("");
        data.setCompanyName(mCompany);
        data.setHumanNmae(mName);
        mData.add(new ItemData(0, SettingDelegate.MARKET_SHARE_HEADER_TYPE, data));

        Injection.provideApiService().findMyStatDetail(token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ShareStateResponse>() {
            @Override
            public void accept(ShareStateResponse myShareResponse) throws Exception {
                if (myShareResponse.getCode() == 200) {
                    ((MarketShareHeaderModel) mData.get(0).getData()).setBrowerCount(myShareResponse.getData().getMyBrowseCount());
                    ((MarketShareHeaderModel) mData.get(0).getData()).setShareCount(myShareResponse.getData().getMyShareCount());
                    ((MarketShareHeaderModel) mData.get(0).getData()).setShareRankings(myShareResponse.getData().getMyRank());
                    adapter.notifyDataSetChanged();
                } else if (myShareResponse.getCode() == 402 || myShareResponse.getCode() == 401) {
                    //token失效
                    SP.getInstance(C.USER_DB, MarketShareActivity.this).put(C.USER_ACCOUNT, "");
                    SP.getInstance(C.USER_DB, MarketShareActivity.this).put(C.USER_PASSWORD, "");
                    finishAllActivity();
                    startActivity(LoginActivity.class);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                SP.getInstance(C.USER_DB, MarketShareActivity.this).put(C.USER_ACCOUNT, "");
                SP.getInstance(C.USER_DB, MarketShareActivity.this).put(C.USER_PASSWORD, "");
                finishAllActivity();
                startActivity(LoginActivity.class);
            }
        });

        HashMap<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("pageSize", "10");
        Injection.provideApiService().findMyShareList(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MyShareResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyShareResponse myShareResponse) {
                if (myShareResponse != null && myShareResponse.getCode() == 200) {
                    List<MyShareResponse.ShareCarBean> lists = myShareResponse.getData().getLists();
                    for (MyShareResponse.ShareCarBean bean : lists) {
                        MarketShareModel data1 = new MarketShareModel();
                        data1.setShareCount(bean.getBrowseNum());
                        data1.setShareTime(bean.getCreateDate());
                        ArrayList<String> imgUrl = new ArrayList<>();
                        for (int j = 0; j < bean.getSaleCarInfos().size(); j++) {
                            data1.setShareTitle(bean.getSaleCarInfos().get(j).getBrand()+ " "+ bean.getSaleCarInfos().get(j).getVname());
                            imgUrl.add(bean.getSaleCarInfos().get(j).getImgThumUrl());
                        }
                        data1.setImgUrl(imgUrl);
                        mData.add(new ItemData(0, SettingDelegate.MARKET_SHARE_TYPE, data1));
                    }
                    Log.e(TAG, "onNext: " + new Gson().toJson(mData));
                    adapter.notifyDataSetChanged();
                } else if (myShareResponse.getCode() == 402 || myShareResponse.getCode() == 401) {
                    //token失效
                    SP.getInstance(C.USER_DB, MarketShareActivity.this).put(C.USER_ACCOUNT, "");
                    SP.getInstance(C.USER_DB, MarketShareActivity.this).put(C.USER_PASSWORD, "");
                    finishAllActivity();
                    startActivity(LoginActivity.class);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        SettingDelegate delegate = new SettingDelegate();
        delegate.setShareHeaderClickListener(new MarketShareHeaderHolder.ShareRankClickListener() {
            @Override
            public void onShareClick() {
                startActivity(MarketShareRankingsActivity.class);
            }
        });
        adapter = new BaseAdapter(mData, delegate);
        mRecycler.setAdapter(adapter);
    }

    @OnClick({R.id.img_back})
    public void onViewClicked() {
        this.finish();
    }
}
