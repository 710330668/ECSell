package com.cheeshou.cheeshou.usercenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/20.
 */
public class ModifyPasswordActivity extends BaseActivity {


    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    private String token;

    @Override
    public int bindLayout() {
        return R.layout.activity_modify_password;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
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

    @OnClick({R.id.iv_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                if(TextUtils.isEmpty(etOldPassword.getText().toString())||
                        TextUtils.isEmpty(etNewPassword.getText().toString())||
                        TextUtils.isEmpty(etConfirmPassword.getText().toString())){
                    ToastUtils.showLong(ModifyPasswordActivity.this,"请完善信息");
                    return;
                }
                if(!TextUtils.equals(etNewPassword.getText().toString(),etConfirmPassword.getText().toString())){
                    ToastUtils.showLong(ModifyPasswordActivity.this,"请保证密码一致");
                    return;
                }
                modifyPassword();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void modifyPassword() {
        Injection.provideApiService().updateMyPassword(token,etNewPassword.getText().toString(),
                etConfirmPassword.getText().toString(),etOldPassword.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EasyResponse>() {
                    @Override
                    public void accept(EasyResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            ToastUtils.showShort(ModifyPasswordActivity.this,response.getMsg());
                            finish();
                        }else if(response.getCode() == 402||response.getCode() == 401){
                            //token失效
                            SP.getInstance(C.USER_DB,ModifyPasswordActivity.this).put(C.USER_ACCOUNT,"");
                            SP.getInstance(C.USER_DB,ModifyPasswordActivity.this).put(C.USER_PASSWORD,"");
                            finishAllActivity();
                            startActivity(LoginActivity.class);
                        }
                        else{
                            ToastUtils.showShort(ModifyPasswordActivity.this,response.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SP.getInstance(C.USER_DB,ModifyPasswordActivity.this).put(C.USER_ACCOUNT,"");
                        SP.getInstance(C.USER_DB,ModifyPasswordActivity.this).put(C.USER_PASSWORD,"");
                        finishAllActivity();
                        startActivity(LoginActivity.class);
                    }
                });
    }
}
