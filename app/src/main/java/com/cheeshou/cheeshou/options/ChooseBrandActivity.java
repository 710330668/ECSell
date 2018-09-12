package com.cheeshou.cheeshou.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerWantCarModel;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.options.contract.ICarSell;
import com.cheeshou.cheeshou.options.model.AddressModel;
import com.cheeshou.cheeshou.options.model.response.CarBrandResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.utils.NotifyCallBackManager;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.SP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/7/26.
 */
public class ChooseBrandActivity extends BaseActivity implements MyAdapter.SelectBrandListener {

    @BindView(R.id.list)
    ListView listView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private List<AddressModel> carBrands = new ArrayList<>();
    private String token;
    private String optionId;
    private String paramsString = "";

    @Override
    public int bindLayout() {
        return R.layout.activity_choose_brand;
    }

    @Override
    public void initParams(Bundle params) {
        optionId = getIntent().getStringExtra("optionId");
        paramsString = params.getString("params");
        if (paramsString == "filter") {
            optionId = params.getString("optionId");
        }
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        carBrands = new ArrayList<>();
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context mContext) {
        final MyAdapter adapter = new MyAdapter(ChooseBrandActivity.this, carBrands);
        adapter.setOnSelectBrandListener(ChooseBrandActivity.this);
        listView.setAdapter(adapter);
        carBrands.clear();
        Injection.provideApiService().getCarBrand(token, optionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarBrandResponse>() {
                    @Override
                    public void accept(CarBrandResponse salesAreaResponse) throws Exception {
                        if (salesAreaResponse.getCode() == 200) {
                            for (int i = 0; i < salesAreaResponse.getData().size(); i++) {
                                carBrands.add(new AddressModel(salesAreaResponse.getData().get(i).getBrand(), salesAreaResponse.getData().get(i).getId()));
                                //对集合排序
                                Collections.sort(carBrands, new Comparator<AddressModel>() {
                                    @Override
                                    public int compare(AddressModel lhs, AddressModel rhs) {
                                        //根据拼音进行排序
                                        return lhs.getPinyin().compareTo(rhs.getPinyin());
                                    }
                                });
                            }
                            adapter.notifyDataSetChanged();

                        }else if(salesAreaResponse.getCode() == 402||salesAreaResponse.getCode() == 401){
                            //token失效
                            SP.getInstance(C.USER_DB,ChooseBrandActivity.this).put(C.USER_ACCOUNT,"");
                            SP.getInstance(C.USER_DB,ChooseBrandActivity.this).put(C.USER_PASSWORD,"");
                            finishAllActivity();
                            startActivity(LoginActivity.class);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SP.getInstance(C.USER_DB,ChooseBrandActivity.this).put(C.USER_ACCOUNT,"");
                        SP.getInstance(C.USER_DB,ChooseBrandActivity.this).put(C.USER_PASSWORD,"");
                        finishAllActivity();
                        startActivity(LoginActivity.class);
                    }
                });

        NotifyCallBackManager.getInstance().registPagerCloseCallBack(new ICarSell.IPagerClose() {
            @Override
            public void close() {
                finish();
            }
        });
    }


    @Override
    public void selectBrand(String carBrand, String id) {
        Bundle bundle = new Bundle();
        bundle.putString("carBrand", carBrand);
        bundle.putString("brandId", id);
        if (!TextUtils.isEmpty(paramsString)) {
            if (paramsString.equals("filter")) {
                Intent intent = new Intent();
                intent.putExtra("carBrand", carBrand);
                intent.putExtra("brandId", id);
                this.setResult(RESULT_OK, intent);
                finish();
            }
        } else {
            CustomerWantCarModel model = ParamManager.getInstance(this).getModel();
            CustomerWantCarModel.CodeBean code = model.getCode();
            code.setBrandId(id);
            model.setCode(code);
            ParamManager.getInstance(this).setModel(model);
            startActivity(ChooseCarsActivity.class, bundle);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
