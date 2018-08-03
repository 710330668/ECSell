package com.example.com.careasysell.main.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.main.entrance.StartActivity;
import com.example.com.careasysell.remote.Injection;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.SP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/1.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public int bindLayout() {
        return R.layout.ac_login;
    }

    @Override
    public void initParams(Bundle params) {

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

    @SuppressLint("CheckResult")
    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String userName = etUserName.getText().toString();
        String passWord = etPassword.getText().toString();
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWord)){
            Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Injection.provideApiService().login(userName,passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        if(loginResponse.getCode() == 200){
                            saveUserInfor(loginResponse);
                            startActivity(StartActivity.class);
                        }else{
                            Toast.makeText(LoginActivity.this,loginResponse.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserInfor(LoginResponse loginResponse) {
        SP.getInstance(C.USER_DB,LoginActivity.this).put(C.USER_TOKEN,loginResponse.getData().getToken());
        SP.getInstance(C.USER_DB,LoginActivity.this).put(C.USER_TYPE,loginResponse.getData().getUserType());
    }
}
