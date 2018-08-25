package com.cheeshou.cheeshou.market.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cheeshou.cheeshou.options.model.CarPhotoModel;
import com.cheeshou.cheeshou.options.viewHolder.CarPhotoViewHolder;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.options.TabEntity;
import com.cheeshou.cheeshou.options.model.CarPhotoModel;
import com.cheeshou.cheeshou.options.viewHolder.CarPhotoViewHolder;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;

public class MarketShareCarActivity extends BaseActivity {

    private ArrayList<SearchResultModel> data;

    @BindView(R.id.img_back)
    ImageView mImageBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_to_share)
    TextView mTvShare;
    @BindView(R.id.rcl_share_photos)
    RecyclerView mRecycler;
    @BindView(R.id.ll_bottom_layout)
    LinearLayout mRlBottom;
    @BindView(R.id.view_line)
    View viewLine;
    CommonTabLayout mainTabBar;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<ItemData> carPhotos = new ArrayList<>();
    private BaseAdapter imageDeleteAdapter;
    private String[] mTitles = {"标准版", "配置版"};
    private int[] mIconUnselectIds = {R.drawable.drawable_empty_small, R.drawable.drawable_empty_small};
    private int[] mIconSelectIds = {R.drawable.drawable_empty_small, R.drawable.drawable_empty_small};
    private View convertView;
    private EditText mEtShare;
    private PopupWindow mPopupWindow;
    private String article;
    private List<String> imageArray = new ArrayList<>();
    private static final String TAG = "MarketShareCarActivity";


    @Override
    public int bindLayout() {
        return R.layout.activity_market_share_car;
    }

    @Override
    public void initParams(Bundle params) {
        data = params.getParcelableArrayList("data");
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mTvTitle.setText("已选择" + data.size() + "辆车");
    }

    @Override
    public void doBusiness(Context mContext) {
        for (int i = 0; i < data.size(); i++) {
            final CarPhotoModel carPhotoModel = new CarPhotoModel(null, data.get(i).getImageUrl());
            article += data.get(i).getTitle() + "\n";
            imageArray.add(data.get(i).getImageUrl());
            ItemData itemData = new ItemData(i, SettingDelegate.CAR_PHOTO_TYPE, carPhotoModel);
            carPhotos.add(itemData);
            mRecycler.setLayoutManager(new GridLayoutManager(this, 3));
            SettingDelegate delegate = new SettingDelegate();
            delegate.setOnImageDeleteListener(new CarPhotoViewHolder.OnImageDeleteListener() {
                @Override
                public void removeImage(int position) {
//                    carPhotos.remove(position);
//                    imageDeleteAdapter.notifyDataSetChanged();
                }
            });
            imageDeleteAdapter = new BaseAdapter(carPhotos, delegate, new onItemClickListener() {
                @Override
                public void onClick(View v, Object data) {
                }

                @Override
                public boolean onLongClick(View v, Object data) {
                    return false;
                }
            });
            mRecycler.setAdapter(imageDeleteAdapter);
        }
    }


    @OnClick({R.id.img_back, R.id.tv_to_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.tv_to_share:
                if (mPopupWindow != null && mPopupWindow.isShowing()) {

                } else {
                    convertView = LayoutInflater.from(this).inflate(R.layout.layout_popup_article, null);
                    convertView.findViewById(R.id.tv_to_share).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO: 2018/8/22
                            Intent intent = new Intent(getApplicationContext(), MarketShareWechatActivity.class);
                            Bundle extras = new Bundle();
                            extras.putParcelableArrayList("data", data);
                            intent.putExtras(extras);
                            startActivity(intent);
//                            Wechat.ShareParams sp = new Wechat.ShareParams();
//                            sp.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
//                            sp.setTitle("车易售"); //分享标题
//                            String[] toArray = new String[imageArray.size()];
//                            sp.setImageArray(imageArray.toArray(toArray));
//                            sp.setText(article);   //分享文本
////                            sp.setUrl(url);   //网友点进链接后，可以看到分享的详情
//                            Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
////                            wechat.setPlatformActionListener(platformActionListener); // 设置分享事件回调
////                             执行分享
//                            wechat.share(sp);

//                            OnekeyShare oks = new OnekeyShare();
//                            // title标题，微信、QQ和QQ空间等平台使用
//                            oks.setTitle("车易售");
//                            // titleUrl QQ和QQ空间跳转链接
////                            oks.setTitleUrl("http://sharesdk.cn");
//                            // text是分享文本，所有平台都需要这个字段
//                            oks.setText(article);
//                            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////                            oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
////                            String[] toArray = new String[imageArray.size()];
////                            oks.setImageArray(imageArray.toArray(toArray));
//                            // url在微信、微博，Facebook等平台中使用
////                            oks.setUrl("http://sharesdk.cn");
//                            // comment是我对这条分享的评论，仅在人人网使用
////                            oks.setComment("我是测试评论文本");
//                            // 启动分享GUI
//                            oks.show(MarketShareCarActivity.this);
                        }
                    });
                    mEtShare = ((EditText) convertView.findViewById(R.id.et_share_article));
                    mEtShare.setText(article);
                    mainTabBar = convertView.findViewById(R.id.mainTabBar);
                    mTabEntities.clear();
                    for (int i = 0; i < mTitles.length; i++) {
                        mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
                    }
                    mainTabBar.setTabData(mTabEntities);
                    mainTabBar.setOnTabSelectListener(new OnTabSelectListener() {
                        @Override
                        public void onTabSelect(int position) {
                        }

                        @Override
                        public void onTabReselect(int position) {

                        }
                    });
                    mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    mPopupWindow.showAsDropDown(mImageBack);
                }
                break;
        }
    }
}
