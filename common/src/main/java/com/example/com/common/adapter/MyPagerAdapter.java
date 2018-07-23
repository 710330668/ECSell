package com.example.com.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_dafa on 2018/1/31.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;
    private List<Fragment> mFragments;
    private FragmentManager fm;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public MyPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments){
        super(fm);
        this.fm = fm;
        mTitles = titles;
        mFragments = fragments;
    }
    public void setFragments(ArrayList fragments) {
        if(this.mFragments != null){
            FragmentTransaction ft = fm.beginTransaction();
            for(Fragment f:this.mFragments){
                ft.remove(f);
            }
            ft.commit();
            ft=null;
            fm.executePendingTransactions();
        }
        this.mFragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
