package com.cheeshou.cheeshou;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.fragment.DealerTradersFragment;
import com.cheeshou.cheeshou.market.ui.fragment.MarketTradersFragment;
import com.cheeshou.cheeshou.options.OptionsTradersFragment;
import com.cheeshou.cheeshou.options.SettingFragment;
import com.cheeshou.cheeshou.options.TabEntity;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.NoScrollViewPager;
import com.example.com.common.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * Created by 71033 on 2018/7/24.
 */
public class MainTabActivity extends BaseActivity {

    private static final String TAG = "MainTab";
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
    private DealerTradersFragment dealerTradersFragment;
    private MarketTradersFragment marketTradersFragment;
    private int INVENTORY;

    @Override
    public int bindLayout() {
        return R.layout.activity_maintab;
    }

    @Override
    public void initParams(Bundle params) {
        INVENTORY = ParamManager.getInstance(this).getChannelType();
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        requestPermissions();
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
        initSubFragment();
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

    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(MainTabActivity.this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d(TAG, permission.name + " is denied.");
                        }
                    }
                });

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


    /**
     * 根据Tag初始化fragment
     */
    private void initSubFragment() {
        switch (INVENTORY) {
            case C.INVENTORY_OPTION:
                tradersFragment = new OptionsTradersFragment();
                settingFragment = new SettingFragment();
                mFragments.add(tradersFragment);
                mFragments.add(settingFragment);
                break;
            case C.INVENTORY_DEALER:
                dealerTradersFragment = new DealerTradersFragment();
                settingFragment = new SettingFragment();
                mFragments.add(dealerTradersFragment);
                mFragments.add(settingFragment);
                break;
            case C.INVENTORY_MARKET:
                marketTradersFragment = new MarketTradersFragment();
                settingFragment = new SettingFragment();
                mFragments.add(marketTradersFragment);
                mFragments.add(settingFragment);
                break;
            default:
        }
    }
}
