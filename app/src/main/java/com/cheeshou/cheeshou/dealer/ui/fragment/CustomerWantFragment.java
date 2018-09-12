package com.cheeshou.cheeshou.dealer.ui.fragment;


import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.activity.AllOptionResponse;
import com.cheeshou.cheeshou.dealer.ui.model.CarStateModel;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.StoreManagerResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.options.CarDetailActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.EndlessRecyclerOnScrollListener;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseFragment;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.TimeUtils;

import java.io.Serializable;
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

public class CustomerWantFragment extends BaseFragment {

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
    LinearLayout mLLBottom;
    @BindView(R.id.tv_put_away)
    TextView mTvBottom;
    Unbinder unbinder;

    private String TAG_LOAD_MORE = "tag_load_more";
    private String TAG_FILTER = "tag_filter";


    PopupWindow mPopupWindow;

    private List<ItemData> mSearchResultData = new ArrayList<>();

    private static final String TAG = "StoreManagerItemClickFragment";
    private final int REQUEST_BRAND = 0;
    private BaseAdapter mDataAdapter;
    private String token;
    private int CURRENT_PAGE = 1;
    private int PAGE_SIZE = 12;
    private int count;
    private String carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey, carStatus, orderType;
    StoreManagerResponse.DataBean dataBean;
    private boolean isOpen;
    private ArrayList<SearchResultModel> dataList = new ArrayList<>();
    private List<ItemData> stateData;
    private List<ItemData> orderDate;
    public int INVENTORY = ParamManager.getInstance(getContext()).getChannelType();
    private String scopType = "own";
    private String customerId = "";

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_layout_store_manager_item_click;
    }

    @Override
    public void setView(View rootView) {
        mSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mSearchResult.addItemDecoration(new SpaceItemDecoration(5));
        mLLBottom.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData(Bundle arguments) {
        token = SP.getInstance(C.USER_DB, getActivity()).getString(C.USER_TOKEN);

        if (arguments == null) {
            arguments = new Bundle();
        }
        customerId = arguments.getString("customerId");
        Serializable data = arguments.getSerializable("data");
        if (data != null) {
            dataBean = (StoreManagerResponse.DataBean) data;
        }
        if (dataBean != null) {
            carStatus = dataBean.getCarStatus();
        }
        mDataAdapter = new BaseAdapter(mSearchResultData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                if (data instanceof SearchResultModel) {
                    SearchResultModel model = (SearchResultModel) data;
                    if (isOpen) {
                        Bundle bundle = new Bundle();
                        bundle.putString("carId", model.getId());
                        dataList.clear();
                        dataList.add(model);
                        bundle.putParcelableArrayList("data", dataList);
                        startActivity(CarDetailActivity.class, bundle);
                    } else {
                        model.setPut(!model.isPut());
                        mDataAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mSearchResult.setAdapter(mDataAdapter);
        mSearchResult.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mDataAdapter.setLoadState(mDataAdapter.LOADING);
                if (mSearchResultData.size() < count) {
                    ++CURRENT_PAGE;
                    getOwnOption();
                } else {
                    mDataAdapter.setLoadState(mDataAdapter.LOADING_END);
                }
            }
        });
        if (!TextUtils.isEmpty(customerId)) {
            mTvBottom.setText("确定");
        }
        initPopupWindowData();
        getOwnOption();
    }

    private void initPopupWindowData() {
        switch (INVENTORY) {
            case C.INVENTORY_OPTION:
                stateData = new ArrayList<>();
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("全部", "", true)));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("在售", "IN_SALE")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已上架", "SHELVES")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已预订", "RESERVE")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已售", "OUT_SALE")));

                orderDate = new ArrayList<>();
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("默认排序", "", true)));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("车源价最高", "1")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("车源价最低", "2")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("销售提成最高", "3")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("销售提成最低", "4")));
                break;
            case C.INVENTORY_DEALER:
                stateData = new ArrayList<>();
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("全部", "", true)));
//                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("在售", "IN_SALE")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已上架", "SHELVES")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已预订", "RESERVE")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已售", "OUT_SALE")));

                orderDate = new ArrayList<>();
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("默认排序", "", true)));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("车源价最高", "1")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("车源价最低", "2")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("销售提成最高", "3")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("销售提成最低", "4")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("经销商价格最高", "5")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("经销商价格最低", "6")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("贷款返点最高", "7")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("贷款返点最低", "8")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("保险返点最高", "9")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("保险返点最低", "10")));
                break;
            case C.INVENTORY_MARKET:
                stateData = new ArrayList<>();
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("全部", "", true)));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("在售", "IN_SALE")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已上架", "SHELVES")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已预订", "RESERVE")));
                stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已售", "OUT_SALE")));

                orderDate = new ArrayList<>();
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("默认排序", "", true)));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("车源价最高", "1")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("车源价最低", "2")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("销售提成最高", "3")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("销售提成最低", "4")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("经销商价格最高", "5")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("经销商价格最低", "6")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("贷款返点最高", "7")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("贷款返点最低", "8")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("保险返点最高", "9")));
                orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("保险返点最低", "10")));
                break;
        }
    }

    @Override
    public void onLazyLoad() {

    }

    @SuppressLint("CheckResult")
    private void getOwnOption() {
        if (mSearchResultData.size() > 0) {
            mSearchResultData.remove(mSearchResultData.size() - 1);
        }
        Injection.provideApiService().getCarList(token, PAGE_SIZE + "", CURRENT_PAGE + "", scopType,
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
                                data.setSaleId(response.getData().getLists().get(i).getSaleId());
                                data.setDate(TimeUtils.millis2String(response.getData().getLists().get(i).getCreateDate()));
                                data.setDeduct("销售提成" + response.getData().getLists().get(i).getSaleCommission() + "元");
                                data.setPrice("车源价" + response.getData().getLists().get(i).getCarPrice() + "万");
                                data.setState(response.getData().getLists().get(i).getCarStatusName());
                                data.setAdvicePrice(response.getData().getLists().get(i).getAdvicePrice() + "");
                                data.setOpenPutEntrance(true);
                                switch (INVENTORY) {
                                    case C.INVENTORY_DEALER:
                                        data.setSubTitle("分享" + response.getData().getLists().get(i).getShareNum() + "次|浏览" +
                                                response.getData().getLists().get(i).getBrowseNum() + "次");
                                        break;
                                    case C.INVENTORY_MARKET:
                                        data.setSubTitle("分享" + response.getData().getLists().get(i).getShareNum() + "次|浏览" +
                                                response.getData().getLists().get(i).getBrowseNum() + "次");
                                        break;
                                    default:
                                        data.setSubTitle("上架" + response.getData().getLists().get(i).getShelvesNum() + "次|分享" + response.getData().getLists().get(i).getShareNum() + "次|浏览" +
                                                response.getData().getLists().get(i).getBrowseNum() + "次");
                                }
                                data.setTitle(response.getData().getLists().get(i).getBrand() + "-" + response.getData().getLists().get(i).getVname());
                                data.setImageUrl(response.getData().getLists().get(i).getImgThumUrl());
                                data.setId(response.getData().getLists().get(i).getCarId());
                                ItemData e = new ItemData(0, SettingDelegate.SEARCH_RESULT_TYPE, data);
                                mSearchResultData.add(e);
                            }
                            ItemData e = new ItemData(0, SettingDelegate.FOOT_TYPE, "");
                            mSearchResultData.add(e);
                            mDataAdapter.notifyDataSetChanged();
                            mDataAdapter.setLoadState(mDataAdapter.LOADING_COMPLETE);
                        }
                        else if(response.getCode() == 402||response.getCode() == 401){
                            //token失效
                            SP.getInstance(C.USER_DB,getActivity()).put(C.USER_ACCOUNT,"");
                            SP.getInstance(C.USER_DB,getActivity()).put(C.USER_PASSWORD,"");
                            getActivity().finish();
                            startActivity(LoginActivity.class);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SP.getInstance(C.USER_DB,getActivity()).put(C.USER_ACCOUNT,"");
                        SP.getInstance(C.USER_DB,getActivity()).put(C.USER_PASSWORD,"");
                        getActivity().finish();
                        startActivity(LoginActivity.class);
                    }
                });
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

    @OnClick({R.id.rb_car_state, R.id.rb_car_order, R.id.rb_car_filter, R.id.tv_put_away})
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
            case R.id.tv_put_away:
                dataList.clear();
                for (ItemData bean : mSearchResultData) {
                    if (bean.getData() instanceof SearchResultModel && ((SearchResultModel) bean.getData()).isPut()) {
                        dataList.add((SearchResultModel) bean.getData());
                    }
                }
                String s = "";
                for (SearchResultModel bean : dataList) {
                    s += (bean.getSaleId() + ",");
                }
                final String finalS = s;
                Injection.provideApiService().updateCustomerNeedInfo(token, customerId, s).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<EasyResponse>() {
                    @Override
                    public void accept(EasyResponse easyResponse) throws Exception {
                        if (easyResponse != null && easyResponse.getCode() == 200) {
                            ParamManager.getInstance(getContext()).setCreateCustomerWantCarId(finalS);
                            ParamManager.getInstance(getContext()).setCustomerWantList(dataList);
                            Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        } else if(easyResponse.getCode() == 402||easyResponse.getCode() == 401){
                            //token失效
                            SP.getInstance(C.USER_DB,getActivity()).put(C.USER_ACCOUNT,"");
                            SP.getInstance(C.USER_DB,getActivity()).put(C.USER_PASSWORD,"");
                            getActivity().finish();
                            startActivity(LoginActivity.class);
                        }else {
                            Toast.makeText(getActivity(), easyResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SP.getInstance(C.USER_DB,getActivity()).put(C.USER_ACCOUNT,"");
                        SP.getInstance(C.USER_DB,getActivity()).put(C.USER_PASSWORD,"");
                        getActivity().finish();
                        startActivity(LoginActivity.class);
                    }
                });
                break;
            default:
        }
    }

    public void showPopupWindow(final int viewId) {
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        switch (viewId) {
            case R.id.rb_car_state:
                LinearLayout convertFrame = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_popup_car_state_recycler, null);
                RecyclerView stateRecycler = (RecyclerView) convertFrame.findViewById(R.id.recycler_car_state);
                initStateRecycler(stateRecycler);
                mPopupWindow = new PopupWindow(convertFrame, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setBackgroundDrawable(dw);
                mPopupWindow.showAsDropDown(mRadioGroup, 0, 0);
                break;
            case R.id.rb_car_order:
                LinearLayout convertLinear = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_popup_car_order_recycler, null);
                RecyclerView orderRecycler = (RecyclerView) convertLinear.findViewById(R.id.recycler_car_order);
                orderRecycler.addItemDecoration(new SpaceItemDecoration(5));
                initOrderRecycler(orderRecycler);
                mPopupWindow = new PopupWindow(convertLinear, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setBackgroundDrawable(dw);
                mPopupWindow.showAsDropDown(mRadioGroup, 0, 0);
                break;
            default:
        }
    }

    /**
     * 初始化 状态recycler
     *
     * @param stateRecycler
     */
    private void initStateRecycler(RecyclerView stateRecycler) {
        stateRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
        stateRecycler.setAdapter(new BaseAdapter(stateData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                for (ItemData bean : stateData) {
                    ((CarStateModel) bean.getData()).setSelected(false);
                }
                if (data instanceof CarStateModel) {
                    ((CarStateModel) data).setSelected(true);
                    carStatus = ((CarStateModel) data).getStateCode();
                    mRbState.setText(((CarStateModel) data).getStateName());
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                mSearchResultData.clear();
                getOwnOption();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        }));
    }

    /**
     * 初始化 order recycler
     *
     * @param orderRecycler
     */
    private void initOrderRecycler(RecyclerView orderRecycler) {
        orderRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        orderRecycler.setAdapter(new BaseAdapter(orderDate, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                for (ItemData bean : orderDate) {
                    ((CarStateModel) bean.getData()).setSelected(false);
                }
                if (data instanceof CarStateModel) {
                    ((CarStateModel) data).setSelected(true);
                    orderType = ((CarStateModel) data).getStateCode();
                    mRbOrder.setText(((CarStateModel) data).getStateName());
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                mSearchResultData.clear();
                getOwnOption();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        }));
    }


    public void setShareOpen() {
        boolean openPutEntrance = false;
        for (ItemData bean : mSearchResultData) {
            if (bean.getData() instanceof SearchResultModel) {
                ((SearchResultModel) bean.getData()).setOpenPutEntrance(!((SearchResultModel) bean.getData()).isOpenPutEntrance());
                isOpen = ((SearchResultModel) bean.getData()).isOpenPutEntrance();
                openPutEntrance = ((SearchResultModel) bean.getData()).isOpenPutEntrance();
            }
        }
        mLLBottom.setVisibility(View.VISIBLE);
        mDataAdapter.notifyDataSetChanged();

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
        mSearchResultData.clear();
        getOwnOption();
    }

}
