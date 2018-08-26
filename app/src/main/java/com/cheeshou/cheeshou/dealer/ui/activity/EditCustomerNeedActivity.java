
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
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerWantCarModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
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
    }


    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    @OnClick({R.id.img_back, R.id.tv_add_brand, R.id.tv_save})
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
        String[] strings = new String[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            CustomerWantCarModel.CodeBean code = ((CustomerWantCarModel) dataList.get(i).getData()).getCode();
            strings[i] = new Gson().toJson(code);
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
