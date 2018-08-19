package com.example.com.careasysell.dealer.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.activity.AllOptionResponse;
import com.example.com.careasysell.dealer.ui.activity.PutAwayDetailActivity;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
import com.example.com.careasysell.options.CarDetailActivity;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.EndlessRecyclerOnScrollListener;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseFragment;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.ll_put_away)
    LinearLayout mLinearPut;
    @BindView(R.id.tv_put_away_car)
    TextView mTvPutCar;
    @BindView(R.id.tv_put_away)
    TextView mTvBottom;
    Unbinder unbinder;

    @C.INVENTORY
    public int INVENTORY = C.INVENTORY_OPTION;

    private int selectState = 0;
    private int selectOrder = 0;

    PopupWindow mPopupWindow;

    private List<ItemData> mSearchResultData = new ArrayList<>();
    private ArrayList<SearchResultModel> dataList = new ArrayList<>();

    private String TAG_LOAD_MORE = "tag_load_more";
    private String TAG_FILTER = "tag_filter";

    private static final String TAG = "SearchResultFragment";
    private BaseAdapter mDataAdapter;
    private String token;

    private int CURRENT_PAGE = 1;
    private int PAGE_SIZE = 6;
    private int count;
    private String carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey, carStatus, orderType;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_layout_national_source;
    }

    @Override
    public void setView(View rootView) {
        mSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mSearchResult.addItemDecoration(new SpaceItemDecoration(5));
        mSearchResult.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mDataAdapter.setLoadState(mDataAdapter.LOADING);
                if (mSearchResultData.size() < count) {
                    ++CURRENT_PAGE;
                    initRecycler();
                } else {
                    mDataAdapter.setLoadState(mDataAdapter.LOADING_END);
                }
            }
        });
    }

    @Override
    public void initData(Bundle arguments) {
        INVENTORY = arguments.getInt(C.TAG_PAGE_MAIN);
        token = SP.getInstance(C.USER_DB, getActivity()).getString(C.USER_TOKEN);
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
        mSearchResult.setAdapter(mDataAdapter);
        initRecycler();
        switch (INVENTORY) {
            case C.INVENTORY_MARKET:
                //销售 分享
                mTvPutCar.setText("分享车辆");
                mTvBottom.setText("分享选中车辆");
                break;
            case C.INVENTORY_DEALER:
//                经销商  上架
                mTvPutCar.setText("上架车辆");
                mTvBottom.setText("上架选中车辆");
                break;
            default:
        }
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

    @OnClick({R.id.rb_car_state, R.id.rb_car_order, R.id.rb_car_filter, R.id.tv_put_away_car, R.id.ll_put_away})
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
                for (ItemData bean : mSearchResultData) {
                    if (bean.getData() instanceof SearchResultModel) {
                        ((SearchResultModel) bean.getData()).setOpenPutEntrance(!((SearchResultModel) bean.getData()).isOpenPutEntrance());
//                    mTvPutAway.setText(((SearchResultModel) bean.getData()).isOpenPutEntrance() ? "取消" : "上架车辆");
                        mLinearPut.setVisibility(((SearchResultModel) bean.getData()).isOpenPutEntrance() ? View.VISIBLE : View.GONE);
                    }
                }
                mDataAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_put_away:
                for (ItemData bean : mSearchResultData) {
                    if (bean.getData() instanceof SearchResultModel && ((SearchResultModel) bean.getData()).isPut()) {
                        dataList.add((SearchResultModel) bean.getData());
                    }
                }
                switch (INVENTORY) {
                    case C.INVENTORY_MARKET:
                        //todo销售 分享
                        break;
                    case C.INVENTORY_DEALER:
//                经销商  上架
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("data", dataList);
                        startActivity(PutAwayDetailActivity.class, bundle);
                        break;
                    default:
                }
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
                            switch (mRbState.getText().toString()) {
                                case "全部":
                                    carStatus = "";
                                    break;
                                case "在售":
                                    carStatus = "IN_SALE";
                                    break;
                                case "已上架":
                                    carStatus = "SHELVES";
                                    break;
                                case "已预定":
                                    carStatus = "RESERVE";
                                    break;
                            }
                            selectState = radioGroup.indexOfChild(selectButton);
                            CURRENT_PAGE = 1;
                            mSearchResultData.clear();
                            initRecycler();
                            mPopupWindow.dismiss();
                            break;
                        case R.id.rb_car_order:
                            selectButton = ((RadioButton) radioGroup.findViewById(i));
                            mRbOrder.setText(selectButton.getText());
                            selectOrder = radioGroup.indexOfChild(selectButton);
                            orderType = (i % 5 == 1 ? "" : i % 5 - 1) + "";
                            CURRENT_PAGE = 1;
                            mSearchResultData.clear();
                            initRecycler();
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

    @SuppressLint("CheckResult")
    private void initRecycler() {
        if (mSearchResultData.size() > 0) {
            mSearchResultData.remove(mSearchResultData.size() - 1);
        }
        Injection.provideApiService().getCarList(token, PAGE_SIZE + "", CURRENT_PAGE + "", "own",
                carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice,
                startDate, endDate, queryKey, carStatus, orderType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AllOptionResponse>() {
                    @Override
                    public void accept(AllOptionResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            count = response.getData().getCount();
                            for (int i = 0; i < response.getData().getLists().size(); i++) {
                                SearchResultModel data = new SearchResultModel();
                                data.setDate(response.getData().getLists().get(i).getCreateDate() + "");
                                data.setDeduct("销售提成" + response.getData().getLists().get(i).getSaleCommission() + "元");
                                data.setPrice(response.getData().getLists().get(i).getBrowseNum() + "万");
                                data.setState(response.getData().getLists().get(i).getCarStatusName());
                                data.setSubTitle("分享" + response.getData().getLists().get(i).getShareNum() + "次|浏览" + response.getData().getLists().get(i).getBrowseNum() + "次");
                                data.setTitle(response.getData().getLists().get(i).getVname());
                                data.setImageUrl(response.getData().getLists().get(i).getImgThumUrl());
                                data.setId(response.getData().getLists().get(i).getCarId());
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


    public void filterRecycler(String carType, String brandId, String versionId, String carYear, String outsiteColor, String withinColor, String minCarPrice, String maxCarPrice, String startDate, String endDate, String queryKey) {
        this.carType = carType;
        this.brandId = brandId;
        this.versionId = versionId;
        this.carYear = carYear;
        this.outsiteColor = outsiteColor;
        this.withinColor = withinColor;
        this.minCarPrice = minCarPrice;
        this.maxCarPrice = maxCarPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.queryKey = queryKey;
        initRecycler();
    }
}
