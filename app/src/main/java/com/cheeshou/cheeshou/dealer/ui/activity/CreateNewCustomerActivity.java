package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerWantCarModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.CustomerWantCarViewHolder;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.options.ChooseBrandActivity;
import com.cheeshou.cheeshou.options.contract.ICarSell;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.NotifyCallBackManager;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.CommonDialog;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.util.SP;
import com.example.com.common.util.ToastUtils;
import com.google.gson.Gson;

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
    @BindView(R.id.et_customer_order)
    EditText mEtRemark;
    @BindView(R.id.recycler_want_car)
    RecyclerView mRecycler;
    private SettingDelegate delegate;
    private BaseAdapter adapter;
    private List<ItemData> dataList = new ArrayList<>();
    private boolean optionId;
    @BindView(R.id.tv_want_car_number)
    TextView mTvCarNum;
    @BindView(R.id.tv_daikuan)
    TextView mTvDaikuan;
    private String reserveColumn1 = "否";

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
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        delegate = new SettingDelegate();
        delegate.setCustomerWantCarDeleteListener(new CustomerWantCarViewHolder.OnDeleteListener() {
            @Override
            public void onDeleteClick(int position) {
                dataList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new BaseAdapter(dataList, delegate);
        mRecycler.setAdapter(adapter);

        NotifyCallBackManager.getInstance().registPagerCloseCallBack(new ICarSell.IPagerClose() {
            @Override
            public void close() {
                CustomerWantCarModel model = ParamManager.getInstance(CreateNewCustomerActivity.this).getModel();
                dataList.add(new ItemData(0, SettingDelegate.CUSTOMER_WANT_CAR, model));
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String createCustomerWantCarId = ParamManager.getInstance(CreateNewCustomerActivity.this).getCreateCustomerWantCarId();
        int num = 0;
        if (!TextUtils.isEmpty(createCustomerWantCarId)) {
            for (int i = 0; i < createCustomerWantCarId.length(); i++) {
                if (createCustomerWantCarId.substring(i, (i + 1)).indexOf(',') != -1) {
                    num = num + 1;
                }
            }
        }
        mTvCarNum.setText(num + "辆");
    }

    @OnClick({R.id.ll_contacts, R.id.save_customer, R.id.img_back, R.id.tv_add_car, R.id.ll_want_car, R.id.ll_daikuan})
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
            case R.id.tv_add_car:
                intent = new Intent(this, ChooseBrandActivity.class);
                intent.putExtra("optionId", optionId);
                startActivity(intent);
                break;
            case R.id.ll_want_car:
                startActivity(CustomerWantCarActivity.class);
                break;
            case R.id.ll_daikuan:
                final CommonDialog commonDialog = new CommonDialog(this, "选择保险贷款", "是否有保险贷款需求", "是", "否");
                commonDialog.show();
                commonDialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        mTvDaikuan.setText("是");
                        reserveColumn1 = "是";
                        commonDialog.dismiss();
                    }

                    @Override
                    public void doConfirm(String etContent) {
                    }

                    @Override
                    public void doCancel() {
                        mTvDaikuan.setText("否");
                        reserveColumn1 = "否";
                        commonDialog.dismiss();
                    }
                });
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
            params.put("remark", toRequestBody(mEtRemark.getText().toString()));
            RadioButton viewById = (RadioButton) findViewById(mRgType.getCheckedRadioButtonId());
            if (viewById != null) {
                params.put("commType", toRequestBody(viewById.getText().toString()));
            } else {

                ToastUtils.showShort(this, "请选择沟通方式");
//                params.put("commType", toRequestBody(""));
            }
            String createCustomerWantCarId = ParamManager.getInstance(this).getCreateCustomerWantCarId();
            params.put("saleIds", toRequestBody(TextUtils.isEmpty(createCustomerWantCarId) ? "" : createCustomerWantCarId));
            params.put("reserveColumn1", toRequestBody(reserveColumn1));
            CustomerWantCarModel.CodeBean[] strings = new CustomerWantCarModel.CodeBean[dataList.size()];
            for (int i = 0; i < dataList.size(); i++) {
                CustomerWantCarModel.CodeBean code = ((CustomerWantCarModel) dataList.get(i).getData()).getCode();
                strings[i] = code;
            }
            params.put("versionJson", toRequestBody(new Gson().toJson(strings)));
            Injection.provideApiService().saveCustomerInfo(token, params).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<EasyResponse>() {
                        @Override
                        public void accept(EasyResponse easyResponse) throws Exception {
//                                Log.e(TAG, "accept: " + easyResponse.getMsg());
                            if (easyResponse.getCode() == 200) {
                                Toast.makeText(appContext, "创建成功", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                            } else if (easyResponse.getCode() == 402 || easyResponse.getCode() == 401) {
                                //token失效
                                SP.getInstance(C.USER_DB, CreateNewCustomerActivity.this).put(C.USER_ACCOUNT, "");
                                SP.getInstance(C.USER_DB, CreateNewCustomerActivity.this).put(C.USER_PASSWORD, "");
                                finishAllActivity();
                                startActivity(LoginActivity.class);
                            } else {
                                Toast.makeText(appContext, easyResponse.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            SP.getInstance(C.USER_DB, CreateNewCustomerActivity.this).put(C.USER_ACCOUNT, "");
                            SP.getInstance(C.USER_DB, CreateNewCustomerActivity.this).put(C.USER_PASSWORD, "");
                            finishAllActivity();
                            startActivity(LoginActivity.class);
                        }
                    });
        }
    }
}
