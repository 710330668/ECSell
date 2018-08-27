package com.cheeshou.cheeshou.market.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.market.ui.model.MarketShareHeaderModel;
import com.cheeshou.cheeshou.market.ui.model.MarketShareModel;
import com.cheeshou.cheeshou.market.ui.response.MyShareResponse;
import com.cheeshou.cheeshou.market.ui.response.ShareStateResponse;
import com.cheeshou.cheeshou.market.ui.viewholder.MarketShareHeaderHolder;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.market.ui.model.MarketShareHeaderModel;
import com.cheeshou.cheeshou.market.ui.model.MarketShareModel;
import com.cheeshou.cheeshou.market.ui.viewholder.MarketShareHeaderHolder;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

import org.json.JSONObject;

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
                Log.e(TAG, "accept: " + new Gson().toJson(myShareResponse));
                if (myShareResponse.getCode() == 200) {
                    ((MarketShareHeaderModel) mData.get(0).getData()).setBrowerCount(myShareResponse.getData().getMyBrowseCount());
                    ((MarketShareHeaderModel) mData.get(0).getData()).setShareCount(myShareResponse.getData().getMyShareCount());
                    ((MarketShareHeaderModel) mData.get(0).getData()).setShareRankings(myShareResponse.getData().getMyRank());
                    adapter.notifyDataSetChanged();
                }
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

                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

//        for (int i = 0; i < 10; i++) {
//            MarketShareModel data1 = new MarketShareModel();
//            data1.setShareTitle("雪弗兰2013款 科鲁兹 1.6LSL天地版MT");
//            data1.setShareCount(5);
//            data1.setShareTime("2018-06-04");
//            ArrayList<String> imgUrl = new ArrayList<>();
//            for (int j = 0; j < 1; j++) {
//                imgUrl.add("");
//            }
//            data1.setImgUrl(imgUrl);
//            mData.add(new ItemData(0, SettingDelegate.MARKET_SHARE_TYPE, data1));
//        }
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
