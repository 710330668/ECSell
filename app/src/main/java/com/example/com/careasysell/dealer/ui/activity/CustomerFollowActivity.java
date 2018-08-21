package com.example.com.careasysell.dealer.ui.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.response.EasyResponse;
import com.example.com.careasysell.options.model.CarPhotoModel;
import com.example.com.careasysell.options.viewHolder.CarPhotoViewHolder;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

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
    @BindView(R.id.rg_costumer_status)
    RadioGroup mCoustumerStatus;

    @BindView(R.id.ll_dealer)
    LinearLayout mLLDealer;
    @BindView(R.id.tv_dealer)
    TextView mTvDealer;

    private static final String TAG = "CustomerFollowActivity";

    private ArrayList<String> imgPaths = new ArrayList<>();
    private List<ItemData> carPhotos = new ArrayList<>();
    private List<Bitmap> photos = new ArrayList<>();

    private final int REQUEST_LOCAL = 2;
    private BaseAdapter imageDeleteAdapter;
    private String imgUrl;
    private String customerId;
    private String commandType;
    private String status;
    private String token;

    @Override
    public int bindLayout() {
        return R.layout.activity_customer_follow;
    }

    @Override
    public void initParams(Bundle params) {
        customerId = params.getString("customerId");
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
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

        mCoustumerStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_status_weidaodain:
                        status = "NO_STORE";
                        break;
                    case R.id.rb_status_yichengjiao:
                        status = "SUCCESS";
                        break;
                    case R.id.rb_status_yidaodian:
                        status = "YES_STORE";
                        break;
                    case R.id.rb_status_yiyuding:
                        status = "RESERVE";
                        break;
                    case R.id.rb_status_zhanbai:
                        status = "FAIL";
                        break;
                }
                mLLDealer.setVisibility(checkedId == R.id.rb_status_yichengjiao ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.img_back, R.id.lly_local_image, R.id.tv_save, R.id.tv_dealer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.lly_local_image:
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent1, REQUEST_LOCAL);
                break;
            case R.id.tv_save:
                saveProgressInfo();
                break;
            case R.id.tv_dealer:
//                添加意向车辆

                break;
        }
    }

    private void saveProgressInfo() {
        Map<String, RequestBody> params = new HashMap<>();
        List<MultipartBody.Part> parts = new ArrayList<>();
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
                } else {
                    Toast.makeText(appContext, easyResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
//                Log.e(TAG, "accept: " + new Gson().toJson(easyResponse));
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

}
