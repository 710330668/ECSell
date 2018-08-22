package com.cheeshou.cheeshou.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.SellRankModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.MySaleCountResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.NearDaySaleResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.XsUserStatResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.view.ChartView;
import com.cheeshou.cheeshou.view.CustomDatePicker;
import com.cheeshou.cheeshou.view.DrawableCenterRadioButton;
import com.cheeshou.cheeshou.R;
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
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/8.
 */
public class StoreReportActivity extends BaseActivity {

    @BindView(R.id.rl_sell_rank)
    RecyclerView rlSellRank;
    @BindView(R.id.rb_last_custom)
    RadioButton rbLastCustom;
    @BindView(R.id.rv_top_layout)
    RelativeLayout rvTopLayout;
    @BindView(R.id.rb_today)
    RadioButton rbToday;
    @BindView(R.id.rb_the_week)
    RadioButton rbTheWeek;
    @BindView(R.id.rb_last_month)
    RadioButton rbLastMonth;
    @BindView(R.id.rb_last_year)
    RadioButton rbLastYear;
    @BindView(R.id.rg_radio_button)
    RadioGroup rgRadioButton;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_unit2)
    TextView tvUnit2;
    @BindView(R.id.rb_sell)
    TextView rbSell;
    @BindView(R.id.rb_client)
    DrawableCenterRadioButton rbClient;
    @BindView(R.id.rb_daodian)
    DrawableCenterRadioButton rbDaodian;
    @BindView(R.id.rb_chengjiao)
    DrawableCenterRadioButton rbChengjiao;
    @BindView(R.id.rb_call)
    DrawableCenterRadioButton rbCall;
    @BindView(R.id.ll_tab)
    RadioGroup llTab;
    @BindView(R.id.tv_sale_num)
    TextView tvSaleNum;
    @BindView(R.id.tv_money_num)
    TextView tvMoneyNum;
    @BindView(R.id.chartview)
    ChartView chartview;


    private List<ItemData> sellRankLists = new ArrayList<>();
    private View convertView;
    private PopupWindow mPopupWindow;
    private RelativeLayout rlStartTime, rlEndTime;
    private CustomDatePicker customDatePicker, customDate2Picker;
    private String now;
    private TextView tvStartTime, tvEndTime;
    private String token;
    private String todayTime;
    private List<DayBean> weekDays;
    private String month, year;
    private String startDate;
    private String endDate;
    private int count;
    private BaseAdapter baseAdapter;
    private String[] xLabel, data;

    @Override
    public int bindLayout() {
        return R.layout.activity_store_report;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        weekDays = TimeUtils.getWeekData();
        todayTime = TimeUtils.getTodayTime();
        month = Calendar.getInstance().get(Calendar.MONTH) + 1 + "月";
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());

        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvStartTime.setText(time.substring(0, 10));
                startDate = time.substring(0, 10);
            }
        }, "1990-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(false); // 不允许循环滚动
        customDatePicker.showMonth();
        customDate2Picker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvEndTime.setText(time.substring(0, 10));
                endDate = time.substring(0, 10);
                findXsUserStatList();
                getMySaleCount();
                mPopupWindow.dismiss();
            }
        }, "1990-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDate2Picker.showSpecificTime(false); // 不显示时和分
        customDate2Picker.setIsLoop(false); // 不允许循环滚动
        customDate2Picker.showMonth();
        rbToday.performClick();

    }

    @Override
    public void doBusiness(Context mContext) {
        getNearDaySale();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlSellRank.setLayoutManager(layoutManager);
        rlSellRank.setNestedScrollingEnabled(false);
        rlSellRank.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        baseAdapter = new BaseAdapter(sellRankLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                startActivity(SalerDetailActivity.class);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlSellRank.setAdapter(baseAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.rv_top_layout, R.id.rb_today, R.id.rb_the_week, R.id.rb_last_month, R.id.rb_last_year, R.id.rb_last_custom, R.id.rg_radio_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rv_top_layout:
                break;
            case R.id.rb_today:
                startDate = todayTime;
                endDate = todayTime;
                findXsUserStatList();
                getMySaleCount();
                break;
            case R.id.rb_the_week:
                startDate = weekDays.get(0).getTime();
                endDate = weekDays.get(weekDays.size() - 1).getTime();
                findXsUserStatList();
                getMySaleCount();
                break;
            case R.id.rb_last_month:
                getDayOfMonth(month);
                findXsUserStatList();
                getMySaleCount();
                break;
            case R.id.rb_last_year:
                startDate = TimeUtils.getTimeOfYearStart();
                endDate = TimeUtils.getTimeOfYearEnd();
                findXsUserStatList();
                getMySaleCount();
                break;
            case R.id.rb_last_custom:
                showPopupWindow();
                break;
            case R.id.rg_radio_button:
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void getMySaleCount() {
        Injection.provideApiService().getMySaleCount(token, startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MySaleCountResponse>() {
                    @Override
                    public void accept(MySaleCountResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            tvSaleNum.setText(response.getData().getSaleCount() + "");
                            tvMoneyNum.setText(response.getData().getSaleMoney() + "");
                        }

                    }
                });
    }

    //30天折线图
    @SuppressLint("CheckResult")
    private void getNearDaySale() {
        getDayOfMonth(month);
        Injection.provideApiService().getNearDaySale(token, startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NearDaySaleResponse>() {
                    @Override
                    public void accept(NearDaySaleResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        xLabel = new String[response.getData().size()];
                        data = new String[response.getData().size()];
                        for (int i = 0; i < response.getData().size(); i++) {
                            xLabel[i] = response.getData().get(i).getCreateDate();
                            data[i] = response.getData().get(i).getSaleMoney() + "";
                        }
                        chartview.setData(data);
                        chartview.setxLabel(xLabel);
                        chartview.fresh();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void findXsUserStatList() {
        sellRankLists.clear();
        Injection.provideApiService().findXsUserStatList(token, startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XsUserStatResponse>() {
                    @Override
                    public void accept(XsUserStatResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            List<XsUserStatResponse.DataBean> dataBeans = response.getData();
                            for (int i = 0; i < dataBeans.size(); i++) {
                                SellRankModel sellRankModel = new SellRankModel(dataBeans.get(i).getUserPic(), dataBeans.get(i).getUserName(),
                                        dataBeans.get(i).getCustomerCount() + "", dataBeans.get(i).getEnterCount() + "",
                                        dataBeans.get(i).getSuccessCount() + "", dataBeans.get(i).getPhoneCount() + "");
                                ItemData itemData = new ItemData(0, SettingDelegate.SELL_RANK_TYPE, sellRankModel);
                                sellRankLists.add(itemData);
                            }
                            baseAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void showPopupWindow() {
        convertView = LayoutInflater.from(this).inflate(R.layout.layout_popup_custom, null);
        rlStartTime = convertView.findViewById(R.id.rl_start_time);
        tvStartTime = convertView.findViewById(R.id.tv_start_time);
        rlStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDatePicker.show(now.split(" ")[0]);
            }
        });
        rlEndTime = convertView.findViewById(R.id.rl_end_time);
        tvEndTime = convertView.findViewById(R.id.tv_end_time);
        rlEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDate2Picker.show(now.split(" ")[0]);
            }
        });
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.showAsDropDown(rbLastCustom, 0, 2);
    }


    private void getDayOfMonth(String datum) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (datum.length() == 2) {
            try {
                Date date = format.parse(Calendar.getInstance().get(Calendar.YEAR) + "-0" + datum.substring(0, 1) + "-01");
                startDate = TimeUtils.getSupportBeginDayofMonth(date);
                endDate = TimeUtils.getSupportEndDayofMonth(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Date date = format.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + datum.substring(0, 2) + "-01");
                startDate = TimeUtils.getSupportBeginDayofMonth(date);
                endDate = TimeUtils.getSupportEndDayofMonth(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
