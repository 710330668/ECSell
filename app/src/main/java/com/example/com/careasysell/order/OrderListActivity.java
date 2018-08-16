package com.example.com.careasysell.order;

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

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.activity.SalerManagerActivity;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
import com.example.com.careasysell.options.CarDetailActivity;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.DrawableCenterRadioButton;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private List<ItemData> mSearchResultData = new ArrayList<>();
    private BaseAdapter mDataAdapter;
    private int selectState = 0;
    private int selectOrder = 0;

    PopupWindow mPopupWindow;


    @Override
    public int bindLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initParams(Bundle params) {
        for (int i = 0; i < 10; i++) {
            SearchResultModel data = new SearchResultModel();
            data.setDate("2018/06/24");
            data.setDeduct("销售提成2000");
            data.setPrice("16.8万");
            data.setState("已上架");
            data.setSubTitle("分享20次|浏览140次");
            data.setTitle("雪佛兰2013款  科鲁兹  16LSL天地板MT");
            ItemData e = new ItemData(0, SettingDelegate.SEARCH_RESULT_TYPE, data);
            mSearchResultData.add(e);
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlSearchResult.setLayoutManager(layoutManager);
        rlSearchResult.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDataAdapter = new BaseAdapter(mSearchResultData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                startActivity(CarDetailActivity.class);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlSearchResult.setAdapter(mDataAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.et_search, R.id.rb_default_sort, R.id.rb_sale_time, R.id.rb_deal_valence, R.id.rb_sales})
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
                startActivity(SalerManagerActivity.class);
                break;
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
                            mPopupWindow.dismiss();
                            break;
                        case R.id.rb_sale_time:
                            selectButton = ((RadioButton) radioGroup.findViewById(i));
                            rbSaleTime.setText(selectButton.getText());
                            selectOrder = radioGroup.indexOfChild(selectButton);
                            mPopupWindow.dismiss();
                            break;
                        case R.id.rb_deal_valence:
                            selectButton = ((RadioButton) radioGroup.findViewById(i));
                            rbDealValence.setText(selectButton.getText());
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
