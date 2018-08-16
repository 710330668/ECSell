package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.ColorFilterModel;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
import com.example.com.careasysell.options.CarDetailActivity;
import com.example.com.careasysell.options.ChooseAreaActivity;
import com.example.com.careasysell.options.ChooseBrandActivity;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.EndlessRecyclerOnScrollListener;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
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

public class NationSourceActivity extends BaseActivity {

    @BindView(R.id.tag_flow_price)
    TagFlowLayout mTagFlowPrice;
    @BindView(R.id.tag_flow_time)
    TagFlowLayout mTagFlowTime;
    @BindView(R.id.tag_flow_out_color)
    TagFlowLayout mTagFlowColorOut;
    @BindView(R.id.tag_flow_inside_color)
    TagFlowLayout mTagFlowColorInside;
    @BindView(R.id.tag_flow_car_type)
    TagFlowLayout mTagFlowType;
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
    @BindView(R.id.rb_car_brand)
    RadioButton mRbBrand;
    @BindView(R.id.rb_car_filter)
    RadioButton mRbFilter;
    @BindView(R.id.tv_open_put_away)
    TextView mTvPutAway;
    @BindView(R.id.ll_put_away)
    LinearLayout mLinearPut;
    private int selectState = 0;
    private int selectOrder = 0;

    @C.INVENTORY
    public int INVENTORY = C.INVENTORY_OPTION;
    PopupWindow mPopupWindow;

    private List<ItemData> mSearchResultData = new ArrayList<>();

    private static final String TAG = "NationSourceActivity";
    private final int REQUEST_BRAND = 0;
    private BaseAdapter mDataAdapter;
    private String token;
    private   int CURRENT_PAGE = 1;
    private   int PAGE_SIZE = 6;
    private int count ;
    private String carType,brandId,versionId,carYear,outsiteColor,withinColor,minCarPrice,maxCarPrice,startDate,endDate,queryKey;


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
        mSearchResult.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSearchResult.addItemDecoration(new SpaceItemDecoration(5));
        mSearchResult.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mDataAdapter.setLoadState(mDataAdapter.LOADING);
                if(mSearchResultData.size() < count){
                    ++CURRENT_PAGE;
                    getAllOptions();
                }else{
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
        mSearchResult.setAdapter(mDataAdapter);
        //获取全国车源
        getAllOptions();
        initDrawerTagList();
    }

    @SuppressLint("CheckResult")
    private void getAllOptions() {
        if(mSearchResultData.size()>0){
            mSearchResultData.remove(mSearchResultData.size()-1);
        }
        Injection.provideApiService().getCarList(token, PAGE_SIZE+"", CURRENT_PAGE+"","all",
                carType,brandId,versionId,carYear,outsiteColor,withinColor,minCarPrice,maxCarPrice,
                startDate,endDate,queryKey)
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
                                data.setDeduct(response.getData().getLists().get(i).getSaleCommission() + "");
                                data.setPrice(response.getData().getLists().get(i).getBrowseNum() + "万");
                                data.setState(response.getData().getLists().get(i).getCarStatusName());
                                data.setSubTitle("分享" + response.getData().getLists().get(i).getShareNum() + "次|浏览140次");
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

    private void initDrawerTagList() {
        List<String> dataSize = new ArrayList<>();
        dataSize.add("不限");
        dataSize.add("5万以下");
        dataSize.add("5-10万");
        dataSize.add("10-15万");
        dataSize.add("15-30万");
        dataSize.add("30-50万");
        dataSize.add("50-100万");
        dataSize.add("100万及以上");
        TagAdapter<String> priceAdapter = new TagAdapter<String>(dataSize) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_drawer_filter_racter, mTagFlowPrice, false);
                textView.setText(o);
                return textView;
            }
        };
        priceAdapter.setSelectedList(0);
        mTagFlowPrice.setAdapter(priceAdapter);


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
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_drawer_filter_racter, mTagFlowTime, false);
                textView.setText(o);
                return textView;
            }
        };
        timeAdapter.setSelectedList(0);
        mTagFlowTime.setAdapter(timeAdapter);

        List<ColorFilterModel> dataColorOut = new ArrayList<>();
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


        List<ColorFilterModel> dataColorInside = new ArrayList<>();
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

        List<String> dataCarType = new ArrayList<>();
        dataCarType.add("不限");
        dataCarType.add("SUV");
        dataCarType.add("MPV");
        dataCarType.add("微小型车");
        dataCarType.add("紧凑型车");
        dataCarType.add("中型车");
        dataCarType.add("中大型车");
        dataCarType.add("跑车");
        dataCarType.add("两厢车");
        dataCarType.add("三厢车");

        TagAdapter<String> carTypeAdapter = new TagAdapter<String>(dataCarType) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_drawer_filter_racter, mTagFlowType, false);
                textView.setText(o);
                return textView;
            }
        };
        carTypeAdapter.setSelectedList(0


        );
        mTagFlowType.setAdapter(carTypeAdapter);

        List<String> dataSourceCarType = new ArrayList<>();
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
    }


    @OnClick({R.id.img_back, R.id.iv_sales_area, R.id.iv_car_brand, R.id.iv_car_model, R.id.tv_open_put_away, R.id.ll_put_away})
    public void onDrawerViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_sales_area:
                startActivity(ChooseAreaActivity.class);
                break;
            case R.id.iv_car_brand:
                startActivity(ChooseBrandActivity.class);
                break;
            case R.id.iv_car_model:
                startActivity(ChooseBrandActivity.class);
                break;
            case R.id.tv_open_put_away:
                boolean open = false;
                for (ItemData bean : mSearchResultData) {
                    ((SearchResultModel) bean.getData()).setOpenPutEntrance(!((SearchResultModel) bean.getData()).isOpenPutEntrance());
                    open = ((SearchResultModel) bean.getData()).isOpenPutEntrance();
                    mLinearPut.setVisibility(((SearchResultModel) bean.getData()).isOpenPutEntrance() ? View.VISIBLE : View.GONE);
                }
                switch (INVENTORY) {
                    case C.INVENTORY_MARKET:
                        mTvPutAway.setText(open ? "取消" : "分享车辆");
                        break;
                    case C.INVENTORY_DEALER:
                        mTvPutAway.setText(open ? "取消" : "上架车辆");
                        break;
                    default:
                }
                mDataAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_put_away:
                switch (INVENTORY) {
                    case C.INVENTORY_MARKET:
                        break;
                    case C.INVENTORY_DEALER:
                        startActivity(PutAwayDetailActivity.class);
                        break;
                    default:
                }
                break;

            default:
        }
    }

    @OnClick({R.id.rb_car_state, R.id.rb_car_brand, R.id.rb_car_order, R.id.rb_car_filter})
    public void onRadioButtonSelected(View view) {
        switch (view.getId()) {
//            汽车状态
            case R.id.rb_car_state:
                showPopupWindow(R.id.rb_car_state);
                break;
//                品牌  跳转品牌activity
            case R.id.rb_car_brand:
                Intent intent = new Intent(this, ChooseBrandActivity.class);
                startActivityForResult(intent, REQUEST_BRAND);
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
        RadioGroup convertView = null;

        switch (viewId) {
            case R.id.rb_car_state:
                convertView = (RadioGroup) LayoutInflater.from(this).inflate(R.layout.layout_popup_car_state, null);
                ((RadioButton) convertView.getChildAt(selectState)).setChecked(true);
                mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                break;
            case R.id.rb_car_order:
                convertView = (RadioGroup) LayoutInflater.from(this).inflate(R.layout.layout_popup_car_order, null);
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