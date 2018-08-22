package com.example.com.careasysell.options;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                intent.putExtra("area", areaName + "" + model.getAreasName());
                intent.putExtra("provinceCode", areaId);
                intent.putExtra("cityCode", model.getAreasId());
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlArea.setAdapter(baseAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getLocation();
    }


    //车源所在地
    @SuppressLint("CheckResult")
    private void getRegionList() {
        Injection.provideApiService().getRegionList(token, "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AreaProvinceResponse>() {
                    @Override
                    public void accept(AreaProvinceResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            for (int i = 0; i < response.getData().size(); i++) {
                                areas.add(new AddressModel(response.getData().get(i).getCityName(), response.getData().get(i).getId()));
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
        Injection.provideApiService().getRegionList(token, areaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AreaProvinceResponse>() {
                    @Override
                    public void accept(AreaProvinceResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            for (int i = 0; i < response.getData().size(); i++) {
                                AreasModel areasModel = new AreasModel(response.getData().get(i).getCityName(), response.getData().get(i).getId() + "");
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
    public void selectBrand(String areaBrand, String id) {
        areaId = id + "";
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

    public void getLocation() {
        String locationProvider;
        //获取地理位置管理器
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取Location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            //不为空,显示地理位置经纬度
            showLocation(location);
        }
        //监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
    }


    private void showLocation(Location location) {
        String locationStr = "纬度：" + location.getLatitude() + "\n"
                + "经度：" + location.getLongitude();
//        updateVersion(location.getLatitude() + "", location.getLongitude() + "");
    }



    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            showLocation(location);
        }
    };



}
