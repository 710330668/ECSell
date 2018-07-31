package com.example.com.careasysell.dealer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
import com.example.com.careasysell.options.CarDetailActivity;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseFragment;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Author ： DasonYu
 * Date ： 2018/7/27
 * Email Address : 15764240573@163.com
 */

public class NationalSourceFragment extends BaseFragment {

    @BindView(R.id.rl_search_result)
    RecyclerView mSearchResult;
    @BindView(R.id.ll_tab)
    RadioGroup mRadioGroup;
    @BindView(R.id.rb_car_state)
    RadioButton mRbState;
    @BindView(R.id.rb_car_order)
    RadioButton mRbOrder;
    @BindView(R.id.rb_car_filter)
    RadioButton mRbFilter;
    Unbinder unbinder;

    private int selectState = 0;
    private int selectOrder = 0;

    PopupWindow mPopupWindow;

    private List<ItemData> mSearchResultData = new ArrayList<>();

    private static final String TAG = "SearchResultFragment";
    private BaseAdapter adapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_layout_national_source;
    }

    @Override
    public void setView(View rootView) {
        mSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mSearchResult.addItemDecoration(new SpaceItemDecoration(5));
    }

    @Override
    public void initData(Bundle arguments) {
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
        adapter = new BaseAdapter(mSearchResultData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                startActivity(CarDetailActivity.class);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mSearchResult.setAdapter(adapter);
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void unVisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rb_car_state, R.id.rb_car_order, R.id.rb_car_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            汽车状态
            case R.id.rb_car_state:
                showPopupWindow(R.id.rb_car_state);
                break;
//                排序
            case R.id.rb_car_order:
                showPopupWindow(R.id.rb_car_order);
                break;
//                筛选  draw  open
            case R.id.rb_car_filter:
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    DrawerLayout mainDrawerLayout = (DrawerLayout) activity.findViewById(R.id.layout_drawer);
                    LinearLayout mainRightDrawerLayout = (LinearLayout) activity.findViewById(R.id.layout_drawer_right);
                    if (mainDrawerLayout.isDrawerOpen(mainRightDrawerLayout)) {
                        mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
                    } else {
                        mainDrawerLayout.openDrawer(mainRightDrawerLayout);
                    }
                }
                break;
//                上架车辆
            case R.id.tv_put_away_car:
                for (ItemData bean :mSearchResultData) {
                    ((SearchResultModel) bean.getData()).setPut(!((SearchResultModel) bean.getData()).isPut());
                }
                adapter.notifyDataSetChanged();
                break;
            default:
        }
    }

    public void showPopupWindow(final int viewId) {
        RadioGroup convertView = null;

        switch (viewId) {
            case R.id.rb_car_state:
                convertView = (RadioGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_popup_car_state, null);
                ((RadioButton) convertView.getChildAt(selectState)).setChecked(true);
                mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                break;
            case R.id.rb_car_order:
                convertView = (RadioGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_popup_car_order, null);
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
                        case R.id.rb_car_state:
                            selectButton = ((RadioButton) radioGroup.findViewById(i));
                            mRbState.setText(selectButton.getText());
                            selectState = radioGroup.indexOfChild(selectButton);
                            mPopupWindow.dismiss();
                            break;
                        case R.id.rb_car_order:
                            selectButton = ((RadioButton) radioGroup.findViewById(i));
                            mRbOrder.setText(selectButton.getText());
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
        mPopupWindow.showAsDropDown(mRadioGroup, 0, 2);
    }
}
