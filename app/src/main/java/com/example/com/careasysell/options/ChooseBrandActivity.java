package com.example.com.careasysell.options;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.model.AddressModel;
import com.example.com.common.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 71033 on 2018/7/26.
 */
public class ChooseBrandActivity extends BaseActivity implements MyAdapter.SelectBrandListener {

    @BindView(R.id.list)
    ListView listView;
    private List<AddressModel> carBrands = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_choose_brand;
    }

    @Override
    public void initParams(Bundle params) {
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

    @Override
    public void doBusiness(Context mContext) {
        MyAdapter adapter = new MyAdapter(this, carBrands);
        adapter.setOnSelectBrandListener(this);
        listView.setAdapter(adapter);
    }


    @Override
    public void selectBrand(String carBrand) {
        Bundle bundle = new Bundle();
        bundle.putString("carBrand", carBrand);
        startActivity(ChooseCarsActivity.class, bundle);
    }
}
