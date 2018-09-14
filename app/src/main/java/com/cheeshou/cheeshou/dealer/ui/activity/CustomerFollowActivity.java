package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CarStateModel;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.FindSuccessCarResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.options.ModifyCarInfActivity;
import com.cheeshou.cheeshou.options.model.CarPhotoModel;
import com.cheeshou.cheeshou.options.viewHolder.CarPhotoViewHolder;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.example.com.common.util.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CustomerFollowActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView mImageBack;
    @BindView(R.id.et_follow_record)
    EditText mEtFollowRecord;

    @BindView(R.id.rl_car_photo)
    RecyclerView rlCarPhoto;
    @BindView(R.id.rb_command_type)
    RadioGroup mCommandType;

    @BindView(R.id.ll_dealer)
    LinearLayout mLLDealer;
    @BindView(R.id.tv_dealer)
    TextView mTvDealer;

    @BindView(R.id.rl_deal_car)
    RecyclerView mRecycler;

    private static final String TAG = "CustomerFollowActivity";

    private ArrayList<String> imgPaths = new ArrayList<>();
    private List<ItemData> carPhotos = new ArrayList<>();
    private List<Bitmap> photos = new ArrayList<>();

    private final int REQUEST_LOCAL = 2;
    private final int REQUEST_SUCCESS_CAR = 3;
    private BaseAdapter imageDeleteAdapter;
    private String imgUrl = "";
    private String customerId = "";
    private String commandType = "";
    private String status = "";
    private String token = "";
    private ArrayList<ItemData> stateData = new ArrayList<>();
    private BaseAdapter adapter;
    @BindView(R.id.recycler_follow_car)
    RecyclerView mRecyclerFollowCar;
    private List<ItemData> mFollowCarData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_customer_follow;
    }

    @Override
    public void initParams(Bundle params) {
        customerId = params.getString("customerId");
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);

        stateData = new ArrayList<>();
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("未到店", "NO_STORE")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已到店", "YES_STORE")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已预定", "RESERVE")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已成交", "SUCCESS")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("战败", "FAIL")));
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerFollowCar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mCommandType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_type_daodian:
                        commandType = "到店";
                        break;
                    case R.id.rb_type_dianhua:
                        commandType = "电话";
                        break;
                    case R.id.rb_type_weixin:
                        commandType = "微信";
                        break;
                    case R.id.rb_type_duanxin:
                        commandType = "短信";
                        break;
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

//        Injection.provideApiService().findSoldOutCarList(token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ShareRankResponse>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(ShareRankResponse shareRankResponse) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
        onItemClickListener listener = new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                for (ItemData bean : stateData) {
                    ((CarStateModel) bean.getData()).setSelected(false);
                }
                status = ((CarStateModel) data).getStateCode();
                ((CarStateModel) data).setSelected(true);
                if (status == "SUCCESS") {
                    mLLDealer.setVisibility(View.VISIBLE);
                    mRecyclerFollowCar.setVisibility(View.VISIBLE);
                } else {
                    mLLDealer.setVisibility(View.GONE);
                    mRecyclerFollowCar.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        };
        adapter = new BaseAdapter(stateData, new SettingDelegate(), listener);
        mRecycler.setAdapter(adapter);
    }

    @OnClick({R.id.img_back, R.id.lly_local_image, R.id.tv_save, R.id.tv_dealer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.lly_local_image:
                Intent intent1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent1.setType("image/*");
                startActivityForResult(intent1, REQUEST_LOCAL);
                break;
            case R.id.tv_save:
                saveProgressInfo();
                break;
            case R.id.tv_dealer:
//                添加意向车辆
//                Bundle bundle = new Bundle();
//                bundle.putString("customerId", customerId);
//                startActivity(CustomerCarFollowActivity.class, bundle);
                Intent intent = new Intent(this, FindSuccessCarActivity.class);
                startActivityForResult(intent, REQUEST_SUCCESS_CAR);
                break;
        }
    }

    private void saveProgressInfo() {
        Map<String, RequestBody> params = new HashMap<>();
        List<MultipartBody.Part> parts = new ArrayList<>();
        if (imgPaths.size() <= 0) {
            ToastUtils.showShort(this, "请上传图片");
            return;
        }
        for (int i = 0; i < imgPaths.size(); i++) {
            File file = new File(imgPaths.get(i));
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
            parts.add(body);
        }
//        customerId:客户
//        content：内容
//        type：类型 电话 微信
//        customerStatus：客户状态
//        files：跟进图片
        params.put("customerId", toRequestBody(customerId));
        params.put("content", toRequestBody(mEtFollowRecord.getText().toString()));
        params.put("type", toRequestBody(commandType));
        params.put("customerStatus", toRequestBody(status));
        Injection.provideApiService().saveCustomerProgressInfo(token, parts, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<EasyResponse>() {
            @Override
            public void accept(EasyResponse easyResponse) throws Exception {
                if (easyResponse != null && easyResponse.getCode() == 200) {
                    Toast.makeText(appContext, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (easyResponse.getCode() == 402 || easyResponse.getCode() == 401) {
                    //token失效
                    SP.getInstance(C.USER_DB, CustomerFollowActivity.this).put(C.USER_ACCOUNT, "");
                    SP.getInstance(C.USER_DB, CustomerFollowActivity.this).put(C.USER_PASSWORD, "");
                    finishAllActivity();
                    startActivity(LoginActivity.class);
                } else {
                    Toast.makeText(appContext, easyResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
//                Log.e(TAG, "accept: " + new Gson().toJson(easyResponse));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                SP.getInstance(C.USER_DB, CustomerFollowActivity.this).put(C.USER_ACCOUNT, "");
                SP.getInstance(C.USER_DB, CustomerFollowActivity.this).put(C.USER_PASSWORD, "");
                finishAllActivity();
                startActivity(LoginActivity.class);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        carPhotos.clear();
        imgPaths.clear();
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_LOCAL) {
                Uri uri = data.getData();
                String img_url = uri.getPath();
                ContentResolver cr = this.getContentResolver();
                try {
                    if (photos.size() == 9) {
                        Toast.makeText(CustomerFollowActivity.this, "最多可添加9张图片", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    //最多九张
                    photos.add(bitmap);
                    for (int i = 0; i < photos.size(); i++) {
                        final CarPhotoModel carPhotoModel = new CarPhotoModel(photos.get(i));
                        ItemData itemData = new ItemData(i, SettingDelegate.CAR_PHOTO_TYPE, carPhotoModel);
                        carPhotos.add(itemData);
                        rlCarPhoto.setLayoutManager(new GridLayoutManager(this, 3));
                        SettingDelegate delegate = new SettingDelegate();
                        delegate.setOnImageDeleteListener(new CarPhotoViewHolder.OnImageDeleteListener() {
                            @Override
                            public void removeImage(int position) {
                                carPhotos.remove(position);
                                photos.remove(position);
                                imgPaths.remove(position);
                                imageDeleteAdapter.notifyDataSetChanged();
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
                        rlCarPhoto.setAdapter(imageDeleteAdapter);
                        imgUrl = getPath(data);
                        imgPaths.add(imgUrl);
                    }
                } catch (FileNotFoundException e) {
                }
            }
            if (requestCode == REQUEST_SUCCESS_CAR) {
                ArrayList<FindSuccessCarResponse.DataBean> data1 = data.getParcelableArrayListExtra("data");
                List<ItemData> dataList = new ArrayList<>();
                if (data1.size() > 0) {
                    mTvDealer.setText("重选");
                } else {
                    mTvDealer.setText("添加");
                }
                for (FindSuccessCarResponse.DataBean bean : data1) {
                    ItemData itemData = new ItemData(0, SettingDelegate.FIND_SUCCESS_CAR_LIST_TYPE, bean);
                    dataList.add(itemData);
                }

                mRecyclerFollowCar.setAdapter(new BaseAdapter(dataList, new SettingDelegate()));
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

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        List<SearchResultModel> customerFollowList = ParamManager.getInstance(getApplicationContext()).getCustomerFollowList();
//        mFollowCarData.clear();
//        for (SearchResultModel bean : customerFollowList) {
//            ItemData itemData = new ItemData(0, SettingDelegate.SEARCH_RESULT_TYPE, bean);
//            mFollowCarData.add(itemData);
//        }
//        mRecyclerFollowCar.setAdapter(new BaseAdapter(mFollowCarData, new SettingDelegate()));
    }
}
