package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.options.CarDetailActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.usercenter.DealershipActivity;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PutAwayDetailActivity extends BaseActivity {


    @BindView(R.id.recycler_put_away_car)
    RecyclerView mRecyclerPutCar;
    @BindView(R.id.fm_put_away)
    FrameLayout mFmPutAway;

    @BindView(R.id.et_insurance_rebates)
    EditText mEtInsuranceRebates;
    @BindView(R.id.et_loan_rebates)
    EditText mEtLoanRebates;
    private List<ItemData> mCarData = new ArrayList<>();
    private String token;
    private static final String TAG = "PutAwayDetailActivity";
    private ArrayList<SearchResultModel> data;
    private List<PutAwayCarModel> modelList = new ArrayList<>();


    @BindView(R.id.check_percent_up)
    CheckBox mCheckPercentUp;
    @BindView(R.id.check_price_up)
    CheckBox mCheckPriceUp;
    @BindView(R.id.et_percent_up)
    EditText mEtPercentUp;
    @BindView(R.id.et_price_up)
    EditText mEtPriceUp;
    private BaseAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_put_away_detail;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        data = params.getParcelableArrayList("data");
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        LinearLayoutManager fullyLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerPutCar.setLayoutManager(fullyLinearLayoutManager);
        mRecyclerPutCar.addItemDecoration(new SpaceItemDecoration(5));
        mCheckPercentUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEtPriceUp.setEnabled(!isChecked);
                if (isChecked) {
                    mCheckPriceUp.setChecked(false);
                }
            }
        });
        mCheckPriceUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEtPercentUp.setEnabled(!isChecked);
                if (isChecked) {
                    mCheckPercentUp.setChecked(false);
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        initData();
        mEtPercentUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    String s1 = mEtPercentUp.getText().toString();
                    Double v ;
                    if(TextUtils.isEmpty(s1)){
                        v = 0.0;
                    }else{
                        v = Double.valueOf(s1);
                    }
                    for (SearchResultModel bean : data) {
                        String price = bean.getPrice();
                        bean.setSalePrice(Integer.parseInt(price.substring(3, price.length() - 1)) * (1 + (v / 100)) + "");
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEtPriceUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = mEtPriceUp.getText().toString();
                Double aDouble;
                if(TextUtils.isEmpty(s1)){
                    aDouble = 0.0;
                }else{
                    aDouble = Double.valueOf(s1);
                }
                for (SearchResultModel bean : data) {

                    String price = bean.getPrice();
                    bean.setSalePrice((Integer.parseInt(price.substring(3, price.length() - 1)) + (aDouble / 10000)) + "");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initData() {
        for (int i = 0; i < data.size(); i++) {
            ItemData e = new ItemData(0, SettingDelegate.PUT_AWAY_CAR_TYPE);
            String price = data.get(i).getPrice();
            data.get(i).setSalePrice(price.substring(3, price.length() - 1));
            e.setData(data.get(i));
            mCarData.add(e);
        }
        adapter = new BaseAdapter(mCarData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                startActivity(CarDetailActivity.class);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecyclerPutCar.setAdapter(adapter);
    }

    @OnClick(R.id.fm_put_away)
    public void onViewClicked(View view) {
        for (int i = 0; i < data.size(); i++) {
            PutAwayCarModel putAwayCarModel = new PutAwayCarModel(((SearchResultModel) data.get(i)).getId(), ((SearchResultModel) data.get(i)).getSalePrice());
            String str = putAwayCarModel.getCarPrice();
//            Pattern p = Pattern.compile("\\d+");
//            Matcher m = p.matcher(str);
//            m.find();
            putAwayCarModel.setCarPrice(str);
            putAwayCarModel.setImageUrl(data.get(i).getImageUrl());
            modelList.add(putAwayCarModel);
        }
        Injection.provideApiService().batchShelvesCarInfo(token, mEtInsuranceRebates.getText().toString(), mEtLoanRebates.getText().toString(), new Gson().toJson(modelList)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<EasyResponse>() {
            @Override
            public void accept(EasyResponse easyResponse) throws Exception {
                if (easyResponse.getCode() == 200) {
                    Toast.makeText(appContext, "上架成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else if(easyResponse.getCode() == 402||easyResponse.getCode() == 401){
                    //token失效
                    SP.getInstance(C.USER_DB,PutAwayDetailActivity.this).put(C.USER_ACCOUNT,"");
                    SP.getInstance(C.USER_DB,PutAwayDetailActivity.this).put(C.USER_PASSWORD,"");
                    finishAllActivity();
                    startActivity(LoginActivity.class);
                }else {
                    Toast.makeText(appContext, easyResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                SP.getInstance(C.USER_DB,PutAwayDetailActivity.this).put(C.USER_ACCOUNT,"");
                SP.getInstance(C.USER_DB,PutAwayDetailActivity.this).put(C.USER_PASSWORD,"");
                finishAllActivity();
                startActivity(LoginActivity.class);
            }
        });
    }

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    private class PutAwayCarModel {
        private String carId;
        private String carPrice;
        private String imageUrl;

        public PutAwayCarModel(String carId, String carPrice) {
            this.carId = carId;
            this.carPrice = carPrice;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(String carPrice) {
            this.carPrice = carPrice;
        }
    }
}
