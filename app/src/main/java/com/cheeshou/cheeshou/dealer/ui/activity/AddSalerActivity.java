package com.cheeshou.cheeshou.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.BitmapUtils;
import com.example.com.common.util.ContractUtil;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
public class AddSalerActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.et_sale_name)
    EditText etSaleName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_wechat)
    EditText etWechat;
    private String token;
    private Intent intent;
    public static final int REQUES_CODE_1 = 1;
    private final int REQUEST_LOCAL = 2;
    private String bitmap64Head;
    private String imgUrl;
    private Dialog myDialog;
    private Bitmap bitmap;

    @Override
    public int bindLayout() {
        return R.layout.activity_add_saler;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        myDialog = getLoadingDialog(this);
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_address_book, R.id.btn_save, R.id.iv_head})
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
            case R.id.tv_address_book:
                PackageManager pm = getPackageManager();
                boolean permission = (PackageManager.PERMISSION_GRANTED ==
                        pm.checkPermission("android.permission.READ_CONTACTS", getPackageName()));
                if (permission) {
                    startActivityForResult(intent, REQUES_CODE_1);
                } else {
                    Toast.makeText(AddSalerActivity.this, "请先打开通讯录权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_save:
                //保存
                savaInfor();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void savaInfor() {
        myDialog.show();
        try {
            if (!TextUtils.isEmpty(etAccount.getText().toString()) && !TextUtils.isEmpty(etPassword.getText().toString()) &&
                    !TextUtils.isEmpty(etSaleName.getText().toString()) && !TextUtils.isEmpty(etPhone.getText().toString())
                    && !TextUtils.isEmpty(etWechat.getText().toString()) && !TextUtils.isEmpty(bitmap64Head)) {
                Map<String, RequestBody> params = new HashMap<>();
                File file = new File(imgUrl);
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
                params.put("account", toRequestBody(etAccount.getText().toString()));
                params.put("password", toRequestBody(etPassword.getText().toString()));
                params.put("phone", toRequestBody(etPhone.getText().toString().trim()));
                params.put("userName", toRequestBody(etSaleName.getText().toString()));
                params.put("weChat", toRequestBody(etWechat.getText().toString()));
                params.put("sex", toRequestBody("0"));
                Injection.provideApiService().saveXsUserInfo(token, body, params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<EasyResponse>() {
                            @Override
                            public void accept(EasyResponse response) throws Exception {
                                LogUtils.e(response.getMsg());
                                myDialog.dismiss();
                                if (response.getCode() == 200) {
                                    Toast.makeText(AddSalerActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if(response.getCode() == 402||response.getCode() == 401){
                                    //token失效
                                    SP.getInstance(C.USER_DB,AddSalerActivity.this).put(C.USER_ACCOUNT,"");
                                    SP.getInstance(C.USER_DB,AddSalerActivity.this).put(C.USER_PASSWORD,"");
                                    finishAllActivity();
                                    startActivity(LoginActivity.class);
                                }
                                else {
                                    Toast.makeText(AddSalerActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            } else {
                Toast.makeText(AddSalerActivity.this, "请保证信息完整", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            LogUtils.e(e.toString());
        }



    }

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUES_CODE_1:
                Uri contactData = data.getData();
                Cursor cursor = this.getContentResolver().query(contactData, null, null, null, null);
                String name = ContractUtil.getName(this, cursor).trim();
                String tel = ContractUtil.getTel(this, cursor).trim();
                etSaleName.setText(name);
                etPhone.setText(tel);
                break;
            case REQUEST_LOCAL:
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                try {
                    bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    ivHead.setImageBitmap(bitmap);
                    bitmap64Head = BitmapUtils.bitmapToBase64(bitmap);
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
                imgUrl = getPath(data);
                File file = new File(imgUrl);
                compressBmpToFile(bitmap,file);
                break;
            default:
                break;
        }
    }

    public static void compressBmpToFile(Bitmap bmp,File file){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
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
