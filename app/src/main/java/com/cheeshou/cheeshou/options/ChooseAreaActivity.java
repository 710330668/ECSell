package com.cheeshou.cheeshou.options;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cheeshou.cheeshou.MyApplication;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.options.model.AddressModel;
import com.cheeshou.cheeshou.options.model.AreasModel;
import com.cheeshou.cheeshou.options.model.response.AreaProvinceResponse;
import com.cheeshou.cheeshou.options.model.response.RegisonNameResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;

import java.io.IOException;
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
    @BindView(R.id.tv_loaction)
    TextView tvLoaction;

    private List<AddressModel> areas = new ArrayList<>();
    private List<ItemData> areaLists = new ArrayList<>();
    private String token;
    private BaseAdapter baseAdapter;
    private String areaId;
    private Location location;
    private Geocoder geocoder;
    private static final int REQUEST_PERMISSION_LOCATION = 255;
    private String provinceCode,cityCode,cityName;
    private AreasModel areasModel;
    private ItemData itemData;

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
        rlArea.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_LOCATION);
        } else {
            // We have already permission to use the location
            getLocation(this);
        }

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
                            areasModel = new AreasModel("全省",  "");
                            itemData = new ItemData(0, SettingDelegate.AREAS_TYPE, areasModel);
                            areaLists.add(itemData);
                            for (int i = 0; i < response.getData().size(); i++) {
                                areasModel = new AreasModel(response.getData().get(i).getCityName(), response.getData().get(i).getId() + "");
                                itemData = new ItemData(0, SettingDelegate.AREAS_TYPE, areasModel);
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

    @OnClick({R.id.iv_back, R.id.iv_location,R.id.tv_loaction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_location:
                break;
            case R.id.tv_loaction:
                Intent intent = new Intent();
                intent.putExtra("area", cityName);
                intent.putExtra("provinceCode", provinceCode);
                intent.putExtra("cityCode", cityCode);
                setResult(RESULT_OK, intent);
                finish();
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

    @SuppressLint("MissingPermission")
    private Location getLocation(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        //若GPS未开启
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(ChooseAreaActivity.this, "请开启GPS！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            ChooseAreaActivity.this.startActivityForResult(intent, 0); //此为设置完成后返回到获取界面
        }
        //获得GPS支持
        location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.v("location1", location + "");
        if (location == null) {
            //获得PASSIVE支持
            location = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            Log.v("location2", location + "");
        } else {
            //获得NETWORK支持
            location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Log.v("location3", location + "");

        }

        geocoder = new Geocoder(ChooseAreaActivity.this);
        try {
            List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
            Log.v("location", location.getLatitude() + "," + location.getLongitude());
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Address address = list.get(i);
                    String add = "";
                    int maxLine = address.getMaxAddressLineIndex();
                    Log.v("maxline", maxLine + "");
//                    if (maxLine >= 2) {
//                        add = address.getAddressLine(1) + address.getAddressLine(2);
//                    } else {
//                        add = address.getAddressLine(1);
//                    }
                    tvLoaction.setText(address.getLocality());
                    getCityInfor(address.getLocality());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;

    }

    @SuppressLint("CheckResult")
    private void getCityInfor(String locality) {
        cityName = locality;
        Injection.provideApiService().findRegionByName(token,locality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisonNameResponse>() {
                    @Override
                    public void accept(RegisonNameResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if(response.getCode() == 200){
                            provinceCode = response.getData().getPid()+"";
                            cityCode = response.getData().getId()+"";
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We now have permission to use the location
                getLocation(ChooseAreaActivity.this);
            }
        }
    }


}
