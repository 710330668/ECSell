package com.cheeshou.cheeshou.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.cheeshou.cheeshou.options.CarDetailActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.ReportCarResponse;
import com.cheeshou.cheeshou.options.CarDetailActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.EndlessRecyclerOnScrollListener;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
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
public class ReportCarListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_car)
    RecyclerView rlCar;

    private BaseAdapter mDataAdapter;

    private List<ItemData> mSearchResultData = new ArrayList<>();

    private String token;

    private  int CURRENT_PAGE = 1;
    private  int PAGE_SIZE = 6;
    private int count ;
    private String source;

    @Override
    public int bindLayout() {
        return R.layout.activity_report_car_list;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        source = params.getString("source");
//        for (int i = 0; i < 10; i++) {
//            SearchResultModel data = new SearchResultModel();
//            data.setDate("2018/06/24");
//            data.setDeduct("销售提成2000");
//            data.setPrice("16.8万");
//            data.setState("已上架");
//            data.setSubTitle("分享20次|浏览140次");
//            data.setTitle("雪佛兰2013款  科鲁兹  16LSL天地板MT");
//            ItemData e = new ItemData(0, SettingDelegate.SEARCH_RESULT_TYPE, data);
//            mSearchResultData.add(e);
//        }
//        ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
//        mSearchResultData.add(e);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        rlCar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rlCar.addItemDecoration(new SpaceItemDecoration(5));
        rlCar.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mDataAdapter.setLoadState(mDataAdapter.LOADING);
                if(mSearchResultData.size() < count){
                    ++CURRENT_PAGE;
                    if(source.equals(C.SOURCE_DAY_SELL)){
                        getDayCarList();
                    }else{
                        getMonthCarList();
                    }
                }else{
                    mDataAdapter.setLoadState(mDataAdapter.LOADING_END);
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        mDataAdapter = new BaseAdapter(mSearchResultData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                SearchResultModel model = (SearchResultModel) data;
                Bundle bundle = new Bundle();
                bundle.putString("carId", model.getId());
                startActivity(CarDetailActivity.class, bundle);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlCar.setAdapter(mDataAdapter);
        if(source.equals(C.SOURCE_DAY_SELL)){
            getDayCarList();
        }else{
            getMonthCarList();
        }

    }

    @SuppressLint("CheckResult")
    private void getMonthCarList() {
        if(mSearchResultData.size()>0){
            mSearchResultData.remove(mSearchResultData.size()-1);
        }
        Injection.provideApiService().findMonthOrderList(token,CURRENT_PAGE+"",PAGE_SIZE+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportCarResponse>() {
                    @Override
                    public void accept(ReportCarResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            count = response.getData().getCount();
                            for (int i = 0; i < response.getData().getLists().size(); i++) {
                                SearchResultModel data = new SearchResultModel();
                                data.setDate(TimeUtils.millis2String(response.getData().getLists().get(i).getComDate()));
                                data.setDeduct(response.getData().getLists().get(i).getSaleCommission() + "");
                                data.setPrice(response.getData().getLists().get(i).getBrowseNum() + "万");
//                                data.setState(response.getData().getLists().get(i).getCarStatusName());
                                data.setSubTitle("分享" + response.getData().getLists().get(i).getShareNum() + "次|浏览140次");
                                data.setTitle(response.getData().getLists().get(i).getVname());
                                data.setImageUrl(response.getData().getLists().get(i).getImgThumUrl());
                                data.setId(response.getData().getLists().get(i).getOrderItemId());
                                ItemData e = new ItemData(0, SettingDelegate.SEARCH_RESULT_TYPE, data);
                                mSearchResultData.add(e);
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            mSearchResultData.add(e);
                        }
                        mDataAdapter.notifyDataSetChanged();
                        mDataAdapter.setLoadState(mDataAdapter.LOADING_COMPLETE);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getDayCarList() {
        if(mSearchResultData.size()>0){
            mSearchResultData.remove(mSearchResultData.size()-1);
        }
        Injection.provideApiService().findDayOrderList(token,CURRENT_PAGE+"",PAGE_SIZE+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportCarResponse>() {
                    @Override
                    public void accept(ReportCarResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            count = response.getData().getCount();
                            for (int i = 0; i < response.getData().getLists().size(); i++) {
                                SearchResultModel data = new SearchResultModel();
                                data.setDate(TimeUtils.millis2String(response.getData().getLists().get(i).getComDate()) );
                                data.setDeduct(response.getData().getLists().get(i).getSaleCommission() + "");
                                data.setPrice(response.getData().getLists().get(i).getBrowseNum() + "万");
//                                data.setState(response.getData().getLists().get(i).getCarStatusName());
                                data.setSubTitle("分享" + response.getData().getLists().get(i).getShareNum() + "次|浏览140次");
                                data.setTitle(response.getData().getLists().get(i).getVname());
                                data.setImageUrl(response.getData().getLists().get(i).getImgThumUrl());
                                data.setId(response.getData().getLists().get(i).getOrderItemId());
                                ItemData e = new ItemData(0, SettingDelegate.SEARCH_RESULT_TYPE, data);
                                mSearchResultData.add(e);
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            mSearchResultData.add(e);
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
