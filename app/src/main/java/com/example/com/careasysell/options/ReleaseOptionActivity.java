package com.example.com.careasysell.options;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.options.model.CarPhotoModel;
import com.example.com.careasysell.options.model.ColorModel;
import com.example.com.careasysell.options.model.FormalityModel;
import com.example.com.careasysell.options.model.OptionTypeModel;
import com.example.com.careasysell.options.model.SalesAreaModel;
import com.example.com.careasysell.options.model.response.CommonResponse;
import com.example.com.careasysell.options.model.response.OptionTypeResponse;
import com.example.com.careasysell.options.model.response.SalesAreaResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
public class ReleaseOptionActivity extends BaseActivity {

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mainDrawerLayout;
    @BindView(R.id.rl_option_type)
    RecyclerView rlOptionType;
    @BindView(R.id.main_right_drawer_layout)
    LinearLayout mainRightDrawerLayout;
    @BindView(R.id.tv_options_type)
    TextView tvOptionsType;
    @BindView(R.id.tv_drawer_title)
    TextView tvDrawerTitle;
    @BindView(R.id.tv_apprence_color)
    TextView tvApprenceColor;
    @BindView(R.id.tv_interior_color)
    TextView tvInteriorColor;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_sales_area)
    TextView tvSalesArea;
    @BindView(R.id.tv_formalities)
    TextView tvFormalities;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_car_model)
    TextView tvCarModel;
    @BindView(R.id.iv_local)
    ImageView ivLocal;
    @BindView(R.id.lly_youhui)
    LinearLayout llyYouhui;
    @BindView(R.id.rl_car_photo)
    RecyclerView rlCarPhoto;

    private List<ItemData> optionTypes = new ArrayList<>();
    private List<ItemData> apprenceColorTypes = new ArrayList<>();
    private List<ItemData> interiorColorTypes = new ArrayList<>();
    private List<ItemData> salesAreaTypes = new ArrayList<>();
    private List<ItemData> formalityTypes = new ArrayList<>();
    private List<ItemData> carPhotos = new ArrayList<>();
    private List<String> preferential = new ArrayList<>();
    private List<Boolean> flags = new ArrayList<>();
    private List<Bitmap> photos = new ArrayList<>();
    private final int REQUEST_BRAND = 0;
    private final int REQUEST_AREA = 1;
    private final int REQUEST_LOCAL = 2;
    private boolean flag = true;
    private String token;
    private String optionId;

    @Override
    public int bindLayout() {
        return R.layout.activity_release_options;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context mContext) {
        //内饰颜色
        getInteriorColor();
        //外观颜色
        getAppearanceColor();
        //车源类型
        getOptionType();
        //手续类型
        getFormalityTypes();
        //销售区域
        getSalesAreaTypes();
        //优惠政策
        getPreferential();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlOptionType.setLayoutManager(layoutManager);
        rlOptionType.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @SuppressLint("CheckResult")
    private void getPreferential() {
        llyYouhui.removeAllViews();
        Injection.provideApiService().getCarDiscountList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonResponse>() {
                    @Override
                    public void accept(CommonResponse response) throws Exception {
                        //获取优惠政策类型
                        try {
                            if (response.getCode() == 200) {
                                for (int i = 0; i < response.getData().size(); i++) {
                                    preferential.add(response.getData().get(i).getDataName());
                                    flags.add(true);
                                    final Button button = new Button(ReleaseOptionActivity.this);
                                    button.setText(response.getData().get(i).getDataName());
                                    button.setBackgroundResource(R.drawable.bg_edittext);
                                    button.setPadding(10, 10, 10, 10);
                                    button.setGravity(Gravity.CENTER);
                                    llyYouhui.addView(button);
                                    LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) button.getLayoutParams();
                                    linearParams.setMargins(20, 20, 10, 10);
                                    linearParams.height = 120;
                                    linearParams.width = 240;
                                    button.setLayoutParams(linearParams);
                                    final int finalI = i;
                                    final int finalI1 = i;
                                    final int finalI2 = i;
                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (flags.get(finalI)) {
                                                changeTextColor(button, R.color.color_FF5754, R.drawable.bg_edittext_red);
                                            } else {
                                                changeTextColor(button, R.color.color_333333, R.drawable.bg_edittext);
                                            }
                                            flags.set(finalI2, !flags.get(finalI));
                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {

                        }

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getSalesAreaTypes() {
        Injection.provideApiService().getSalesAreaTypes(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SalesAreaResponse>() {
                    @Override
                    public void accept(SalesAreaResponse response) throws Exception {
                        //获取销售区域类型
                        try {
                            for (int i = 0; i < response.getData().size(); i++) {
                                SalesAreaModel salesAreaModel = new SalesAreaModel(response.getData().get(i).getAreaId(), response.getData().get(i).getAreaName());
                                ItemData itemData = new ItemData(0, SettingDelegate.SALES_AREA_TYPE, salesAreaModel);
                                salesAreaTypes.add(itemData);
                            }
                        } catch (Exception e) {

                        }

                    }
                });
    }


    @SuppressLint("CheckResult")
    private void getFormalityTypes() {
        Injection.provideApiService().getFormalityTypes(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonResponse>() {
                    @Override
                    public void accept(CommonResponse response) throws Exception {
                        //获取车源类型
                        try {
                            for (int i = 0; i < response.getData().size(); i++) {
                                FormalityModel formalityModel = new FormalityModel(response.getData().get(i).getDataName());
                                ItemData itemData = new ItemData(0, SettingDelegate.FORMALITY_TYPE, formalityModel);
                                formalityTypes.add(itemData);
                            }
                        } catch (Exception e) {

                        }

                    }
                });
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
                        } catch (Exception e) {

                        }

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getAppearanceColor() {
        Injection.provideApiService().getAppearanceColor(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonResponse>() {
                    @Override
                    public void accept(CommonResponse response) throws Exception {
                        //获取外观颜色
                        try {
                            if (response.getCode() == 200) {
                                for (int i = 0; i < response.getData().size(); i++) {
                                    ColorModel colorModel = new ColorModel(response.getData().get(i).getDataName());
                                    ItemData itemData = new ItemData(0, SettingDelegate.COLOR_TYPE, colorModel);
                                    apprenceColorTypes.add(itemData);
                                }
                            }
                        } catch (Exception e) {

                        }

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getInteriorColor() {
        //内饰颜色
        Injection.provideApiService().getInteriorColor(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonResponse>() {
                    @Override
                    public void accept(CommonResponse response) throws Exception {
                        //获取内饰颜色
                        try {
                            if (response.getCode() == 200) {
                                for (int i = 0; i < response.getData().size(); i++) {
                                    ColorModel colorModel = new ColorModel(response.getData().get(i).getDataName());
                                    ItemData itemData = new ItemData(0, SettingDelegate.COLOR_TYPE, colorModel);
                                    interiorColorTypes.add(itemData);
                                }
                            }
                        } catch (Exception e) {

                        }

                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.iv_options_type, R.id.iv_car_model, R.id.iv_appearance_color,
            R.id.iv_interior_color, R.id.iv_area, R.id.iv_sales_area, R.id.iv_formalities, R.id.iv_year,
            R.id.lly_local_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_options_type:
                openRightLayout();
                showOptionsTypeList();
                break;
            case R.id.iv_car_model:
                Intent intent = new Intent(ReleaseOptionActivity.this, ChooseBrandActivity.class);
                intent.putExtra("optionId",optionId);
                startActivityForResult(intent, REQUEST_BRAND);
                break;
            case R.id.iv_appearance_color:
                openRightLayout();
                showApprenceColorList();
                break;
            case R.id.iv_interior_color:
                openRightLayout();
                showInteriorColorList();
                break;
            case R.id.iv_area:
                startActivityForResult(new Intent(ReleaseOptionActivity.this, ChooseAreaActivity.class), REQUEST_AREA);
                break;
            case R.id.iv_sales_area:
                openRightLayout();
                showSalesAreaList();
                break;
            case R.id.iv_formalities:
                openRightLayout();
                showFormalityList();
                break;
            case R.id.iv_year:
                break;
            case R.id.lly_local_image:
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent1, REQUEST_LOCAL);
                break;

        }
    }

    private void changeTextColor(Button view, int color, int bg) {
        view.setTextColor(this.getResources().getColor(color));
        view.setBackgroundResource(bg);
    }

    private void showFormalityList() {
        tvDrawerTitle.setText("选择手续");
        BaseAdapter baseAdapter = new BaseAdapter(formalityTypes, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
                FormalityModel model = (FormalityModel) data;
                tvFormalities.setText(model.getFormalityName());
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlOptionType.setAdapter(baseAdapter);
    }

    private void showSalesAreaList() {
        tvDrawerTitle.setText("选择销售区域");
        BaseAdapter baseAdapter = new BaseAdapter(salesAreaTypes, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
                SalesAreaModel model = (SalesAreaModel) data;
                tvSalesArea.setText(model.getSalesAreaName());
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlOptionType.setAdapter(baseAdapter);
    }

    private void showInteriorColorList() {
        tvDrawerTitle.setText("内饰颜色");
        BaseAdapter baseAdapter = new BaseAdapter(interiorColorTypes, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
                ColorModel model = (ColorModel) data;
                tvInteriorColor.setText(model.getApprearceColor());
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlOptionType.setAdapter(baseAdapter);
    }

    private void showApprenceColorList() {
        tvDrawerTitle.setText("外观颜色");
        BaseAdapter baseAdapter = new BaseAdapter(apprenceColorTypes, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
                ColorModel model = (ColorModel) data;
                tvApprenceColor.setText(model.getApprearceColor());
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlOptionType.setAdapter(baseAdapter);
    }

    private void showOptionsTypeList() {
        tvDrawerTitle.setText("车源类型");
        BaseAdapter baseAdapter = new BaseAdapter(optionTypes, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
                OptionTypeModel model = (OptionTypeModel) data;
                tvOptionsType.setText(model.getOptionType());
                optionId = model.getOptionId();
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });
        rlOptionType.setAdapter(baseAdapter);
    }

    private void openRightLayout() {
        if (mainDrawerLayout.isDrawerOpen(mainRightDrawerLayout)) {
            mainDrawerLayout.closeDrawer(mainRightDrawerLayout);
        } else {
            mainDrawerLayout.openDrawer(mainRightDrawerLayout);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCarModel.setText(ParamManager.getInstance(this).getCarFullName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        carPhotos.clear();
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_AREA) {
                tvArea.setText(data.getStringExtra("area"));
            } else if (requestCode == REQUEST_LOCAL) {
                Uri uri = data.getData();
                String img_url = uri.getPath();
                ContentResolver cr = this.getContentResolver();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    //最多九张
                    photos.add(bitmap);
                    for(int i =0;i<photos.size();i++){
                        CarPhotoModel carPhotoModel = new CarPhotoModel(photos.get(i));
                        ItemData itemData = new ItemData(0, SettingDelegate.CAR_PHOTO_TYPE, carPhotoModel);
                        carPhotos.add(itemData);
                        rlCarPhoto.setLayoutManager(new GridLayoutManager(this,3));
                        BaseAdapter baseAdapter = new BaseAdapter(carPhotos, new SettingDelegate(), new onItemClickListener() {
                            @Override
                            public void onClick(View v, Object data) {
                            }

                            @Override
                            public boolean onLongClick(View v, Object data) {
                                return false;
                            }
                        });
                        rlCarPhoto.setAdapter(baseAdapter);
                    }
                } catch (FileNotFoundException e) {
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
