package com.example.com.careasysell.options;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.model.ColorModel;
import com.example.com.careasysell.options.model.FormalityModel;
import com.example.com.careasysell.options.model.OptionTypeModel;
import com.example.com.careasysell.options.model.SalesAreaModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.btn_baoxian)
    Button btnBaoxian;
    @BindView(R.id.btn_zhihuan)
    Button btnZhihuan;
    @BindView(R.id.btn_daikuan)
    Button btnDaikuan;
    @BindView(R.id.btn_baoyang)
    Button btnBaoyang;
    @BindView(R.id.tv_car_model)
    TextView tvCarModel;
    @BindView(R.id.iv_local)
    ImageView ivLocal;

    private List<ItemData> optionTypes = new ArrayList<>();
    private List<ItemData> apprenceColorTypes = new ArrayList<>();
    private List<ItemData> interiorColorTypes = new ArrayList<>();
    private List<ItemData> salesAreaTypes = new ArrayList<>();
    private List<ItemData> formalityTypes = new ArrayList<>();
    private final int REQUEST_BRAND = 0;
    private final int REQUEST_AREA = 1;
    private final int REQUEST_LOCAL = 2;
    private boolean baoxianFlag = true, zhihuanFlag = true, daikuanFlag = true, baoyangFlag = true;

    @Override
    public int bindLayout() {
        return R.layout.activity_release_options;
    }

    @Override
    public void initParams(Bundle params) {
        for (int i = 0; i < 5; i++) {
            OptionTypeModel typeModel = new OptionTypeModel("国产-现车");
            ItemData itemData = new ItemData(0, SettingDelegate.OPTION_TYPE, typeModel);
            optionTypes.add(itemData);
        }

        for (int i = 0; i < 5; i++) {
            ColorModel colorModel = new ColorModel("红");
            ItemData itemData = new ItemData(0, SettingDelegate.COLOR_TYPE, colorModel);
            apprenceColorTypes.add(itemData);
        }

        for (int i = 0; i < 5; i++) {
            ColorModel colorModel = new ColorModel("红");
            ItemData itemData = new ItemData(0, SettingDelegate.COLOR_TYPE, colorModel);
            interiorColorTypes.add(itemData);
        }

        for (int i = 0; i < 5; i++) {
            SalesAreaModel salesAreaModel = new SalesAreaModel("全省");
            ItemData itemData = new ItemData(0, SettingDelegate.SALES_AREA_TYPE, salesAreaModel);
            salesAreaTypes.add(itemData);
        }

        for (int i = 0; i < 5; i++) {
            FormalityModel formalityModel = new FormalityModel("手续齐全");
            ItemData itemData = new ItemData(0, SettingDelegate.FORMALITY_TYPE, formalityModel);
            formalityTypes.add(itemData);
        }

    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlOptionType.setLayoutManager(layoutManager);
        rlOptionType.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @OnClick({R.id.iv_back, R.id.iv_options_type, R.id.iv_car_model, R.id.iv_appearance_color,
            R.id.iv_interior_color, R.id.iv_area, R.id.iv_sales_area, R.id.iv_formalities, R.id.iv_year,
            R.id.btn_baoxian, R.id.btn_zhihuan, R.id.btn_daikuan, R.id.btn_baoyang,R.id.lly_local_image})
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
            case R.id.btn_baoxian:
                if (baoxianFlag) {
                    changeTextColor(btnBaoxian, R.color.color_FF5754, R.drawable.bg_edittext_red);
                } else {
                    changeTextColor(btnBaoxian, R.color.color_333333, R.drawable.bg_edittext);
                }
                baoxianFlag = !baoxianFlag;
                break;
            case R.id.btn_zhihuan:
                if (zhihuanFlag) {
                    changeTextColor(btnZhihuan, R.color.color_FF5754, R.drawable.bg_edittext_red);
                } else {
                    changeTextColor(btnZhihuan, R.color.color_333333, R.drawable.bg_edittext);
                }
                zhihuanFlag = !zhihuanFlag;
                break;
            case R.id.btn_daikuan:
                if (daikuanFlag) {
                    changeTextColor(btnDaikuan, R.color.color_FF5754, R.drawable.bg_edittext_red);
                } else {
                    changeTextColor(btnDaikuan, R.color.color_333333, R.drawable.bg_edittext);
                }
                daikuanFlag = !daikuanFlag;
                break;
            case R.id.btn_baoyang:
                if (baoyangFlag) {
                    changeTextColor(btnBaoyang, R.color.color_FF5754, R.drawable.bg_edittext_red);
                } else {
                    changeTextColor(btnBaoyang, R.color.color_333333, R.drawable.bg_edittext);
                }
                baoyangFlag = !baoyangFlag;
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
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_AREA) {
                tvArea.setText(data.getStringExtra("area"));
            }else if(requestCode == REQUEST_LOCAL){
                Uri uri = data.getData();
                String img_url = uri.getPath();
                ContentResolver cr = this.getContentResolver();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    ivLocal.setImageBitmap(bitmap);
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
