
package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerWantCarModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.FindCustomerNeedResponse;
import com.cheeshou.cheeshou.dealer.ui.viewHolder.CustomerWantCarViewHolder;
import com.cheeshou.cheeshou.options.ChooseBrandActivity;
import com.cheeshou.cheeshou.options.ReleaseOptionActivity;
import com.cheeshou.cheeshou.options.contract.ICarSell;
import com.cheeshou.cheeshou.options.model.CarPhotoModel;
import com.cheeshou.cheeshou.options.model.response.CarDetailResponse;
import com.cheeshou.cheeshou.options.viewHolder.CarPhotoViewHolder;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.NotifyCallBackManager;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.CommonDialog;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditCustomerNeedActivity extends BaseActivity {

    private String customerId;

    @BindView(R.id.recycler_want_car)
    RecyclerView mRecycler;
    @BindView(R.id.tv_need_text)
    EditText mEtNeedText;
    @BindView(R.id.tv_remark)
    EditText mEtRemark;
    @BindView(R.id.et_min_money)
    EditText mEtMinMoney;
    @BindView(R.id.et_max_money)
    EditText mEtMaxMoney;
    private String token;
    private static final String TAG = "EditCustomerNeedActivit";

    private String optionId;
    private final int REQUEST_BRAND = 0;
    private List<ItemData> dataList = new ArrayList<>();
    private SettingDelegate delegate;
    private BaseAdapter adapter;
    @BindView(R.id.tv_daikuan)
    TextView mTvDaiKuan;
    private String reserveColumn1;

    @Override
    public int bindLayout() {
        return R.layout.activity_edit_customer_need;
    }

    @Override
    public void initParams(Bundle params) {
        customerId = params.getString("customerId");
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
                CustomerWantCarModel model = ParamManager.getInstance(EditCustomerNeedActivity.this).getModel();
                dataList.add(new ItemData(0, SettingDelegate.CUSTOMER_WANT_CAR, model));
                adapter.notifyDataSetChanged();
            }
        });

        Injection.provideApiService().findCustomerNeedInfo(token, customerId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<FindCustomerNeedResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FindCustomerNeedResponse easyResponse) {
                mEtMinMoney.setText(easyResponse.getData().getMinBudget() + "");
                mEtMaxMoney.setText(easyResponse.getData().getMaxBudget() + "");
                mEtNeedText.setText(easyResponse.getData().getNeedTxt());
                mEtRemark.setText(easyResponse.getData().getRemark());
                mTvDaiKuan.setText(easyResponse.getData().getReserveColumn1());
                List<FindCustomerNeedResponse.DataBean.ListsBean> lists = easyResponse.getData().getLists();
                for (FindCustomerNeedResponse.DataBean.ListsBean bean : lists) {
                    CustomerWantCarModel model = new CustomerWantCarModel();
                    model.setName(bean.getBrandName() + "|" + bean.getAudiName() + "|" + bean.getVersionName());
                    model.setCode(new CustomerWantCarModel.CodeBean(bean.getBrandId(), bean.getAudiId(), bean.getVersionId()));
                    dataList.add(new ItemData(0, SettingDelegate.CUSTOMER_WANT_CAR, model));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    @OnClick({R.id.img_back, R.id.tv_add_brand, R.id.tv_save, R.id.ll_daikuan})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.tv_add_brand:
                Intent intent = new Intent(this, ChooseBrandActivity.class);
                intent.putExtra("optionId", optionId);
                startActivity(intent);
                break;
            case R.id.tv_save:
                updateInfo();
                break;
            case R.id.ll_daikuan:
                final CommonDialog commonDialog = new CommonDialog(this, "选择保险贷款", "是否有保险贷款需求", "是", "否");
                commonDialog.show();
                commonDialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        mTvDaiKuan.setText("是");
                        reserveColumn1 = "是";
                        commonDialog.dismiss();
                    }

                    @Override
                    public void doConfirm(String etContent) {
                    }

                    @Override
                    public void doCancel() {
                        mTvDaiKuan.setText("否");
                        reserveColumn1 = "否";
                        commonDialog.dismiss();
                    }
                });
                break;
            default:
        }
    }

    private void updateInfo() {
        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("customerId", toRequestBody(customerId));
        params.put("needTxt", toRequestBody(mEtNeedText.getText().toString()));
        params.put("minBudget", toRequestBody(mEtMinMoney.getText().toString()));
        params.put("maxBudget", toRequestBody(mEtMaxMoney.getText().toString()));
        params.put("remark", toRequestBody(mEtRemark.getText().toString()));
        params.put("reserveColumn1", toRequestBody(reserveColumn1));
        CustomerWantCarModel.CodeBean[] strings = new CustomerWantCarModel.CodeBean[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            CustomerWantCarModel.CodeBean code = ((CustomerWantCarModel) dataList.get(i).getData()).getCode();
            strings[i] = code;
        }
        params.put("versionJson", toRequestBody(new Gson().toJson(strings)));
        Injection.provideApiService().updateCustomerNeedInfo(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<EasyResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EasyResponse easyResponse) {
                if (easyResponse != null && easyResponse.getCode() == 200) {
                    finish();
                    Toast.makeText(EditCustomerNeedActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditCustomerNeedActivity.this, easyResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: -" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
