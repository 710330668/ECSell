package com.cheeshou.cheeshou.usercenter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.usercenter.model.UserInforModel;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by 71033 on 2018/8/6.
 */
public class UserInforActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_modify_infor)
    TextView tvModifyInfor;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_account_name)
    EditText tvAccountName;
    @BindView(R.id.tv_username)
    EditText tvUsername;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_wechat)
    EditText tvWechat;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private String token;

    private String imgUrl;
    private Dialog myDialog;
    private String userId;


    private final int REQUEST_LOCAL = 2;

    @Override
    public int bindLayout() {
        return R.layout.activity_user_infor;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        myDialog = getLoadingDialog(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        getUserInfor();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @SuppressLint("CheckResult")
    private void getUserInfor() {
        Injection.provideApiService().getUserInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInforModel>() {
                    @Override
                    public void accept(UserInforModel response) throws Exception {
                        try {
                            if (response.getCode() == 200) {
                                tvAccountName.setText(response.getData().getAccount());
                                tvUsername.setText(response.getData().getUserName());
                                userId = response.getData().getUserId();
                            }
                        } catch (Exception e) {

                        }
                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.iv_head, R.id.tv_modify_infor,R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_head:
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent1, REQUEST_LOCAL);
                break;
            case R.id.tv_modify_infor:
                btnConfirm.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_confirm:
                savaInfor();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void savaInfor() {
        myDialog.show();
        Map<String, RequestBody> params = new HashMap<>();
        params.put("account", toRequestBody(tvAccountName.getText().toString()));
        params.put("phone", toRequestBody(tvPhone.getText().toString().trim()));
        params.put("userName", toRequestBody(tvUsername.getText().toString()));
        params.put("weChat", toRequestBody(tvWechat.getText().toString()));
        params.put("userId", toRequestBody(userId));
        if(!TextUtils.isEmpty(imgUrl)){
            File file = new File(imgUrl);
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
            Injection.provideApiService().saveXsUserInfo(token, body, params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<EasyResponse>() {
                        @Override
                        public void accept(EasyResponse response) throws Exception {
                            myDialog.dismiss();
                            LogUtils.e(response.getMsg());
                            if (response.getCode() == 200) {
                                Toast.makeText(UserInforActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UserInforActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Injection.provideApiService().saveXsUserInfo2(token, params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<EasyResponse>() {
                        @Override
                        public void accept(EasyResponse response) throws Exception {
                            LogUtils.e(response.getMsg());
                            myDialog.dismiss();
                            if (response.getCode() == 200) {
                                Toast.makeText(UserInforActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UserInforActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }



    }


    public RequestBody toRequestBody(String value){
        return RequestBody.create(MediaType.parse("text/plain"),value);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_LOCAL:
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    ivHead.setImageBitmap(bitmap);
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
                imgUrl = getPath(data);
                break;
            default:
                break;
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
