package com.cheeshou.cheeshou.market.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CarStateModel;
import com.cheeshou.cheeshou.market.ui.model.ShareRankModel;
import com.cheeshou.cheeshou.market.ui.response.ShareRankResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.market.ui.model.ShareRankModel;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.EndlessRecyclerOnScrollListener;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MarketShareRankingsActivity extends BaseActivity {


    @BindView(R.id.rb_share_rank_today)
    RadioButton mRbToday;
    @BindView(R.id.rb_share_rank_the_week)
    RadioButton mRbTheWeek;
    @BindView(R.id.rb_share_rank_last_week)
    RadioButton mRbLaseWeek;
    @BindView(R.id.rb_share_rank_more)
    RadioButton mRbMorel;
    @BindView(R.id.recycler_share_rank)
    RecyclerView mRecycler;
    @BindView(R.id.rg_date)
    RadioGroup mRadioGroup;

    private List<ItemData> mData = new ArrayList<>();
    private String token;
    private String pageSize = "10";
    private String page = "1";
    private String startDate = "";
    private String endDate = "";
    private String count;
    private BaseAdapter adapter;
    private int CURRENT_PAGE = 1;
    private PopupWindow mPopupWindow;
    private List<ItemData> stateData;
    private Calendar calendar;
    private Calendar calendarLast;

    private static final String TAG = "MarketShareRankingsActi";


    @Override
    public int bindLayout() {
        return R.layout.activity_market_share_rankings;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.addItemDecoration(new SpaceItemDecoration(2));
        mRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                adapter.setLoadState(adapter.LOADING);
                if (mData.size() < Integer.parseInt(count)) {
                    CURRENT_PAGE++;
                    initRecycler();
                } else {
                    adapter.setLoadState(adapter.LOADING_END);
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        initPopouWindowData();
        adapter = new BaseAdapter(mData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {

            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecycler.setAdapter(adapter);

        initRecycler();

    }

    private void initPopouWindowData() {
        stateData = new ArrayList<>();
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel((calendar.get(Calendar.MONTH) + 1) + "月", new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), false)));

        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendarLast = Calendar.getInstance();
        calendarLast.set(Calendar.DAY_OF_MONTH, 1);
        calendarLast.add(Calendar.DATE, -1);
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel((calendar.get(Calendar.MONTH) + 1) + "月", new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(calendarLast.getTime()), false)));

        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendarLast = Calendar.getInstance();
        calendarLast.set(Calendar.DAY_OF_MONTH, -1);
        calendarLast.add(Calendar.DATE, -1);
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel((calendar.get(Calendar.MONTH) + 1) + "月", new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(calendarLast.getTime()), false)));
//        stateData.add(new ItemData())
    }


    @OnClick({R.id.rb_share_rank_today, R.id.rb_share_rank_the_week, R.id.rb_share_rank_last_week, R.id.rb_share_rank_more})
    public void onViewCheckChanged(View view) {
        switch (view.getId()) {
            case R.id.rb_share_rank_today:
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 1);
                startDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 1);
                endDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                mData.clear();
                CURRENT_PAGE = 1;
                initRecycler();
                break;
            case R.id.rb_share_rank_the_week:
                calendar = Calendar.getInstance();
                int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                if (day_of_week == 0)
                    day_of_week = 7;
                calendar.add(Calendar.DATE, -day_of_week + 1);
//                    calendar.add(Calendar.DATE, -1);
                startDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 1);
                endDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                mData.clear();
                CURRENT_PAGE = 1;
                initRecycler();
                break;
            case R.id.rb_share_rank_last_week:

                calendar = Calendar.getInstance();
                while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                    calendar.add(Calendar.DAY_OF_WEEK, -1);
                }
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                int offset = 1 - dayOfWeek;
                calendar.add(Calendar.DATE, offset - 7);
                startDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

                calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, 1);
                endDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                mData.clear();
                CURRENT_PAGE = 1;
                initRecycler();
                break;
            case R.id.rb_share_rank_more:
                showPopupWindow();
                break;
        }

    }

    public void showPopupWindow() {
        ColorDrawable dw = new ColorDrawable(0xb0000000);

        LinearLayout convertFrame = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_popup_car_state_recycler, null);
        RecyclerView stateRecycler = (RecyclerView) convertFrame.findViewById(R.id.recycler_car_state);
        initStateRecycler(stateRecycler);
        mPopupWindow = new PopupWindow(convertFrame, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(dw);
        mPopupWindow.showAsDropDown(mRadioGroup, 0, 0);

    }

    public void initRecycler() {
        if (mData.size() > 0) {
            if (mData.get(mData.size() - 1).getHolderType() == SettingDelegate.FOOT_TYPE) {
                mData.remove(mData.size() - 1);
            }
        }
        HashMap<String, String> params = new HashMap<>();
        page = CURRENT_PAGE + "";
        params.put("pageSize", pageSize);
        params.put("page", page);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        Injection.provideApiService().findShareRankList(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ShareRankResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ShareRankResponse shareRankResponse) {
                if (shareRankResponse.getCode() == 200) {
                    count = shareRankResponse.getData().getCount();
                    for (int i = 0; i < shareRankResponse.getData().getLists().size(); i++) {
                        ShareRankResponse.listsBean data = shareRankResponse.getData().getLists().get(i);
                        ShareRankModel shareRankModel = new ShareRankModel();
                        shareRankModel.setImgUrl(data.getUserPic());
                        shareRankModel.setBrowerCount(data.getBrowseNum());
                        shareRankModel.setName(data.getUserName());
                        shareRankModel.setShareCount(data.getShareNum());
                        ItemData e = new ItemData(0, SettingDelegate.SHARE_RANK_TYPE, shareRankModel);
                        mData.add(e);
                    }
                    mData.add(new ItemData(0, SettingDelegate.FOOT_TYPE));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initStateRecycler(RecyclerView stateRecycler) {
        stateRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        Log.e(TAG, "initStateRecycler: " + stateData.size());
        stateRecycler.setAdapter(new BaseAdapter(stateData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                for (ItemData bean : stateData) {
                    ((CarStateModel) bean.getData()).setSelected(false);
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                startDate = ((CarStateModel) data).getStartTime();
                endDate = ((CarStateModel) data).getEndTime();
                Log.e(TAG, "onClick:startDate " + startDate);
                Log.e(TAG, "onClick:endDate " + endDate);
                mData.clear();
                CURRENT_PAGE = 1;
                initRecycler();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        }));
    }
}
