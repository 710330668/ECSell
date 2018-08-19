package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.PutCarModel;
import com.example.com.careasysell.dealer.ui.model.SearchResultModel;
import com.example.com.careasysell.dealer.ui.model.response.EasyResponse;
import com.example.com.careasysell.options.CarDetailActivity;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
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
    }

    @Override
    public void doBusiness(Context mContext) {
        initData();
    }

    private void initData() {
        for (int i = 0; i < data.size(); i++) {
            ItemData e = new ItemData(0, SettingDelegate.PUT_AWAY_CAR_TYPE, data.get(i));
            PutAwayCarModel putAwayCarModel = new PutAwayCarModel(((SearchResultModel) data.get(i)).getId(), ((SearchResultModel) data.get(i)).getPrice());
            modelList.add(putAwayCarModel);
            mCarData.add(e);
        }
        BaseAdapter adapter = new BaseAdapter(mCarData, new SettingDelegate(), new onItemClickListener() {
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
        Injection.provideApiService().batchShelvesCarInfo(token, mEtInsuranceRebates.getText().toString(), mEtLoanRebates.getText().toString(), new Gson().toJson(modelList)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<EasyResponse>() {
            @Override
            public void accept(EasyResponse easyResponse) throws Exception {
                Log.e(TAG, "accept: " + new Gson().toJson(easyResponse));
                if (easyResponse.getCode() == 200) {
                    Toast.makeText(appContext, "上架成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(appContext, easyResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    private class PutAwayCarModel {
        private String carId;
        private String carPrice;

        public PutAwayCarModel(String carId, String carPrice) {
            this.carId = carId;
            this.carPrice = carPrice;
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
