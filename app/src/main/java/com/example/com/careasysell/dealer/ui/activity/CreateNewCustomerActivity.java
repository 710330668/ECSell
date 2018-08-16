package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.response.EasyResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CreateNewCustomerActivity extends BaseActivity {

    private static final String TAG = "CreateNewCustomerActiviy";
    private String token;

    @BindView(R.id.et_customer_name)
    EditText mEtName;
    @BindView(R.id.et_customer_phone)
    EditText mEtPhone;
    @BindView(R.id.et_customer_wechat)
    EditText mEtWeChat;
    @BindView(R.id.rb_customer_sex)
    RadioButton mRbSex;
    @BindView(R.id.et_min_money)
    EditText mEtMinMoney;
    @BindView(R.id.et_max_money)
    EditText mEtMaxMoney;
    @BindView(R.id.et_customer_need)
    EditText mEtNeed;
    @BindView(R.id.rg_common_type)
    RadioGroup mRgType;

    @Override
    public int bindLayout() {
        return R.layout.activity_create_new_customer;
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

    @OnClick({R.id.ll_contacts, R.id.save_customer, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_contacts:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.PICK");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setType("vnd.android.cursor.dir/phone_v2");
                startActivity(intent);
                break;
            case R.id.save_customer:
                createNewCustomer();
                break;
            case R.id.img_back:
                finish();
                break;
            default:
        }
    }

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    private void createNewCustomer() {
        if (!TextUtils.isEmpty(mEtName.getText().toString()) && !TextUtils.isEmpty(mEtPhone.getText().toString()) && !TextUtils.isEmpty(mEtWeChat.getText().toString())) {
            Map<String, RequestBody> params = new HashMap<>();
            params.put("name", toRequestBody(mEtName.getText().toString()));
            params.put("phone", toRequestBody(mEtPhone.getText().toString()));
            params.put("weBcat", toRequestBody(mEtWeChat.getText().toString()));
            params.put("sex", toRequestBody(mRbSex.isChecked() ? "1" : "0"));
            params.put("minBudget", toRequestBody(mEtMinMoney.getText().toString()));
            params.put("maxBudget", toRequestBody(mEtMaxMoney.getText().toString()));
            params.put("needTxt", toRequestBody(mEtNeed.getText().toString()));
            params.put("commType", toRequestBody(((RadioButton) findViewById(mRgType.getCheckedRadioButtonId())).getText().toString()));
            params.put("saleIds", toRequestBody("1,1,1"));
            params.put("versionJson", toRequestBody(""));
            params.put("remark", toRequestBody(""));
            Injection.provideApiService().saveCustomerInfo(token, params).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<EasyResponse>() {
                        @Override
                        public void accept(EasyResponse easyResponse) throws Exception {
//                                Log.e(TAG, "accept: " + easyResponse.getMsg());
                            if (easyResponse.getCode() == 200) {
                                Toast.makeText(appContext, "创建成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(appContext, easyResponse.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
