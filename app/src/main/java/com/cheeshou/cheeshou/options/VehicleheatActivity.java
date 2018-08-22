package com.cheeshou.cheeshou.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.options.model.VehicleHeatModel;
import com.cheeshou.cheeshou.options.model.response.HotCarCountResponse;
import com.cheeshou.cheeshou.options.model.response.HotCarListResponse;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.options.model.VehicleHeatModel;
import com.cheeshou.cheeshou.options.model.response.HotCarCountResponse;
import com.cheeshou.cheeshou.options.model.response.HotCarListResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.EndlessRecyclerOnScrollListener;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.DayBean;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.TimeUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class VehicleheatActivity extends BaseActivity {

    @BindView(R.id.recycler_vehicle_heat)
    RecyclerView mRecycler;
    @BindView(R.id.img_back)
    ImageView mTvBack;
    @BindView(R.id.rb_today)
    RadioButton mRbToday;
    @BindView(R.id.rb_the_week)
    RadioButton mRbTheWeek;
    @BindView(R.id.rb_last_week)
    RadioButton mRbLastWeek;
    @BindView(R.id.rb_choose_date)
    RadioButton mRbChooseData;
    @BindView(R.id.rg_radio_button)
    RadioGroup rgRadioButton;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    @BindView(R.id.tv_shelves)
    TextView tvShelves;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_browse)
    TextView tvBrowse;

    private String startDate;
    private String endDate;
    private List<DayBean> weekDays;
    private List<DayBean> lastWeekDays;
    private String todayTime;

    private List<ItemData> mData = new ArrayList<>();
    private String token;
    private Calendar calendar;
    private ArrayList<String> months = new ArrayList<>();
    private View popView;
    private ListView lvMonth;
    private String []data = new String [3];
    private PopupWindow pop;
    private   int CURRENT_PAGE = 1;
    private   int PAGE_SIZE = 6;
    private int count ;
    private BaseAdapter<ItemData> adapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_vehicleheat;
    }

    @Override
    public void initParams(Bundle params) {
        weekDays = TimeUtils.getWeekData();
        lastWeekDays = TimeUtils.getLastWeekData();
        todayTime = TimeUtils.getTodayTime();
        calendar = Calendar.getInstance();
        for(int i = 0 ;i<data.length;i++){
            data[i] = Calendar.getInstance().get(Calendar.MONTH) + 1 - i +"月";
        }
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        popView = LayoutInflater.from(getApplication()).inflate(R.layout.item_pop_month, null, false);
        lvMonth = popView.findViewById(R.id.lv_month);
        mRbChooseData.setText(calendar.get(Calendar.MONTH)+1+"月");
        ArrayAdapter<String> array=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,data);
        lvMonth.setAdapter(array);
        lvMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mRbChooseData.setText(data[i]);
                getDayOfMonth(data[i]);
            }
        });
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.addItemDecoration(new SpaceItemDecoration(10));
        mRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                adapter.setLoadState(adapter.LOADING);
                if(mData.size() < count){
                    ++CURRENT_PAGE;
                    refreshCarData();
                }else{
                    adapter.setLoadState(adapter.LOADING_END);
                }
            }
        });
    }

    private void getDayOfMonth(String datum) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(datum.length() == 2){
            try {
                Date date = format.parse(calendar.get(Calendar.YEAR)+"-0"+datum.substring(0,1)+"-01");
                startDate = TimeUtils.getSupportBeginDayofMonth(date);
                endDate = TimeUtils.getSupportEndDayofMonth(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            try {
                Date date = format.parse(calendar.get(Calendar.YEAR)+"-"+datum.substring(0,2)+"-01");
                startDate = TimeUtils.getSupportBeginDayofMonth(date);
                endDate = TimeUtils.getSupportEndDayofMonth(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        refreshData();
        mData.clear();
        CURRENT_PAGE = 1;
        PAGE_SIZE = 6;
        refreshCarData();
        pop.dismiss();
    }

    @Override
    public void doBusiness(Context mContext) {
        adapter = new BaseAdapter<>(mData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {

            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecycler.setAdapter(adapter);

        mRbToday.setChecked(true);

    }

    private void showPopWindow() {
        pop = new PopupWindow(popView, 300, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.showAsDropDown(mRbChooseData, 10, 70);
    }

    @OnClick({R.id.img_back, R.id.rb_choose_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.rb_choose_date:
                showPopWindow();
                break;
            default:
        }
    }



    @OnCheckedChanged({R.id.rb_today, R.id.rb_the_week, R.id.rb_last_week})
    public void onCheckdChanged(RadioButton view, boolean isChecked) {
        if(!isChecked){
            return;
        }
        switch (view.getId()) {
            case R.id.rb_today:
                startDate = todayTime;
                endDate = todayTime;
                refreshData();
                mData.clear();
                CURRENT_PAGE = 1;
                PAGE_SIZE = 6;
                refreshCarData();
                break;
            case R.id.rb_the_week:
                startDate = weekDays.get(0).getTime();
                endDate = weekDays.get(weekDays.size() - 1).getTime();
                refreshData();
                mData.clear();
                CURRENT_PAGE = 1;
                PAGE_SIZE = 6;
                refreshCarData();
                break;
            case R.id.rb_last_week:
                startDate = lastWeekDays.get(0).getTime();
                endDate = lastWeekDays.get(lastWeekDays.size() - 1).getTime();
                refreshData();
                mData.clear();
                CURRENT_PAGE = 1;
                PAGE_SIZE = 6;
                refreshCarData();
                break;
            default:
        }
    }

    @SuppressLint("CheckResult")
    private void refreshCarData() {
        if(mData.size()>0){
            mData.remove(mData.size()-1);
        }
        Injection.provideApiService().findHotCarList(token,startDate,endDate,PAGE_SIZE+"",CURRENT_PAGE+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotCarListResponse>() {
                    @Override
                    public void accept(HotCarListResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            count = response.getData().getCount();
                            for(int i = 0; i < response.getData().getLists().size();i++) {
                                VehicleHeatModel data = new VehicleHeatModel();
                                data.setBrowsTime(response.getData().getLists().get(i).getBrowseNum()+"");
                                data.setShareTime(response.getData().getLists().get(i).getShareNum()+"");
                                data.setPutAwayTime(response.getData().getLists().get(i).getShelvesNum()+"");
                                data.setCarRankings(i + 1);
                                data.setCarTitle(response.getData().getLists().get(i).getVname());
                                mData.add(new ItemData(0, SettingDelegate.VEHICLE_HEAT_TYPE, data));
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            mData.add(e);
                            adapter.notifyDataSetChanged();
                            adapter.setLoadState(adapter.LOADING_COMPLETE);
                        }
                    }
                });
    }

    /**
     * 刷新页面信息
     */
    @SuppressLint("CheckResult")
    public void refreshData() {
        Injection.provideApiService().findHotCarCount(token, startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotCarCountResponse>() {
                    @Override
                    public void accept(HotCarCountResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            tvRelease.setText(response.getData().getIssueCount()+"辆");
                            tvBrowse.setText(response.getData().getBrowseCount()+"次");
                            tvShare.setText(response.getData().getShareCount()+"次");
                            tvShelves.setText(response.getData().getShelvesCount()+"次");
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
