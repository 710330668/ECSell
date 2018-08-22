package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerInfoResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.R;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.SP;
import com.example.com.common.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditCustomerMessageActivity extends BaseActivity {

    private String customerId;
    private String token;

    private static final String TAG = "EditCustomerMessageActi";

    @BindView(R.id.et_message_name)
    EditText mEtName;
    @BindView(R.id.et_message_phone)
    EditText mEtPhone;
    @BindView(R.id.et_message_wechat)
    EditText mEtWeChat;
    @BindView(R.id.radio_sex_group)
    RadioGroup mRgSex;

    private int sex = 0;

    @Override
    public int bindLayout() {
        return R.layout.activity_edit_customer_message;
    }

    @Override
    public void initParams(Bundle params) {
        customerId = params.getString("customerId");
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_sex_boy:
                        sex = 0;
                        break;
                    case R.id.rb_sex_girl:
                        sex = 1;
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        Map<String, RequestBody> params = new HashMap<>();
        params.put("customerId", RequestBody.create(MediaType.parse("text/plain"), customerId));
        Injection.provideApiService().getCustomerInfo(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CustomerInfoResponse>() {
            @Override
            public void accept(CustomerInfoResponse s) throws Exception {
                if (s != null && s.getCode() == 200) {
                    CustomerInfoResponse.DataBean data = s.getData();
                    mEtName.setText(data.getName());
                    mEtPhone.setText(data.getPhone());
                    mEtWeChat.setText(data.getWeBcat());
                    sex = data.getSex();
                    ((RadioButton) mRgSex.getChildAt(data.getSex())).setChecked(true);
                }
            }
        });
    }

    @OnClick({R.id.img_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.tv_save:
                Map<String, RequestBody> params = new HashMap<>();
                params.put("customerId", toRequestBody(customerId));
                params.put("name", toRequestBody(mEtName.getText().toString()));
                params.put("phone", toRequestBody(mEtPhone.getText().toString()));
                params.put("weBcat", toRequestBody(mEtWeChat.getText().toString()));
                params.put("sex", toRequestBody(sex + ""));
                Injection.provideApiService().updateCustomerInfo(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<EasyResponse>() {
                    @Override
                    public void accept(EasyResponse easyResponse) throws Exception {
                        if (easyResponse.getCode() == 200) {
                            ToastUtils.showShort(EditCustomerMessageActivity.this, "修改成功");
                            EditCustomerMessageActivity.this.finish();
                        } else {
                            ToastUtils.showShort(EditCustomerMessageActivity.this, easyResponse.getMsg());
                        }
                    }
                });
                break;
            default:
        }
    }

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }
}
