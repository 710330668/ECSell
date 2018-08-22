package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheeshou.cheeshou.options.ChooseAreaActivity;
import com.cheeshou.cheeshou.options.ChooseBrandActivity;
import com.cheeshou.cheeshou.options.ChooseCarsActivity;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.fragment.SearchResultFragment;
import com.cheeshou.cheeshou.dealer.ui.model.ColorFilterModel;
import com.cheeshou.cheeshou.dealer.ui.model.PriceModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.StoreManagerResponse;
import com.cheeshou.cheeshou.options.ChooseAreaActivity;
import com.cheeshou.cheeshou.options.ChooseBrandActivity;
import com.cheeshou.cheeshou.options.ChooseCarsActivity;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.example.com.common.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cheeshou.cheeshou.R.layout.item_drawer_filter_racter;

public class StoreSearchActivity extends BaseActivity implements TagFlowLayout.OnSelectListener {

    private static final String TAG = "StoreSearchActivity";

    @BindView(R.id.flow_layout_hot_character)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.tag_flow_price)
    TagFlowLayout mTagFlowPrice;
    @BindView(R.id.tag_flow_time)
    TagFlowLayout mTagFlowTime;
    @BindView(R.id.tag_flow_out_color)
    TagFlowLayout mTagFlowColorOut;
    @BindView(R.id.tag_flow_inside_color)
    TagFlowLayout mTagFlowColorInside;
    @BindView(R.id.tag_flow_car_source_type)
    TagFlowLayout mTagFlowSourceType;
    @BindView(R.id.layout_drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.layout_drawer_right)
    LinearLayout mDrawerRightContent;
    @BindView(R.id.tv_bar_right)
    TextView mTvBarRight;


    @BindView(R.id.et_search)
    EditText mEtSearch;

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

    private final int REQUEST_BRAND = 0;
    private final int REQUEST_AREA = 1;
    private final int REQUEST_CAR = 2;

    private String carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey, carStatus, orderType;
    private String carBrand;
    StoreManagerResponse.DataBean dataBean;

    @C.INVENTORY
    public int INVENTORY = C.INVENTORY_OPTION;


    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private SearchResultFragment mSearchResultFragment;


    @Override
    public int bindLayout() {
        return R.layout.activity_store_search;
    }

    @Override
    public void initParams(Bundle params) {
        INVENTORY = ParamManager.getInstance(this).getChannelType();
        if (params != null) {
            dataBean = (StoreManagerResponse.DataBean) params.getSerializable("data");
            if (mSearchResultFragment == null) {
                mSearchResultFragment = new SearchResultFragment();
                mSearchResultFragment.setArguments(params);
            }
            mFragmentManager.beginTransaction().add(R.id.fm_fg_container, mSearchResultFragment).commit();
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mFlowLayout.setOnSelectListener(this);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (INVENTORY == C.INVENTORY_DEALER) {
                    if (!TextUtils.isEmpty(mEtSearch.getText().toString())) {
                        mTvBarRight.setText("搜索");
                        queryKey = mEtSearch.getText().toString();
                    } else {
                        mTvBarRight.setText("取消");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        switch (INVENTORY) {
            case C.INVENTORY_DEALER:
                mTvBarRight.setText("取消");
                break;
            case C.INVENTORY_MARKET:
                mTvBarRight.setText("分享");
                break;
            case C.INVENTORY_OPTION:
                mTvBarRight.setText("取消");
                break;
            default:
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        initHotCharcter();
        initDrawerTagList();
    }

    /**
     * 初始化热词
     */
    private void initHotCharcter() {
        List<String> data = new ArrayList<>();
        data.add("奔驰");
        data.add("奔驰");
        data.add("奔驰");
        data.add("奔驰");
        mFlowLayout.setAdapter(new TagAdapter<String>(data) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_hot_character, mFlowLayout, false);
                textView.setText(o);
                return textView;
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
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_drawer_filter_racter, mTagFlowTime, false);
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

    /**
     * 热词点击 响应
     *
     * @param selectPosSet
     */
    @Override
    public void onSelected(Set<Integer> selectPosSet) {
        if (mSearchResultFragment == null) {
            mSearchResultFragment = new SearchResultFragment();
        }
        mFragmentManager.beginTransaction().add(R.id.fm_fg_container, mSearchResultFragment).commit();
    }

    @OnClick({R.id.iv_sales_area, R.id.iv_car_brand, R.id.iv_car_model, R.id.tv_bar_right, R.id.bt_search_reset, R.id.bt_search_sure, R.id.tv_year_all, R.id.ll_choose_year, R.id.img_last, R.id.img_next})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.iv_sales_area:
                startActivityForResult(new Intent(this, ChooseAreaActivity.class), REQUEST_AREA);
                break;
            case R.id.iv_car_brand:
                bundle.putString("params", "filter");
                startActivityForResult(ChooseBrandActivity.class, bundle, REQUEST_BRAND);
                break;
            case R.id.iv_car_model:
                if ("".equals(brandId)) {
                    bundle.putString("params", "filter");
                    startActivityForResult(ChooseBrandActivity.class, bundle, REQUEST_BRAND);
                } else {
                    bundle.putString("carBrand", carBrand);
                    bundle.putString("brandId", brandId);
                    bundle.putString("params", "filter");
                    startActivityForResult(ChooseCarsActivity.class, bundle, REQUEST_CAR);
                }
                break;
            case R.id.tv_bar_right:
                switch (INVENTORY) {
                    case C.INVENTORY_DEALER:
                        switch (mTvBarRight.getText().toString()) {
                            case "取消":
                                this.finish();
                                break;
                            case "搜索":
                                mSearchResultFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
                                break;
                        }
                        break;
                    case C.INVENTORY_MARKET:
                        mSearchResultFragment.setShareOpen();
                        break;
                    case C.INVENTORY_OPTION:
                        this.finish();
                        break;
                    default:
                }
                break;
            case R.id.bt_search_sure:
                //搜索确认
//                mSearchResultData.clear();
//                getAllOptions();
                mSearchResultFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
//                searchResultFragment.
                mDrawer.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.bt_search_reset:
                //搜索重置
                mTvSelectArea.setText("");
                mTvSelectBrand.setText("");
                mTvSelectedCar.setText("");
                mTagFlowPrice.getAdapter().setSelectedList(0);
                mTagFlowSourceType.getAdapter().setSelectedList(0);
                mTagFlowColorInside.getAdapter().setSelectedList(0);
                mTagFlowColorOut.getAdapter().setSelectedList(0);
                mTagFlowTime.getAdapter().setSelectedList(0);
//                getAllOptions();
                mSearchResultFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
                break;
            case R.id.tv_year_all:
                mTvSelectYear.setBackgroundResource(R.drawable.bg_edittext_red);
                carYear = "";
                mLLChooseYear.setBackgroundResource(R.drawable.bg_edittext);
                break;
            case R.id.ll_choose_year:
                mLLChooseYear.setBackgroundResource(R.drawable.bg_edittext_red);
                carYear = mTvYear.getText().toString();
                mTvSelectYear.setBackgroundResource(R.drawable.bg_edittext);
                break;
            case R.id.img_last:
                mTvYear.setText(Integer.parseInt(mTvYear.getText().toString()) - 1 + "");
                carYear = mTvYear.getText().toString();
                break;
            case R.id.img_next:
                mTvYear.setText(Integer.parseInt(mTvYear.getText().toString()) + 1 + "");
                carYear = mTvYear.getText().toString();
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(mDrawerRightContent)) {
            mDrawer.closeDrawer(mDrawerRightContent);
            return;
        }
        super.onBackPressed();
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
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
