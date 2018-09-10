package com.cheeshou.cheeshou.dealer.ui.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CarStateModel;
import com.cheeshou.cheeshou.dealer.ui.model.ColorFilterModel;
import com.cheeshou.cheeshou.dealer.ui.model.PriceModel;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.market.ui.MarketShareCarActivity;
import com.cheeshou.cheeshou.options.CarDetailActivity;
import com.cheeshou.cheeshou.options.ChooseAreaActivity;
import com.cheeshou.cheeshou.options.ChooseBrandActivity;
import com.cheeshou.cheeshou.options.ChooseCarsActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.EndlessRecyclerOnScrollListener;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.TimeUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.cheeshou.cheeshou.R.layout.item_drawer_filter_racter;

public class NationSourceActivity extends BaseActivity {

    @BindView(R.id.tag_flow_price)
    TagFlowLayout mTagFlowPrice;
    @BindView(R.id.tag_flow_time)
    TagFlowLayout mTagFlowTime;
    @BindView(R.id.tag_flow_out_color)
    TagFlowLayout mTagFlowColorOut;
    @BindView(R.id.tag_flow_inside_color)
    TagFlowLayout mTagFlowColorInside;
    //    @BindView(R.id.tag_flow_car_type)
//    TagFlowLayout mTagFlowType;
    @BindView(R.id.tag_flow_car_source_type)
    TagFlowLayout mTagFlowSourceType;
    @BindView(R.id.layout_drawer)
    DrawerLayout mDrawerMain;
    @BindView(R.id.layout_drawer_right)
    LinearLayout mDrawerRight;
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
    @BindView(R.id.tv_open_put_away)
    TextView mTvPutAway;
    @BindView(R.id.ll_put_away)
    LinearLayout mLinearPut;
    @BindView(R.id.et_search)
    EditText mEditSearch;
    @BindView(R.id.tv_sales_area)
    TextView mTvSelectArea;
    @BindView(R.id.tv_car_brand)
    TextView mTvSelectBrand;
    @BindView(R.id.tv_car_model)
    TextView mTvSelectedCar;
    @BindView(R.id.tv_year_all)
    TextView mTvSelectYear;
    @BindView(R.id.ll_choose_year)
    LinearLayout mLLChooseYear;
    @BindView(R.id.img_last)
    ImageView mImgLastYear;
    @BindView(R.id.img_next)
    ImageView mImgNextYear;
    @BindView(R.id.tv_year)
    TextView mTvYear;
    private int selectState = 0;
    private int selectOrder = 0;
    @BindView(R.id.tv_options_type)
    TextView mCarTypeName;
    @BindView(R.id.et_min_money)
    EditText mEtMinMoney;
    @BindView(R.id.et_max_money)
    EditText mEtMaxMoney;

    @C.INVENTORY
    public int INVENTORY = C.INVENTORY_OPTION;
    PopupWindow mPopupWindow;

    private List<ItemData> mSearchResultData = new ArrayList<>();
    private ArrayList<SearchResultModel> mPutAwayData = new ArrayList<>();

    private static final String TAG = "NationSourceActivity";
    private final int REQUEST_BRAND = 0;
    private final int REQUEST_AREA = 1;
    private final int REQUEST_CAR = 2;
    private final int REQUEST_CAR_TYPE = 3;
    private BaseAdapter mDataAdapter;
    private String token;
    private int CURRENT_PAGE = 1;
    private int PAGE_SIZE = 12;
    private int count;
    private String carType, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey, carStatus, orderType;
    private String brandId = "";
    private boolean isOpen;
    private String carBrand;
    private List<ItemData> stateData;
    private List<ItemData> orderDate;

    @Override
    public int bindLayout() {
        return R.layout.activity_nation_source;
    }

    @Override
    public void initParams(Bundle params) {
        INVENTORY = ParamManager.getInstance(this).getChannelType();
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mEtMinMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                minCarPrice = mEtMinMoney.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEtMaxMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                maxCarPrice = mEtMaxMoney.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSearchResult.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSearchResult.addItemDecoration(new SpaceItemDecoration(5));
        mSearchResult.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mDataAdapter.setLoadState(mDataAdapter.LOADING);
                if (mSearchResultData.size() < count) {
                    ++CURRENT_PAGE;
                    getAllOptions();
                } else {
                    mDataAdapter.setLoadState(mDataAdapter.LOADING_END);
                }
            }
        });
        switch (INVENTORY) {
            case C.INVENTORY_MARKET:
                mTvPutAway.setText("分享车辆");
                break;
            case C.INVENTORY_DEALER:
                mTvPutAway.setText("上架车辆");
                break;
            default:
        }
        // TODO: 2018/8/18 搜索内容
        mEditSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                queryKey = mEditSearch.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        mDataAdapter = new BaseAdapter(mSearchResultData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                SearchResultModel model = (SearchResultModel) data;
                mPutAwayData.clear();
                mPutAwayData.add(model);
                if (!isOpen) {
                    Bundle bundle = new Bundle();
                    bundle.putString("carId", model.getId());
                    bundle.putString("dealer_source", "nationSource");
                    bundle.putParcelableArrayList("shelves_data", mPutAwayData);
                    startActivity(CarDetailActivity.class, bundle);
                } else {
                    model.setPut(!model.isPut());
                    mDataAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mSearchResult.setAdapter(mDataAdapter);

        initPopupWindowData();
        //获取全国车源
        getAllOptions();
        initDrawerTagList();
    }

    private void initPopupWindowData() {
        stateData = new ArrayList<>();
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("全部", "", true)));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("在售", "IN_SALE")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已上架", "SHELVES")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已预订", "RESERVE")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已售", "OUT_SALE")));

        orderDate = new ArrayList<>();
        orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("默认排序", "", true)));
        orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("车原价最高", "1")));
        orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("车原价最低", "2")));
        orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("销售提成最高", "3")));
        orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("销售提成最低", "4")));

    }

    @SuppressLint("CheckResult")
    private void getAllOptions() {
        if (mSearchResultData.size() > 0) {
            mSearchResultData.remove(mSearchResultData.size() - 1);
        }
        Injection.provideApiService().getCarList(token, PAGE_SIZE + "", CURRENT_PAGE + "", "all",
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
                                data.setDate(TimeUtils.millis2String(response.getData().getLists().get(i).getCreateDate()));
                                data.setDeduct("销售提成" + response.getData().getLists().get(i).getSaleCommission() + "元");
                                data.setPrice("车源价" + response.getData().getLists().get(i).getCarPrice() + "万");
                                data.setState(response.getData().getLists().get(i).getCarStatusName());
                                data.setSubTitle(response.getData().getLists().get(i).getCarUserName() + "|" + response.getData().getLists().get(i).getProvinceName() + response.getData().getLists().get(i).getCityName()
                                        + response.getData().getLists().get(i).getSaleArea());
                                data.setTitle(response.getData().getLists().get(i).getBrand() + "-" + response.getData().getLists().get(i).getVname());
                                data.setImageUrl(response.getData().getLists().get(i).getImgThumUrl());
                                data.setId(response.getData().getLists().get(i).getCarId());
                                data.setSaleId(response.getData().getLists().get(i).getSaleId());
                                data.setAdvicePrice(response.getData().getLists().get(i).getAdvicePrice() + "");
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

    private void initDrawerTagList() {
        final List<PriceModel> dataSize = new ArrayList<>();
        dataSize.add(new PriceModel("", "", "不限"));
        dataSize.add(new PriceModel("", "5", "5万以下"));
        dataSize.add(new PriceModel("5", "10", "5-10万"));
        dataSize.add(new PriceModel("10", "15", "10-15万"));
        dataSize.add(new PriceModel("15", "30", "15-30万"));
        dataSize.add(new PriceModel("30", "50", "30-50万"));
        dataSize.add(new PriceModel("50", "100", "50-100万"));
        dataSize.add(new PriceModel("100", "", "100万及以上"));
        TagAdapter<PriceModel> priceAdapter = new TagAdapter<PriceModel>(dataSize) {
            @Override
            public View getView(FlowLayout parent, int position, PriceModel o) {
                TextView textView = (TextView) getLayoutInflater().inflate(item_drawer_filter_racter, mTagFlowPrice, false);
                textView.setText(o.getText());
                return textView;
            }
        };
        priceAdapter.setSelectedList(0);
        mTagFlowPrice.setAdapter(priceAdapter);
        mTagFlowPrice.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                PriceModel priceModel = dataSize.get(position);
                minCarPrice = priceModel.getMinPrice();
                maxCarPrice = priceModel.getMaxPrice();
                return false;
            }
        });


        List<String> dataTime = new ArrayList<>();
        dataTime.add("不限");
        dataTime.add("1天内");
        dataTime.add("3天内");
        dataTime.add("1周内");
        dataTime.add("2周内");
        dataTime.add("1月内");
        TagAdapter<String> timeAdapter = new TagAdapter<String>(dataTime) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) getLayoutInflater().inflate(item_drawer_filter_racter, mTagFlowTime, false);
                textView.setText(o);
                return textView;
            }
        };
        timeAdapter.setSelectedList(0);
        mTagFlowTime.setAdapter(timeAdapter);

        final List<ColorFilterModel> dataColorOut = new ArrayList<>();
        dataColorOut.add(new ColorFilterModel("不限", ""));
        dataColorOut.add(new ColorFilterModel("黑色", "#333333"));
        dataColorOut.add(new ColorFilterModel("白色", "#E6E6E6"));
        dataColorOut.add(new ColorFilterModel("灰色", "#BBBBBB"));
        dataColorOut.add(new ColorFilterModel("红色", "#E51C23"));
        dataColorOut.add(new ColorFilterModel("蓝色", "#3F51B5"));
        dataColorOut.add(new ColorFilterModel("绿色", "#259B24"));
        dataColorOut.add(new ColorFilterModel("黄色", "#FFE700"));
        dataColorOut.add(new ColorFilterModel("香槟色", "#EEDFA1"));
        dataColorOut.add(new ColorFilterModel("紫色", "#A20F89"));
        dataColorOut.add(new ColorFilterModel("银色", "#F3F5F5"));
        dataColorOut.add(new ColorFilterModel("其他", ""));

        TagAdapter<ColorFilterModel> outColorAdapter = new TagAdapter<ColorFilterModel>(dataColorOut) {
            @Override
            public View getView(FlowLayout parent, int position, ColorFilterModel o) {
                LinearLayout linear = (LinearLayout) getLayoutInflater().inflate(R.layout.item_drawer_filter_color, mTagFlowColorOut, false);
                TextView colorText = (TextView) linear.findViewById(R.id.tv_color_name);
                colorText.setText(o.getText());
                final ImageView colorImg = (ImageView) (linear.findViewById(R.id.img_color_apparence));
                if (!TextUtils.isEmpty(o.getColor())) {
                    colorImg.setBackgroundColor(Color.parseColor(o.getColor()));
                } else {
                    colorImg.setVisibility(View.GONE);
                }
                return linear;
            }
        };
        outColorAdapter.setSelectedList(0);
        mTagFlowColorOut.setAdapter(outColorAdapter);
        mTagFlowColorOut.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ColorFilterModel colorFilterModel = dataColorOut.get(position);
                if (!TextUtils.isEmpty(colorFilterModel.getColor())) {
                    outsiteColor = colorFilterModel.getText();
                } else {
                    outsiteColor = "";
                }
                return false;
            }
        });


        final List<ColorFilterModel> dataColorInside = new ArrayList<>();
        dataColorInside.add(new ColorFilterModel("不限", ""));
        dataColorInside.add(new ColorFilterModel("黑色", "#333333"));
        dataColorInside.add(new ColorFilterModel("白色", "#E6E6E6"));
        dataColorInside.add(new ColorFilterModel("灰色", "#BBBBBB"));
        dataColorInside.add(new ColorFilterModel("红色", "#E51C23"));
        dataColorInside.add(new ColorFilterModel("蓝色", "#3F51B5"));
        dataColorInside.add(new ColorFilterModel("绿色", "#259B24"));
        dataColorInside.add(new ColorFilterModel("黄色", "#FFE700"));
        dataColorInside.add(new ColorFilterModel("香槟色", "#EEDFA1"));
        dataColorInside.add(new ColorFilterModel("紫色", "#A20F89"));
        dataColorInside.add(new ColorFilterModel("银色", "#F3F5F5"));
        dataColorInside.add(new ColorFilterModel("其他", ""));

        TagAdapter<ColorFilterModel> insideColorAdapter = new TagAdapter<ColorFilterModel>(dataColorInside) {
            @Override
            public View getView(FlowLayout parent, int position, ColorFilterModel o) {
                LinearLayout linear = (LinearLayout) getLayoutInflater().inflate(R.layout.item_drawer_filter_color, mTagFlowColorInside, false);
                TextView colorText = (TextView) linear.findViewById(R.id.tv_color_name);
                colorText.setText(o.getText());
                final ImageView colorImg = (ImageView) (linear.findViewById(R.id.img_color_apparence));
                if (!TextUtils.isEmpty(o.getColor())) {
                    colorImg.setBackgroundColor(Color.parseColor(o.getColor()));
                } else {
                    colorImg.setVisibility(View.GONE);
                }
                return linear;
            }
        };
        insideColorAdapter.setSelectedList(0);
        mTagFlowColorInside.setAdapter(insideColorAdapter);
        mTagFlowColorInside.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ColorFilterModel colorFilterModel = dataColorInside.get(position);
                if (!TextUtils.isEmpty(colorFilterModel.getColor())) {
                    withinColor = colorFilterModel.getText();
                } else {
                    withinColor = "";
                }
                return false;
            }
        });

//        List<String> dataCarType = new ArrayList<>();
//        dataCarType.add("不限");
//        dataCarType.add("SUV");
//        dataCarType.add("MPV");
//        dataCarType.add("微小型车");
//        dataCarType.add("紧凑型车");
//        dataCarType.add("中型车");
//        dataCarType.add("中大型车");
//        dataCarType.add("跑车");
//        dataCarType.add("两厢车");
//        dataCarType.add("三厢车");
//
//        TagAdapter<String> carTypeAdapter = new TagAdapter<String>(dataCarType) {
//            @Override
//            public View getView(FlowLayout parent, int position, String o) {
//                TextView textView = (TextView) getLayoutInflater().inflate(item_drawer_filter_racter, mTagFlowType, false);
//                textView.setText(o);
//                return textView;
//            }
//        };
//        carTypeAdapter.setSelectedList(0);
//        mTagFlowType.setAdapter(carTypeAdapter);

        final List<String> dataSourceCarType = new ArrayList<>();
        dataSourceCarType.add("全部");
        dataSourceCarType.add("国产-现车");
        dataSourceCarType.add("中规-现车");
        dataSourceCarType.add("中规-期货");
        dataSourceCarType.add("美版-现车");
        dataSourceCarType.add("美版-期货");
        dataSourceCarType.add("中东-现车");
        dataSourceCarType.add("中东-期货");
        dataSourceCarType.add("加版-现车");
        dataSourceCarType.add("加版-期货");
        dataSourceCarType.add("欧版-现车");
        dataSourceCarType.add("欧版-期货");
        dataSourceCarType.add("墨西哥版-现车");
        dataSourceCarType.add("墨西哥版-期货");

        TagAdapter<String> sourceCarTypeAdapter = new TagAdapter<String>(dataSourceCarType) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_drawer_filter_source_type, mTagFlowSourceType, false);
                textView.setText(o);
                return textView;
            }
        };
        sourceCarTypeAdapter.setSelectedList(0);
        mTagFlowSourceType.setAdapter(sourceCarTypeAdapter);
        mTagFlowSourceType.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (position != 0) {
                    carType = dataSourceCarType.get(position);
                } else {
                    carType = "";
                }
                return false;
            }
        });
    }


    @OnClick({R.id.img_back, R.id.rl_sales_area, R.id.rl_car_brand, R.id.rl_car_model, R.id.tv_open_put_away, R.id.ll_put_away, R.id.bt_search_reset, R.id.bt_search_sure,
            R.id.tv_year_all, R.id.ll_choose_year, R.id.img_last, R.id.img_next, R.id.tv_search, R.id.rl_options_type})
    public void onDrawerViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_sales_area:
                startActivityForResult(new Intent(this, ChooseAreaActivity.class), REQUEST_AREA);
                break;
            case R.id.rl_car_brand:
                bundle.putString("params", "filter");
                bundle.putString("optionId", carType);
                startActivityForResult(ChooseBrandActivity.class, bundle, REQUEST_BRAND);
                break;
            case R.id.rl_car_model:
                if (TextUtils.isEmpty(brandId)) {
                    bundle.putString("params", "filter");
                    startActivityForResult(ChooseBrandActivity.class, bundle, REQUEST_BRAND);
                } else {
                    bundle.putString("carBrand", carBrand);
                    bundle.putString("brandId", brandId);
                    bundle.putString("params", "filter");
                    startActivityForResult(ChooseCarsActivity.class, bundle, REQUEST_CAR);
                }
                break;
            case R.id.tv_open_put_away:
                isOpen = false;
                for (ItemData bean : mSearchResultData) {
                    if (bean.getData() instanceof SearchResultModel) {
                        ((SearchResultModel) bean.getData()).setOpenPutEntrance(!((SearchResultModel) bean.getData()).isOpenPutEntrance());
                        isOpen = ((SearchResultModel) bean.getData()).isOpenPutEntrance();
                    }
                }
                mLinearPut.setVisibility(isOpen ? View.VISIBLE : View.GONE);
                switch (INVENTORY) {
                    case C.INVENTORY_MARKET:
                        mTvPutAway.setText(isOpen ? "取消" : "分享车辆");
                        break;
                    case C.INVENTORY_DEALER:
                        mTvPutAway.setText(isOpen ? "取消" : "上架车辆");
                        break;
                    default:
                }
                mDataAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_put_away:
                switch (INVENTORY) {
                    case C.INVENTORY_MARKET:
                        mPutAwayData.clear();
                        for (ItemData bean : mSearchResultData) {
                            if ((bean.getData() instanceof SearchResultModel) && ((SearchResultModel) bean.getData()).isPut()) {
                                mPutAwayData.add((SearchResultModel) bean.getData());
                            }
                        }
                        bundle.putParcelableArrayList("data", mPutAwayData);
                        startActivity(MarketShareCarActivity.class, bundle);
                        break;
                    case C.INVENTORY_DEALER:
                        mPutAwayData.clear();
                        for (ItemData bean : mSearchResultData) {
                            if ((bean.getData() instanceof SearchResultModel) && ((SearchResultModel) bean.getData()).isPut()) {
                                mPutAwayData.add((SearchResultModel) bean.getData());
                            }
                        }
                        bundle.putParcelableArrayList("data", mPutAwayData);
                        startActivity(PutAwayDetailActivity.class, bundle);
                        break;
                    default:
                }
                break;
            case R.id.bt_search_sure:
                //搜索确认
                CURRENT_PAGE = 1;
                mSearchResultData.clear();
                getAllOptions();
                mDrawerMain.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.bt_search_reset:
                //搜索重置
                CURRENT_PAGE = 1;
                outsiteColor = "";
                withinColor = "";
                mTvSelectArea.setText("");
                mTvSelectBrand.setText("");
                brandId = "";
                mTvSelectedCar.setText("");
                versionId = "";
                mCarTypeName.setText("");
                carType = "";
                carYear = "";
                mEtMaxMoney.setText("");
                mEtMinMoney.setText("");
                mTagFlowPrice.getAdapter().setSelectedList(0);
                mTagFlowSourceType.getAdapter().setSelectedList(0);
                mTagFlowColorInside.getAdapter().setSelectedList(0);
                mTagFlowColorOut.getAdapter().setSelectedList(0);
                mTagFlowTime.getAdapter().setSelectedList(0);
                getAllOptions();
                break;
            case R.id.tv_year_all:
                mTvSelectYear.setBackgroundResource(R.drawable.bg_radiobutton_red);
                carYear = "";
                mLLChooseYear.setBackgroundResource(R.drawable.bg_radiobutton);
                break;
            case R.id.ll_choose_year:
                mLLChooseYear.setBackgroundResource(R.drawable.bg_radiobutton_red);
                carYear = mTvYear.getText().toString();
                mTvSelectYear.setBackgroundResource(R.drawable.bg_radiobutton);
                break;
            case R.id.img_last:
                mTvYear.setText(Integer.parseInt(mTvYear.getText().toString()) - 1 + "");
                carYear = mTvYear.getText().toString();
                break;
            case R.id.img_next:
                mTvYear.setText(Integer.parseInt(mTvYear.getText().toString()) + 1 + "");
                carYear = mTvYear.getText().toString();
                break;
            case R.id.tv_search:
                queryKey = mEditSearch.getText().toString();
                mSearchResultData.clear();
                getAllOptions();
                break;
            case R.id.rl_options_type:
                Intent intent = new Intent(this, CarSourceTypeActivity.class);
                startActivityForResult(intent, REQUEST_CAR_TYPE);
                break;
            default:
        }
    }

    @OnClick({R.id.rb_car_state, R.id.rb_car_order, R.id.rb_car_filter})
    public void onRadioButtonSelected(View view) {
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
                if (mDrawerMain.isDrawerOpen(mDrawerRight)) {
                    mDrawerMain.closeDrawer(mDrawerRight);
                } else {
                    mDrawerMain.openDrawer(mDrawerRight);
                }
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerMain.isDrawerOpen(mDrawerRight)) {
            mDrawerMain.closeDrawer(mDrawerRight);
            return;
        }
        super.onBackPressed();
    }

    public void showPopupWindow(final int viewId) {
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        switch (viewId) {
            case R.id.rb_car_state:
                LinearLayout convertFrame = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_popup_car_state_recycler, null);
                RecyclerView stateRecycler = (RecyclerView) convertFrame.findViewById(R.id.recycler_car_state);
                initStateRecycler(stateRecycler);
                mPopupWindow = new PopupWindow(convertFrame, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setBackgroundDrawable(dw);
                mPopupWindow.showAsDropDown(mRadioGroup, 0, 0);
                break;
            case R.id.rb_car_order:
                LinearLayout convertLinear = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_popup_car_order_recycler, null);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_AREA:
                    String area = (data.getStringExtra("area"));
                    mTvSelectArea.setText(area);
//                    String provinceCode = data.getStringExtra("provinceCode");
//                    String cityCode = data.getStringExtra("cityCode");
                    break;
                case REQUEST_BRAND:
                    carBrand = data.getStringExtra("carBrand");
                    mTvSelectBrand.setText(carBrand);
                    brandId = data.getStringExtra("brandId");
                    break;
                case REQUEST_CAR:
                    String carCombinate = data.getStringExtra("carCombinate");
                    String audiId = data.getStringExtra("audiId");
                    mTvSelectedCar.setText(carCombinate);
                    versionId = audiId;
                    break;
                case REQUEST_CAR_TYPE:
                    String carTypeName = data.getStringExtra("carTypeName");
                    String carTypeId = data.getStringExtra("carTypeId");
                    mCarTypeName.setText(carTypeName);
                    carType = carTypeId;
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 初始化 状态recycler
     *
     * @param stateRecycler
     */
    private void initStateRecycler(RecyclerView stateRecycler) {
        stateRecycler.setLayoutManager(new GridLayoutManager(this, 4));
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
                getAllOptions();
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
        orderRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
                getAllOptions();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        }));
    }
}