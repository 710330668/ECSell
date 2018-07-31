package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.fragment.NationalSourceFragment;
import com.example.com.careasysell.dealer.ui.fragment.SearchResultFragment;
import com.example.com.careasysell.options.TabEntity;
import com.example.com.common.BaseActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AllSourceSearchActivity extends BaseActivity {

    @BindView(R.id.tab_search_result)
    SlidingTabLayout mTabSearchResult;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;


    private List<Fragment> mTagFragments = new ArrayList<>();
    private String [] mContentTitles = {"车源","库存"};
    private ContentPagerAdapter mContentAdapter;


    @Override
    public int bindLayout() {
        return R.layout.activity_all_source_search;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        initViewPager();
        initTabLayout();
    }

    private void initViewPager() {
        mTagFragments.add(new NationalSourceFragment());
        mTagFragments.add(new SearchResultFragment());
        mContentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mVpContent.setAdapter(mContentAdapter);

    }

    private void initTabLayout() {
        mTabSearchResult.setViewPager(mVpContent,mContentTitles);
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

}
