package com.example.com.careasysell.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.options.contract.ICarSell;
import com.example.com.careasysell.options.model.AddressModel;
import com.example.com.careasysell.options.model.response.CarBrandResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.utils.NotifyCallBackManager;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.SP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/7/26.
 */
public class ChooseBrandActivity extends BaseActivity implements MyAdapter.SelectBrandListener {

    @BindView(R.id.list)
    ListView listView;
    private List<AddressModel> carBrands = new ArrayList<>();
    private String token , carType;

    @Override
    public int bindLayout() {
        return R.layout.activity_choose_brand;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB,this).getString(C.USER_TOKEN);
        carType = SP.getInstance(C.USER_DB,this).getString(C.USER_TYPE);
        carBrands = new ArrayList<>();
        carBrands.add(new AddressModel("保时捷"));
        carBrands.add(new AddressModel("奥迪"));
        carBrands.add(new AddressModel("福特"));
        carBrands.add(new AddressModel("兰博基尼"));
        carBrands.add(new AddressModel("阿斯顿"));
        carBrands.add(new AddressModel("奔驰"));
        carBrands.add(new AddressModel("五菱宏光"));
        carBrands.add(new AddressModel("哈弗"));
        carBrands.add(new AddressModel("凯迪拉克"));

        //对集合排序
        Collections.sort(carBrands, new Comparator<AddressModel>() {
            @Override
            public int compare(AddressModel lhs, AddressModel rhs) {
                //根据拼音进行排序
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context mContext) {
        Injection.provideApiService().getCarBrand(token,carType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarBrandResponse>() {
                    @Override
                    public void accept(CarBrandResponse salesAreaResponse) throws Exception {
                        if(salesAreaResponse.getCode() == 200){

                        }
                    }
                });

        MyAdapter adapter = new MyAdapter(this, carBrands);
        adapter.setOnSelectBrandListener(this);
        listView.setAdapter(adapter);
        NotifyCallBackManager.getInstance().registPagerCloseCallBack(new ICarSell.IPagerClose() {
            @Override
            public void close() {
                finish();
            }
        });
    }


    @Override
    public void selectBrand(String carBrand) {
        Bundle bundle = new Bundle();
        bundle.putString("carBrand", carBrand);
        startActivity(ChooseCarsActivity.class, bundle);
    }
}
