package com.example.com.careasysell.dealer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseFragment;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author ： DasonYu
 * Date ： 2018/7/27
 * Email Address : 15764240573@163.com
 */

public class SearchResultFragment extends BaseFragment {

    @BindView(R.id.rl_search_result)
    RecyclerView mSearchResult;
    Unbinder unbinder;

    private List<ItemData> mSearchResultData = new ArrayList<>();

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_layout_search_result;
    }

    @Override
    public void setView(View rootView) {
        mSearchResult.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void initData(Bundle arguments) {
        for (int i = 0; i < 10; i++) {
            SearchResultModel data = new SearchResultModel();
            data.setDate("2018/06/24");
            data.setDeduct("销售提成2000");
            data.setPrice("16.8万");
            data.setState("已上架");
            data.setSubTitle("分享20次|浏览140次");
            data.setTitle("雪佛兰2013款  科鲁兹  16LSL天地板MT");
            ItemData e = new ItemData(0,SettingDelegate.SEARCH_RESULT_TYPE, data);
            mSearchResultData.add(e);
        }
        BaseAdapter adapter = new BaseAdapter(mSearchResultData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {

            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mSearchResult.setAdapter(adapter);
    }

    @Override
    public void onLazyLoad() {

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
}
