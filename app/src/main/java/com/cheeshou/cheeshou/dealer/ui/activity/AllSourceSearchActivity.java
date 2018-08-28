package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.fragment.NationalSourceFragment;
import com.cheeshou.cheeshou.dealer.ui.fragment.StoreManagerItemClickFragment;
import com.cheeshou.cheeshou.dealer.ui.model.ColorFilterModel;
import com.cheeshou.cheeshou.dealer.ui.model.PriceModel;
import com.cheeshou.cheeshou.dealer.ui.model.SearchHistoryDeleteModel;
import com.cheeshou.cheeshou.dealer.ui.model.SearchHistoryModel;
import com.cheeshou.cheeshou.dealer.ui.model.SearchHistoryModelDao;
import com.cheeshou.cheeshou.options.ChooseAreaActivity;
import com.cheeshou.cheeshou.options.ChooseBrandActivity;
import com.cheeshou.cheeshou.options.ChooseCarsActivity;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.DaoUtils;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.flyco.tablayout.SlidingTabLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class AllSourceSearchActivity extends BaseActivity {

    private static final String TAG = "AllSourceSearchActivity";

    @BindView(R.id.tab_search_result)
    SlidingTabLayout mTabSearchResult;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
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
    DrawerLayout mDrawerMain;
    @BindView(R.id.layout_drawer_right)
    LinearLayout mDrawerRight;
    @BindView(R.id.recycler_search_history)
    RecyclerView mRecyclerSearchHistory;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
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
    @BindView(R.id.tv_options_type)
    TextView mCarTypeName;

    private String carBrand;


    private List<Fragment> mTagFragments = new ArrayList<>();
    private String[] mContentTitles = {"车源", "库存"};
    private ContentPagerAdapter mContentAdapter;

    private List<ItemData> mSearchData = new ArrayList<>();
    private BaseAdapter<ItemData> mSearchAdapter;

    private final int REQUEST_BRAND = 0;
    private final int REQUEST_AREA = 1;
    private final int REQUEST_CAR = 2;
    private final int REQUEST_CAR_TYPE = 3;

    private String carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey, carStatus, orderType;
    private StoreManagerItemClickFragment searchResultFragment;
    private NationalSourceFragment nationAlFragment;

    private int INVENTORY = ParamManager.getInstance(this).getChannelType();


    @Override
    public int bindLayout() {
        return R.layout.activity_all_source_search;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecyclerSearchHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerSearchHistory.addItemDecoration(new SpaceItemDecoration(5));
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(mEtSearch.getText().toString())) {
                    mTvSearch.setText("搜索");
                } else {
                    mTvSearch.setText("取消");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        initViewPager();
        initTabLayout();
        initDrawerTagList();
        initSearchHistory();
    }

    private void initSearchHistory() {
        mSearchData.add(new ItemData(0, SettingDelegate.DELETE_SEARCH_HISTORY_TYPE, new SearchHistoryDeleteModel()));
        List<SearchHistoryModel> searchHistoryModels = DaoUtils.getDaoSession(this).getSearchHistoryModelDao().queryBuilder().where(SearchHistoryModelDao.Properties.SearchPosition.eq(TAG)).list();
        for (SearchHistoryModel bean : searchHistoryModels) {
            mSearchData.add(new ItemData(0, SettingDelegate.SEARCH_HISTORY_TYPE, bean));
        }
        mSearchAdapter = new BaseAdapter<>(mSearchData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                if (data instanceof SearchHistoryModel) {
                    mRecyclerSearchHistory.setVisibility(View.GONE);
                    queryKey = ((SearchHistoryModel) data).getSearchHistory();
                    nationAlFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
                    searchResultFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
                }
                if (data instanceof SearchHistoryDeleteModel) {
                    List<SearchHistoryModel> list = DaoUtils.getDaoSession(AllSourceSearchActivity.this).getSearchHistoryModelDao().queryBuilder().where(SearchHistoryModelDao.Properties.SearchPosition.eq(TAG)).list();
                    for (SearchHistoryModel bean : list) {
                        DaoUtils.getDaoSession(AllSourceSearchActivity.this).getSearchHistoryModelDao().delete(bean);
                    }
                    mSearchData.clear();
                    mSearchData.add(new ItemData(0, SettingDelegate.DELETE_SEARCH_HISTORY_TYPE, new SearchHistoryDeleteModel()));
                    mSearchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecyclerSearchHistory.setAdapter(mSearchAdapter);
    }

    private void initViewPager() {
        nationAlFragment = new NationalSourceFragment();
        Bundle args = new Bundle();
        nationAlFragment.setArguments(args);
        mTagFragments.add(nationAlFragment);
        searchResultFragment = new StoreManagerItemClickFragment();
        mTagFragments.add(searchResultFragment);
        mContentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mVpContent.setAdapter(mContentAdapter);

    }

    private void initTabLayout() {
        mTabSearchResult.setViewPager(mVpContent, mContentTitles);
        mTabSearchResult.setIndicatorColor(Color.parseColor("#F55745"));
    }


    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mTagFragments.get(position);
        }

        @Override
        public int getCount() {
            return mContentTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mContentTitles[position];
        }
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
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_drawer_filter_racter, mTagFlowPrice, false);
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


    @OnClick({R.id.iv_sales_area, R.id.iv_car_brand, R.id.iv_car_model, R.id.tv_search, R.id.bt_search_reset, R.id.bt_search_sure, R.id.tv_year_all, R.id.ll_choose_year, R.id.img_last, R.id.img_next, R.id.iv_options_type})
    public void onDrawerViewClicked(View view) {
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
            case R.id.tv_search:
                if (TextUtils.isEmpty(mEtSearch.getText().toString())) {
                    this.finish();
                } else {
                    mRecyclerSearchHistory.setVisibility(View.GONE);
                    queryKey = mEtSearch.getText().toString();
                    nationAlFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
                    searchResultFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);

                    SearchHistoryModel unique = DaoUtils.getDaoSession(this).getSearchHistoryModelDao().queryBuilder().where(SearchHistoryModelDao.Properties.SearchPosition.eq(TAG), SearchHistoryModelDao.Properties.SearchHistory.eq(queryKey)).unique();
                    if (unique != null) {
                        unique.setTimeShamp(System.currentTimeMillis());
                        DaoUtils.getDaoSession(this).getSearchHistoryModelDao().update(unique);
                    } else {
                        SearchHistoryModel entity = new SearchHistoryModel();
                        entity.setTimeShamp(System.currentTimeMillis());
                        entity.setSearchHistory(queryKey);
                        entity.setSearchPosition(TAG);
                        DaoUtils.getDaoSession(this).getSearchHistoryModelDao().insert(entity);
                    }
                }
                break;
            case R.id.bt_search_sure:
                //搜索确认
//                mSearchResultData.clear();
//                getAllOptions();
                nationAlFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
                searchResultFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
//                searchResultFragment.
                mDrawerMain.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.bt_search_reset:
                //搜索重置
                mTvSelectArea.setText("");
                mTvSelectBrand.setText("");
                mTvSelectedCar.setText("");
                mCarTypeName.setText("");
                carType = "";
                mTagFlowPrice.getAdapter().setSelectedList(0);
                mTagFlowSourceType.getAdapter().setSelectedList(0);
                mTagFlowColorInside.getAdapter().setSelectedList(0);
                mTagFlowColorOut.getAdapter().setSelectedList(0);
                mTagFlowTime.getAdapter().setSelectedList(0);
//                getAllOptions();
                nationAlFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
                searchResultFragment.filterRecycler(carType, brandId, versionId, carYear, outsiteColor, withinColor, minCarPrice, maxCarPrice, startDate, endDate, queryKey);
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
            case R.id.iv_options_type:
                Intent intent = new Intent(this, CarSourceTypeActivity.class);
                startActivityForResult(intent, REQUEST_CAR_TYPE);
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


}
