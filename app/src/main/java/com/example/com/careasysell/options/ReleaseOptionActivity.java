package com.example.com.careasysell.options;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.model.ColorModel;
import com.example.com.careasysell.options.model.OptionTypeModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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

    private List<ItemData> optionTypes = new ArrayList<>();
    private List<ItemData> apprenceColorTypes = new ArrayList<>();
    private List<ItemData> interiorColorTypes = new ArrayList<>();
    private final int REQUEST_BRAND = 0;

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

    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlOptionType.setLayoutManager(layoutManager);
    }

    @OnClick({R.id.iv_back, R.id.iv_options_type, R.id.iv_car_model, R.id.iv_appearance_color, R.id.iv_interior_color, R.id.iv_area})
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
                Intent intent = new Intent(ReleaseOptionActivity.this,ChooseBrandActivity.class);
                startActivityForResult(intent,REQUEST_BRAND);
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
                break;
        }
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

}
