package com.example.com.careasysell.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.options.model.AddressModel;
import com.example.com.careasysell.options.model.AreasModel;
import com.example.com.careasysell.options.model.response.AreaProvinceResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.LogUtils;
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
public class ChooseAreaActivity extends BaseActivity implements MyAdapter.SelectBrandListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.list)
    ListView listView;
    @BindView(R.id.tv_area_name)
    TextView tvAreaName;
    @BindView(R.id.rl_area)
    RecyclerView rlArea;
    @BindView(R.id.main_right_drawer_layout)
    LinearLayout mainRightDrawerLayout;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mainDrawerLayout;

    private List<AddressModel> areas = new ArrayList<>();
    private List<ItemData> areaLists = new ArrayList<>();
    private String token;
    private BaseAdapter baseAdapter;
    private String areaId;

    @Override
    public int bindLayout() {
        return R.layout.activity_choose_area;
    }

    @Override
    public void initParams(Bundle params) {

        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);

        areas = new ArrayList<>();

    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        getRegionList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlArea.setLayoutManager(layoutManager);
        baseAdapter = new BaseAdapter(areaLists, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                String areaName = tvAreaName.getText().toString();
                AreasModel model = (AreasModel) data;
                Intent intent = new Intent();
                intent.putExtra("area",areaName+""+model.getAreasName());
                intent.putExtra("provinceCode",areaId);
                intent.putExtra("cityCode",model.getAreasId());
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlArea.setAdapter(baseAdapter);
    }

    //车源所在地
    @SuppressLint("CheckResult")
    private void getRegionList() {
        Injection.provideApiService().getRegionList(token,"0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AreaProvinceResponse>() {
                    @Override
                    public void accept(AreaProvinceResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            for (int i = 0; i < response.getData().size(); i++) {
                                areas.add(new AddressModel(response.getData().get(i).getCityName(),response.getData().get(i).getId()));
                            }
                            //对集合排序
                            Collections.sort(areas, new Comparator<AddressModel>() {
                                @Override
                                public int compare(AddressModel lhs, AddressModel rhs) {
                                    //根据拼音进行排序
                                    return lhs.getPinyin().compareTo(rhs.getPinyin());
                                }
                            });

                            MyAdapter adapter = new MyAdapter(ChooseAreaActivity.this, areas);
                            adapter.setOnSelectBrandListener(ChooseAreaActivity.this);
                            listView.setAdapter(adapter);
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getCity(String areaId) {
        Injection.provideApiService().getRegionList(token,areaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AreaProvinceResponse>() {
                    @Override
                    public void accept(AreaProvinceResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            for (int i =0;i<response.getData().size();i++) {
                                AreasModel areasModel = new AreasModel(response.getData().get(i).getCityName(),response.getData().get(i).getId()+"");
                                ItemData itemData = new ItemData(0, SettingDelegate.AREAS_TYPE, areasModel);
                                areaLists.add(itemData);
                            }
                        }
                        baseAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.iv_location:
                break;
        }
    }

    @Override
    public void selectBrand(String areaBrand,String id) {
        areaId = id+"";
        getCity(areaId);
        tvAreaName.setText(areaBrand);
        openRightLayout();
    }

    private void openRightLayout() {
        if (mainDrawerLayout.isDrawerOpen(mainRightDrawerLayout)) {
            mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
        } else {
            mainDrawerLayout.openDrawer(mainRightDrawerLayout);
        }
    }
}
