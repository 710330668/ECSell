package com.cheeshou.cheeshou.market.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.example.com.common.BaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class MarketShareWechatActivity extends BaseActivity {

    private ArrayList<SearchResultModel> data;
    @BindView(R.id.rcl_share_photos)
    RecyclerView mRecyclerSharePhoto;

    private static final String TAG = "MarketShareWechatActivity";

    @Override
    public int bindLayout() {
        return R.layout.activity_market_share_wechat;
    }

    @Override
    public void initParams(Bundle params) {
        data = params.getParcelableArrayList("data");
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @SuppressLint("LongLogTag")
    @OnClick({R.id.ll_share_to_friend, R.id.ll_share_to_friend_circle, R.id.ll_share_to_collector})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_share_to_friend:
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
                sp.setTitle("车一首"); //分享标题
                sp.setText("lalalal");   //分享文本
//                sp.setImageUrl(webBean.getCfg().getShareIcon());
                sp.setUrl("www.baidu.com");   //网友点进链接后，可以看到分享的详情
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
//                wechat.setPlatformActionListener(platformActionListener); // 设置分享事件回调
                wechat.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Log.e(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Log.e(TAG, "onError: " + throwable.toString());
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Log.e(TAG, "onCancel: ");
                    }
                });
                // 执行分享
                wechat.share(sp);
                break;
            case R.id.ll_share_to_friend_circle:
                break;
            case R.id.ll_share_to_collector:
                break;
        }
    }
}
