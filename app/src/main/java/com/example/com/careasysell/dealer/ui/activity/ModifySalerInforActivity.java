package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.response.EasyResponse;
import com.example.com.careasysell.dealer.ui.model.response.XsUserDetailResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.view.CommonDialog;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.ContractUtil;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.imageloader.LoaderManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/6.
 */
public class ModifySalerInforActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_delete_infor)
    TextView tvDeleteInfor;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_re_selection)
    TextView tvReSelection;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_address_hook)
    TextView tvAddressHook;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_wechat)
    EditText etWechat;
    @BindView(R.id.iv_head)
    ImageView ivHead;

    private Intent intent;
    private String userId;

    public static final int REQUES_CODE_1 = 1;
    private final int REQUEST_LOCAL = 2;
    private String token;
    public XsUserDetailResponse response;
    public CommonDialog resetPassDialog;

    @Override
    public int bindLayout() {
        return R.layout.activity_modify_saler_detail;
    }

    @Override
    public void initParams(Bundle params) {
        intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        userId = params.getString("userId");
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        response = (XsUserDetailResponse) params.getSerializable("response");
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        etAccount.setText(response.getData().getAccount());
        etName.setText(response.getData().getUserName());
        etPhone.setText(response.getData().getPhone());
        etPassword.setText("********");
        LoaderManager.getLoader().loadNet(ivHead,response.getData().getUserPic());
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

    @OnClick({R.id.iv_back, R.id.tv_delete_infor, R.id.btn_confirm, R.id.tv_reset, R.id.tv_re_selection, R.id.tv_address_hook})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_delete_infor:
                showDialog("确定删除销售?", "删除后,该销售信息将不可找回", "取消", "确定");
                break;
            case R.id.btn_confirm:
                break;
            case R.id.tv_reset:
                showDialog("重置密码","取消", "確定");
                break;
            case R.id.tv_re_selection:
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent1, REQUEST_LOCAL);
                break;
            case R.id.tv_address_hook:
                PackageManager pm = getPackageManager();
                boolean permission = (PackageManager.PERMISSION_GRANTED ==
                        pm.checkPermission("android.permission.READ_CONTACTS", getPackageName()));
                if (permission) {
                    startActivityForResult(intent, REQUES_CODE_1);
                } else {
                    Toast.makeText(ModifySalerInforActivity.this, "请先打开通讯录权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showDialog(String title, String content, String cancel, String confirm) {
        final CommonDialog dialog = new CommonDialog(this, title, content, confirm, cancel);
        dialog.show();
        dialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }

            @Override
            public void doConfirm(String etContent) {

            }

            @Override
            public void doCancel() {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void resetXsUserPass(String etContent) {
        Injection.provideApiService().resetXsUserPass(token,userId,etContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EasyResponse>() {
                    @Override
                    public void accept(EasyResponse response) throws Exception {
                        resetPassDialog.dismiss();
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            Toast.makeText(ModifySalerInforActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ModifySalerInforActivity.this,response.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void showDialog(String title,String cancel, String confirm) {
        resetPassDialog = new CommonDialog(this, title,confirm, cancel);
        resetPassDialog.show();
        resetPassDialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
                resetPassDialog.dismiss();
            }

            @Override
            public void doConfirm(String etContent) {
                //重置密码接口
                resetXsUserPass(etContent);
            }

            @Override
            public void doCancel() {
                // TODO Auto-generated method stub
                resetPassDialog.dismiss();
            }
        });
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
                etName.setText(name);
                etPhone.setText(tel);
                break;
            case REQUEST_LOCAL:
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    ivHead.setImageBitmap(bitmap);
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
                break;
            default:
                break;
        }
    }

}
