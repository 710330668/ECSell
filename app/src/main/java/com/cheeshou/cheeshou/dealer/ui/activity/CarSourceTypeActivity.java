package com.cheeshou.cheeshou.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.options.model.OptionTypeModel;
import com.cheeshou.cheeshou.options.model.response.OptionTypeResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CarSourceTypeActivity extends BaseActivity {


    private static final String TAG = "CarSourceTypeActivity";
    private String token;
    @BindView(R.id.recycler_car_type)
    RecyclerView mRecycler;
    private List<ItemData> optionTypes = new ArrayList<>();
    private BaseAdapter baseAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_car_source_type;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        baseAdapter = new BaseAdapter(optionTypes, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {

                OptionTypeModel model = (OptionTypeModel) data;
//                                    tvOptionsType.setText(model.getOptionType());
                Intent data1 = new Intent();
                data1.putExtra("carTypeName", model.getOptionType());
                data1.putExtra("carTypeId", model.getOptionId());
                setResult(RESULT_OK, data1);
                finish();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        mRecycler.setAdapter(baseAdapter);
    }

    @Override
    public void doBusiness(Context mContext) {
        getOptionType();
    }

    @SuppressLint("CheckResult")
    private void getOptionType() {
        Injection.provideApiService().getOptionTypes(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OptionTypeResponse>() {
                    @Override
                    public void accept(OptionTypeResponse response) throws Exception {
                        //获取车源类型
                        try {
                            for (int i = 0; i < response.getData().size(); i++) {
                                OptionTypeModel typeModel = new OptionTypeModel(response.getData().get(i).getCarTypeId(), response.getData().get(i).getTypeName());
                                ItemData itemData = new ItemData(0, SettingDelegate.OPTION_TYPE, typeModel);
                                optionTypes.add(itemData);
                            }
                            baseAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Log.e(TAG, "accept: -----------------------------");
                        }

                    }
                });
    }
}
