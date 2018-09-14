package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerDetailResponse;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FollowDetailActivity extends BaseActivity {

    @BindView(R.id.tv_follow_time)
    TextView mTvTime;
    @BindView(R.id.tv_follow_source)
    TextView mTvSource;
    @BindView(R.id.tv_follow_method)
    TextView mTvMethod;
    @BindView(R.id.tv_follow_content)
    TextView mTvContent;
    @BindView(R.id.recycler_image)
    RecyclerView mRecyclerImage;
    private CustomerDetailResponse.DataBean.ProgressesBean data;

    private List<ItemData> imageList = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_follow_detail;
    }

    @Override
    public void initParams(Bundle params) {
        data = (CustomerDetailResponse.DataBean.ProgressesBean) getIntent().getExtras().getSerializable("data");
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mTvTime.setText(formatDate("yyyy-MM-dd hh:mm:ss", data.getCreateDate()));
        mTvSource.setText(data.getSource());
        mTvMethod.setText(data.getType());
        mTvContent.setText(data.getContent());
        mRecyclerImage.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));

        List<CustomerDetailResponse.DataBean.ImageBean> imgs = data.getImgs();
        if (imgs != null && imgs.size() > 0) {
            for (CustomerDetailResponse.DataBean.ImageBean bean : imgs) {
                ItemData itemData = new ItemData(0, SettingDelegate.CUSTOMER_FOLLOW_IMAGE_TYPE, bean);
                imageList.add(itemData);
            }
        }
        mRecyclerImage.setAdapter(new BaseAdapter(imageList, new SettingDelegate()));

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private String formatDate(String format, long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date1 = new Date(date);
        return simpleDateFormat.format(date1);
    }
}
