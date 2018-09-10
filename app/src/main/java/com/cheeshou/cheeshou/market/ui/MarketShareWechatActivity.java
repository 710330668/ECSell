package com.cheeshou.cheeshou.market.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.options.model.CarPhotoModel;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.util.SP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class MarketShareWechatActivity extends BaseActivity {

    private ArrayList<SearchResultModel> data;
    @BindView(R.id.rcl_share_photos)
    RecyclerView mRecyclerSharePhoto;

    private static final String TAG = "MarketShareWechatActivity";
    private BaseAdapter imageDeleteAdapter;
    private List<ItemData> carPhotos = new ArrayList<>();
    private List<String> imageArray = new ArrayList<>();
    private String article;
    private String shareUrl = "http://www.cheeshou.com";
    private String mToken;
    private String shareDirect = "";
    private List<CarPhotoModel> photos = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_market_share_wechat;
    }

    @Override
    public void initParams(Bundle params) {
        data = params.getParcelableArrayList("data");
        article = params.getString("article");
        photos = params.getParcelableArrayList("photo");
        shareUrl = params.getString("shareUrl");
        mToken = SP.getInstance(this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        for (SearchResultModel bean : data) {
            shareDirect += (bean.getId() + ",");
        }
        mRecyclerSharePhoto.setLayoutManager(new GridLayoutManager(this, 3));
        imageDeleteAdapter = new BaseAdapter(carPhotos, new SettingDelegate());
        for (int i = 0; i < photos.size(); i++) {
//            photos.get(i);
//            final CarPhotoModel carPhotoModel = new CarPhotoModel(null, data.get(i).getImageUrl());
            imageArray.add(photos.get(i).getImageUrl());
            ItemData itemData = new ItemData(i, SettingDelegate.SHARE_CAR_PHOTO_TYPE, photos.get(i));
            carPhotos.add(itemData);
            mRecyclerSharePhoto.setAdapter(imageDeleteAdapter);
        }
    }

    @SuppressLint("LongLogTag")
    @OnClick({R.id.ll_share_to_friend, R.id.ll_share_to_friend_circle, R.id.ll_share_to_collector,R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_share_to_friend:
                shareToFriend();
                break;
            case R.id.ll_share_to_friend_circle:
                shareToMoments();
                break;
            case R.id.ll_share_to_collector:
                weChatFavorite();
                break;
            case R.id.tv_close:
                finish();
                break;
        }
    }

    private void weChatFavorite() {
        WechatFavorite.ShareParams sp = new WechatFavorite.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
        sp.setTitle("车易售"); //分享标题
        sp.setText(article);   //分享文本
        String[] image = {};
        sp.setImageArray(imageArray.toArray(image));
        sp.setUrl(shareUrl);   //网友点进链接后，可以看到分享的详情
        Platform wechat = ShareSDK.getPlatform(WechatFavorite.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(appContext, "收藏成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(appContext, throwable.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(appContext, "收藏已取消", Toast.LENGTH_SHORT).show();
            }
        });
        // 执行分享
        wechat.share(sp);
        finish();
    }

    @SuppressLint("LongLogTag")
    private void shareToMoments() {
        WechatMoments.ShareParams momentsSp = new WechatMoments.ShareParams();
//        momentsSp.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
        momentsSp.setShareType(Platform.SHARE_IMAGE);
//        momentsSp.setTitle("车易售");  //分享标题
        momentsSp.setText(article);   //分享文本
//        momentsSp.setUrl("www.baidu.com");   //网友点进链接后，可以看到分享的详情
        String[] image = {};
        momentsSp.setImageArray(imageArray.toArray(image));
        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
        wechatMoments.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e(TAG, "onComplete: ");
                Toast.makeText(appContext, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e(TAG, "onError: " + throwable.toString());
                Toast.makeText(appContext, throwable.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e(TAG, "onCancel: ");
                Toast.makeText(appContext, "取消已分享", Toast.LENGTH_SHORT).show();
            }
        });
        // 执行分享
        wechatMoments.share(momentsSp);
        finish();
    }

    private void shareToFriend() {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
        sp.setTitle("车易售"); //分享标题
        sp.setText(article);   //分享文本
        String[] image = {};
        sp.setImageArray(imageArray.toArray(image));
        sp.setUrl(shareUrl);   //网友点进链接后，可以看到分享的详情
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(appContext, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(appContext, throwable.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(appContext, "分享已取消", Toast.LENGTH_SHORT).show();
            }
        });
        // 执行分享
        wechat.share(sp);
        finish();
    }
}
