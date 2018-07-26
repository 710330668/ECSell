package com.example.com.careasysell;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.com.careasysell.config.C;
import com.example.com.careasysell.options.OptionsTradersFragment;
import com.example.com.careasysell.options.SettingFragment;
import com.example.com.careasysell.options.TabEntity;
import com.example.com.careasysell.view.NoScrollViewPager;
import com.example.com.common.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 71033 on 2018/7/24.
 */
public class MainTabActivity extends BaseActivity {

    @BindView(R.id.container)
    NoScrollViewPager container;
    @BindView(R.id.mainTabBar)
    CommonTabLayout mainTabBar;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {C.TAG_PAGE_DUANJIE, C.TAG_PAGE_SETTING};
    private int[] mIconUnselectIds = {R.mipmap.home_white, R.mipmap.set_white};
    private int[] mIconSelectIds = {R.mipmap.home_yellow, R.mipmap.set_yellow};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private OptionsTradersFragment tradersFragment;
    private SettingFragment settingFragment;

    @Override
    public int bindLayout() {
        return R.layout.activity_maintab;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mainTabBar.setTabData(mTabEntities);
        mainTabBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                container.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        tradersFragment = new OptionsTradersFragment();
        settingFragment = new SettingFragment();
        mFragments.add(tradersFragment);
        mFragments.add(settingFragment);
        container.setOffscreenPageLimit(mFragments.size());
        container.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mainTabBar.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        container.setCurrentItem(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
