package com.cheeshou.cheeshou.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.activity.SalerManagerActivity;
import com.cheeshou.cheeshou.options.CarDetailActivity;
import com.cheeshou.cheeshou.order.response.OrderListResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.EndlessRecyclerOnScrollListener;
import com.cheeshou.cheeshou.view.DrawableCenterRadioButton;
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
 * Created by 71033 on 2018/8/9.
 */
public class OrderListActivity extends BaseActivity {

    @BindView(R.id.rl_search_result)
    RecyclerView rlSearchResult;
    @BindView(R.id.rb_default_sort)
    DrawableCenterRadioButton rbDefaultSort;
    @BindView(R.id.rb_sale_time)
    DrawableCenterRadioButton rbSaleTime;
    @BindView(R.id.rb_deal_valence)
    DrawableCenterRadioButton rbDealValence;
    @BindView(R.id.rb_sales)
    DrawableCenterRadioButton rbSales;
    @BindView(R.id.ll_tab)
    RadioGroup llTab;
    @BindView(R.id.et_search)
    EditText etSearch;
    private List<ItemData> mSearchResultData = new ArrayList<>();
    private BaseAdapter mDataAdapter;
    private int selectState = 0;
    private int selectOrder = 0;
    private String token;
    private int CURRENT_PAGE = 1;
    private int PAGE_SIZE = 12;
    private int count;
    private String orderType;
    private static final int REQUEST_CLIENT = 1;
    private String xsUserId;
    private String startDate, endDate;
    private String minPrice, maxPrice;


    PopupWindow mPopupWindow;


    @Override
    public int bindLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlSearchResult.setLayoutManager(layoutManager);
        rlSearchResult.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rlSearchResult.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mDataAdapter.setLoadState(mDataAdapter.LOADING);
                if (mSearchResultData.size() < count) {
                    ++CURRENT_PAGE;
                    getOrderList();
                } else {
                    mDataAdapter.setLoadState(mDataAdapter.LOADING_END);
                }
            }
        });
        mDataAdapter = new BaseAdapter(mSearchResultData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                OrderListResponse.DataBean.ListsBean model = (OrderListResponse.DataBean.ListsBean) data;
                Bundle bundle = new Bundle();
                bundle.putString("orderItemId", model.getOrderItemId());
                bundle.putString("source", C.SOURCE_ORDER);
                startActivity(CarDetailActivity.class, bundle);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlSearchResult.setAdapter(mDataAdapter);
        getOrderList();
    }

    @SuppressLint("CheckResult")
    private void getOrderList() {
        if (mSearchResultData.size() > 0) {
            mSearchResultData.remove(mSearchResultData.size() - 1);
        }
        Injection.provideApiService().findMyOrderList(token, CURRENT_PAGE + "", PAGE_SIZE + "", xsUserId,
                startDate, endDate, "", "", etSearch.getText().toString(), orderType).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderListResponse>() {
                    @Override
                    public void accept(OrderListResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            count = response.getData().getCount();
                            for (int i = 0; i < response.getData().getLists().size(); i++) {
                                OrderListResponse.DataBean.ListsBean bean = response.getData().getLists().get(i);
                                ItemData e = new ItemData(0, SettingDelegate.ORDER_LIST_TYPE, bean);
                                mSearchResultData.add(e);
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            mSearchResultData.add(e);
                            mDataAdapter.notifyDataSetChanged();
                            mDataAdapter.setLoadState(mDataAdapter.LOADING_COMPLETE);
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

    @OnClick({R.id.iv_back, R.id.et_search, R.id.rb_default_sort, R.id.rb_sale_time, R.id.rb_deal_valence, R.id.rb_sales, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_search:
                break;
            case R.id.rb_default_sort:
                showPopupWindow(R.id.rb_default_sort);
                break;
            case R.id.rb_sale_time:
                showPopupWindow(R.id.rb_sale_time);
                break;
            case R.id.rb_deal_valence:
                showPopupWindow(R.id.rb_deal_valence);
                break;
            case R.id.rb_sales:
                Intent intent = new Intent(OrderListActivity.this, SalerManagerActivity.class);
                startActivityForResult(intent, REQUEST_CLIENT);
                break;
            case R.id.tv_search:
                mSearchResultData.clear();
                CURRENT_PAGE = 1;
                getOrderList();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CLIENT) {
                rbSales.setText(data.getStringExtra("saleName"));
                xsUserId = data.getStringExtra("xsUserId");
                mSearchResultData.clear();
                CURRENT_PAGE = 1;
                getOrderList();
            }
        }
    }

    public void showPopupWindow(final int viewId) {
        RadioGroup convertView = null;

        switch (viewId) {
            case R.id.rb_default_sort:
                convertView = (RadioGroup) LayoutInflater.from(this).inflate(R.layout.layout_popup_car_order, null);
                ((RadioButton) convertView.getChildAt(selectState)).setChecked(true);
                mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                break;
            case R.id.rb_sale_time:
                convertView = (RadioGroup) LayoutInflater.from(this).inflate(R.layout.layout_popup_sale_time, null);
                ((RadioButton) convertView.getChildAt(selectOrder)).setChecked(true);
                mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                break;
            case R.id.rb_deal_valence:
                convertView = (RadioGroup) LayoutInflater.from(this).inflate(R.layout.layout_deal_valence, null);
                ((RadioButton) convertView.getChildAt(selectOrder)).setChecked(true);
                mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                break;
            default:
        }

        if (convertView != null) {
            convertView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton selectButton;
                    switch (viewId) {
                        case R.id.rb_default_sort:
                            selectButton = ((RadioButton) radioGroup.findViewById(i));
                            rbDefaultSort.setText(selectButton.getText());
                            selectState = radioGroup.indexOfChild(selectButton);
                            orderType = (i % 5 == 1 ? "" : i % 5 - 1) + "";
                            mSearchResultData.clear();
                            CURRENT_PAGE = 1;
                            getOrderList();
                            mPopupWindow.dismiss();
                            break;
                        case R.id.rb_sale_time:
                            selectButton = ((RadioButton) radioGroup.findViewById(i));
                            rbSaleTime.setText(selectButton.getText());
                            switch (rbSaleTime.getText().toString()) {
                                case "不限":
                                    startDate = "";
                                    endDate = "";
                                    break;
                                case "昨天":
                                    startDate = TimeUtils.getBeforeTime();
                                    endDate = TimeUtils.getBeforeTime();
                                    break;
                                case "今天":
                                    startDate = TimeUtils.getTodayTime();
                                    endDate = TimeUtils.getTodayTime();
                                    break;
                                case "本周":
                                    startDate = TimeUtils.getTimeOfWeekStart();
                                    endDate = TimeUtils.getTimeOfWeekEnd();
                                    break;
                                case "本月":
                                    startDate = TimeUtils.getTimeOfMonthStart();
                                    endDate = TimeUtils.getTimeOfMonthEnd();
                                    break;
                                case "本年":
                                    startDate = TimeUtils.getTimeOfYearStart();
                                    endDate = TimeUtils.getTimeOfYearEnd();
                                    break;
                                case "更早":
                                    break;
                            }
                            mSearchResultData.clear();
                            CURRENT_PAGE = 1;
                            getOrderList();
                            selectOrder = radioGroup.indexOfChild(selectButton);
                            mPopupWindow.dismiss();
                            break;
                        case R.id.rb_deal_valence:
                            selectButton = ((RadioButton) radioGroup.findViewById(i));
                            rbDealValence.setText(selectButton.getText());
                            switch (rbDealValence.getText().toString()) {
                                case "不限":
                                    minPrice = "";
                                    maxPrice = "";
                                    break;
                                case "5万以下":
                                    minPrice = "";
                                    maxPrice = "";
                                    break;
                                case "5万-10万":
                                    break;
                                case "50-100万":
                                    break;
                                case "100万及以上":
                                    break;
                                default:
                                    break;
                            }
                            selectOrder = radioGroup.indexOfChild(selectButton);
                            mPopupWindow.dismiss();
                            break;
                        default:
                    }
                }
            });
        }

        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.showAsDropDown(llTab, 0, 2);
    }
}
