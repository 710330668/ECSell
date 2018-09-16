package com.cheeshou.cheeshou.market.ui;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
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
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.ShareUrlResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.options.TabEntity;
import com.cheeshou.cheeshou.options.model.CarPhotoModel;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    private final int REQUEST_LOCAL = 1;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<ItemData> carPhotos = new ArrayList<>();
    private BaseAdapter imageDeleteAdapter;
    private String[] mTitles = {"标准版", "配置版"};
    //    private int[] mIconUnselectIds = {R.drawable.drawable_empty_small, R.drawable.drawable_empty_small};
//    private int[] mIconSelectIds = {R.drawable.drawable_empty_small, R.drawable.drawable_empty_small};
    private View convertView;
    private EditText mEtShare;
    private PopupWindow mPopupWindow;
    private String normalArticle = "";
    private String affineArticle = "";
    private String singleNormalArticle = "";
    private String singleAffineArticle = "";
    private List<String> imageArray = new ArrayList<>();
    private static final String TAG = "MarketShareCarActivity";
    private String mPhone;
    private String mAddress;
    private String mName;
    private String mCompany;
    private SettingDelegate delegate;
    private String url = "http://www.cheeshou.com/share/visitShareInfo?shareId=";
    private String shareUrl = "";
    private String token = "";
    private String shareType, shareItems;
    private String UuId;


    @Override
    public int bindLayout() {
        return R.layout.activity_market_share_car;
    }

    @Override
    public void initParams(Bundle params) {
        data = params.getParcelableArrayList("data");
        mPhone = SP.getInstance(C.USER_DB, this).getString(C.USER_PHONE);
        mAddress = SP.getInstance(C.USER_DB, this).getString(C.USER_ADDRESS);
        mName = SP.getInstance(C.USER_DB, this).getString(C.USER_NAME);
        mCompany = SP.getInstance(C.USER_DB, this).getString(C.USER_COMPANYNAME);
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mTvTitle.setText("已选择" + data.size() + "辆车");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("StringFormatMatches")
    @Override
    public void doBusiness(Context mContext) {
        mRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        delegate = new SettingDelegate();
        String id = "";
        for (int i = 0; i < data.size(); i++) {
            id += (data.get(i).getSaleId() + ",");
            final CarPhotoModel carPhotoModel = new CarPhotoModel(null, data.get(i).getImageUrl());
            imageArray.add(data.get(i).getImageUrl());
            ItemData itemData = new ItemData(0, SettingDelegate.SHARE_CAR_PHOTO_TYPE, carPhotoModel);
            carPhotos.add(itemData);
        }
        ItemData itemData = new ItemData(0, SettingDelegate.SHARE_CAR_PHOTO_ADD_TYPE);
        carPhotos.add(itemData);
        imageDeleteAdapter = new BaseAdapter(carPhotos, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                if (data == null) {
                    Intent intent1 = new Intent();
                    intent1.setType("image/*");
                    intent1.setAction(Intent.ACTION_PICK);
                    startActivityForResult(intent1, REQUEST_LOCAL);
                }
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecycler.setAdapter(imageDeleteAdapter);

        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("shareId", toRequestBody(UUID.randomUUID().toString()));

        if (data.size() == 1) {
            UuId = UUID.randomUUID().toString();
            normalArticle += ("  [品牌车型] " + "  " + data.get(0).getTitle() + "\n");
            normalArticle += ("  [价格] " + "  " + data.get(0).getPrice() + "\n");
            normalArticle += ("  [颜色]  " + "  " + "外观" + data.get(0).getOutsiteColor() + "内饰" + data.get(0).getWithinColor()+ "\n");
            normalArticle += ("  [销售]  " + "  " + mName + "\n");
            normalArticle += ("  [联系方式]  " + mPhone + "\n");
            normalArticle += ("  [链接]  " + "  " + url + UuId + "\n");

            affineArticle = getResources().getString(R.string.single_affine_article, data.size(), data.get(0).getTitle(), data.get(0).getPrice() + "元", data.get(0).getOutsiteColor(), data.get(0).getWithinColor(), url + UuId, mPhone, mName);


            shareType = "单车";
            shareItems = data.get(0).getSaleId();
//            params.put("shareType", toRequestBody("单车"));
//            params.put("shareItems", toRequestBody(data.get(0).getId()));


        } else {

            UuId = UUID.randomUUID().toString();
            normalArticle += ("  [诚信车商] " + mCompany + " \n");
            normalArticle += ("  [优质车源] " + url + UuId + "\n");
            normalArticle += ("  [联系方式]  " + mPhone + "  " + mName + "\n");
            normalArticle += ("  [看车地址]  " + mAddress + "\n");
            double maxPrice = 0, minPrice = Integer.MAX_VALUE;
            for (SearchResultModel bean : data) {
                String substring = bean.getPrice().substring(0, bean.getPrice().length() - 1);
                double integer = Double.parseDouble(substring);
                maxPrice = Double.max(maxPrice, integer);
                minPrice = Double.min(minPrice, integer);
            }
            affineArticle = getResources().getString(R.string.affine_article, data.size(), minPrice + "万", maxPrice + "万", url + UuId, mPhone, mName);

            shareType = "多车";
            shareItems = id;
            params.put("shareType", toRequestBody("多车"));
            params.put("shareItems", toRequestBody(id));
        }
        params.put("shareAtt", toRequestBody(affineArticle));
        params.put("shareDirect", toRequestBody("微信"));

//        params.put("shareId", (UUID.randomUUID().toString()));
//
//        if (data.size() == 1) {
//            params.put("shareType", ("单车"));
//            params.put("shareItems", (data.get(0).getId()));
//        } else {
//            params.put("shareType", ("多车"));
//            params.put("shareItems", (id));
//        }
////        params.put("shareAtt", (affineArticle));
//        params.put("shareDirect", ("微信"));


//        affineArticle = "";

        Injection.provideApiService().saveShareInfo(token, UuId, shareType, shareItems, "微信", affineArticle).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ShareUrlResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ShareUrlResponse easyResponse) {
                if (easyResponse.getCode() == 200) {
                    shareUrl = easyResponse.getData().getShareUrl();
                } else if (easyResponse.getCode() == 402 || easyResponse.getCode() == 401) {
                    //token失效
                    SP.getInstance(C.USER_DB, MarketShareCarActivity.this).put(C.USER_ACCOUNT, "");
                    SP.getInstance(C.USER_DB, MarketShareCarActivity.this).put(C.USER_PASSWORD, "");
                    finishAllActivity();
                    startActivity(LoginActivity.class);
                }
                Log.e(TAG, "onNext: " + new Gson().toJson(easyResponse));
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: -----" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
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
                            extras.putString("shareUrl", url + UuId);
                            ArrayList<CarPhotoModel> list = new ArrayList<>();
                            for (ItemData carPhoto : carPhotos) {
                                if (carPhoto.getData() != null) {
                                    list.add(((CarPhotoModel) carPhoto.getData()));
                                }
                            }
                            Log.e(TAG, "onClick: " + list.size());
                            extras.putParcelableArrayList("photo", list);
                            extras.putString("article", mEtShare.getText().toString());
                            intent.putExtras(extras);
                            startActivity(intent);
                        }
//                            Wechat.ShareParams sp = new Wechat.ShareParams();
//                            sp.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
//                            sp.setTitle("车易售"); //分享标题
//                            String[] toArray = new String[imageArray.size()];
//                            sp.setImageArray(imageArray.toArray(toArray));
//                            sp.setText(normalArticle);   //分享文本
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
//                            oks.setText(normalArticle);
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
//                        }
                    });

                    convertView.findViewById(R.id.tv_to_copy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            cm.setText(mEtShare.getText());
                            Toast.makeText(appContext, "复制成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    mEtShare = ((EditText) convertView.findViewById(R.id.et_share_article));
                    mEtShare.setText(normalArticle);
                    mainTabBar = convertView.findViewById(R.id.mainTabBar);
                    mTabEntities.clear();
                    for (int i = 0; i < mTitles.length; i++) {
                        mTabEntities.add(new TabEntity(mTitles[i]));
                    }
                    mainTabBar.setTabData(mTabEntities);
                    mainTabBar.setOnTabSelectListener(new OnTabSelectListener() {
                        @Override
                        public void onTabSelect(int position) {
                            if (position == 0) {
                                mEtShare.setText(normalArticle);
                            } else {
                                mEtShare.setText(affineArticle);
                            }
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

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_LOCAL) {
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                if (carPhotos.size() == 9) {
                    Toast.makeText(MarketShareCarActivity.this, "最多可添加9张图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                //最多九张
                CarPhotoModel data1 = new CarPhotoModel(null, uri.toString());
//                data1.setImageUrl(getPath(data));
                carPhotos.add(carPhotos.size() - 1, new ItemData(0, SettingDelegate.SHARE_CAR_PHOTO_TYPE, data1));
                imageDeleteAdapter.notifyDataSetChanged();
            } catch (FileNotFoundException e) {
            }
        }
    }

    private String getPath(Intent data) {
        String[] imgPath = {MediaStore.Images.Media.DATA};

        Cursor cursor = managedQuery(data.getData(), imgPath, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String path = cursor.getString(column_index);
        return path;
    }
}
