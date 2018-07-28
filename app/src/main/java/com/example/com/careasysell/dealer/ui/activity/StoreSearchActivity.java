package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.fragment.SearchResultFragment;
import com.example.com.common.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

public class StoreSearchActivity extends BaseActivity implements TagFlowLayout.OnSelectListener {

    private static final String TAG = "StoreSearchActivity";

    @BindView(R.id.flow_layout_hot_character)
    TagFlowLayout mFlowLayout;

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private SearchResultFragment mSearchResultFragment;


    @Override
    public int bindLayout() {
        return R.layout.activity_store_search;
    }

    @Override
    public void initParams(Bundle params) {
        if (params!=null) {
//            String string = params.getString(C.TAG_PAGE_STORE_MANAGER, "");
//            switch (string) {
//                case C.TAG_STATE_PUT_AWAY:
//                    break;
//                case C.TAG_STATE_RESERVE:
//                    break;
//                case C.TAG_STATE_SELL:
//                    break;
//                    default:
//            }
            if (mSearchResultFragment==null) {
                mSearchResultFragment = new SearchResultFragment();
            }
            mFragmentManager.beginTransaction().add(R.id.fm_fg_container,mSearchResultFragment).commit();
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mFlowLayout.setOnSelectListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        initHotCharcter();
    }

    /**
     * 初始化热词
     */
    private void initHotCharcter() {
        List<String> data = new ArrayList<>();
        data.add("哈密瓜");
        data.add("哈密瓜");
        data.add("哈密瓜");
        data.add("哈密瓜");
        mFlowLayout.setAdapter(new TagAdapter<String>(data) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_hot_character, mFlowLayout, false);
                textView.setText(o);
                return textView;
            }
        });
    }


    /**
     * 热词点击 响应
     * @param selectPosSet
     */
    @Override
    public void onSelected(Set<Integer> selectPosSet) {
        if (mSearchResultFragment==null) {
            mSearchResultFragment = new SearchResultFragment();
        }
        mFragmentManager.beginTransaction().add(R.id.fm_fg_container,mSearchResultFragment).commit();
    }
}
