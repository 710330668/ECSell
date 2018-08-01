package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.fragment.NationalSourceFragment;
import com.example.com.careasysell.dealer.ui.fragment.SearchResultFragment;
import com.example.com.careasysell.dealer.ui.model.ColorFilterModel;
import com.example.com.careasysell.dealer.ui.model.SearchHistoryDeleteModel;
import com.example.com.careasysell.dealer.ui.model.SearchHistoryModel;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
import com.example.com.careasysell.options.ChooseAreaActivity;
import com.example.com.careasysell.options.ChooseBrandActivity;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
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
    @BindView(R.id.tag_flow_car_type)
    TagFlowLayout mTagFlowType;
    @BindView(R.id.tag_flow_car_source_type)
    TagFlowLayout mTagFlowSourceType;
    @BindView(R.id.layout_drawer)
    DrawerLayout mDrawerMain;
    @BindView(R.id.layout_drawer_right)
    LinearLayout mDrawerRight;
    @BindView(R.id.recycler_search_history)
    RecyclerView mRecyclerSearchHistory;


    private List<Fragment> mTagFragments = new ArrayList<>();
    private String[] mContentTitles = {"车源", "库存"};
    private ContentPagerAdapter mContentAdapter;

    private List<ItemData> mSearchData = new ArrayList<>();
    private BaseAdapter<ItemData> mSearchAdapter;


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
        for (int i = 0; i < 20; i++) {
            SearchHistoryModel data = new SearchHistoryModel();
            data.setSearchHistory("保时捷 911");
            mSearchData.add(new ItemData(0, SettingDelegate.SEARCH_HISTORY_TYPE, data));
        }
        mSearchAdapter = new BaseAdapter<>(mSearchData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                if (data instanceof SearchHistoryModel) {
                    mRecyclerSearchHistory.setVisibility(View.GONE);
                }
                if (data instanceof SearchHistoryDeleteModel) {
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
        mTagFragments.add(new NationalSourceFragment());
        mTagFragments.add(new SearchResultFragment());
        mContentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mVpContent.setAdapter(mContentAdapter);

    }

    private void initTabLayout() {
        mTabSearchResult.setViewPager(mVpContent, mContentTitles);
        mTabSearchResult.setIndicatorColor(Color.parseColor("#F55745"));
//        mTabSearchResult.setTabTextColors(Color.parseColor("#333333"),Color.parseColor("#F55745"));
//        mTabSearchResult.setSelectedTabIndicatorColor(Color.parseColor("#FF5745"));
//        ViewCompat.setElevation(mTabSearchResult,10);
//        mTabSearchResult.setupWithViewPager(mVpContent);
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


    @OnClick({R.id.iv_sales_area, R.id.iv_car_brand, R.id.iv_car_model})
    public void onDrawerViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sales_area:
                startActivity(ChooseAreaActivity.class);
                break;
            case R.id.iv_car_brand:
                startActivity(ChooseBrandActivity.class);
                break;
            case R.id.iv_car_model:
                startActivity(ChooseBrandActivity.class);
                break;
            default:
        }
    }


}
