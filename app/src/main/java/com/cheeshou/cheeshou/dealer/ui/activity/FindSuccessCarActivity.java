package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.response.FindSuccessCarResponse;
import com.cheeshou.cheeshou.market.ui.response.ShareRankResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FindSuccessCarActivity extends BaseActivity {

    private String token;
    private static final String TAG = "FindSuccessCarActivity";
    private List<ItemData> dataList = new ArrayList<>();

    @BindView(R.id.recycler_sold_car)
    RecyclerView mRecycler;
    private BaseAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_find_success_car;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    @OnClick({R.id.img_back, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_sure:
                this.setResult(RESULT_OK);
                for (ItemData bean : dataList) {
                    if (((FindSuccessCarResponse.DataBean) bean.getData()).isChecked()) {

                    }
                }
                finish();
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        Injection.provideApiService().findSoldOutCarList(token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<FindSuccessCarResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FindSuccessCarResponse findSuccessCarResponse) {
                List<FindSuccessCarResponse.DataBean> data = findSuccessCarResponse.getData();
                if (findSuccessCarResponse.getCode() == 200 && data != null && data.size() > 0) {
                    for (FindSuccessCarResponse.DataBean bean : data) {
                        ItemData itemData = new ItemData(0, SettingDelegate.FIND_SUCCESS_CAR_LIST_TYPE, bean);
                        dataList.add(itemData);
                    }
                }
                adapter = new BaseAdapter(dataList, new SettingDelegate(), new onItemClickListener() {
                    @Override
                    public void onClick(View v, Object data) {
                        FindSuccessCarResponse.DataBean data1 = (FindSuccessCarResponse.DataBean) data;
                        data1.setChecked(!data1.isChecked());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public boolean onLongClick(View v, Object data) {
                        return false;
                    }
                });
                mRecycler.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
