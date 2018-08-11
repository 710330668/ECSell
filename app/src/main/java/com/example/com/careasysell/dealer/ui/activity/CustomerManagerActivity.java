package com.example.com.careasysell.dealer.ui.activity;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.dealer.ui.model.CustomerModel;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomerManagerActivity extends BaseActivity {

    private PopupWindow mPopupWindow;

    private static final String TAG = "CustomerManagerActivity";

    @BindView(R.id.rb_customer_state)
    RadioButton mRbState;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.rb_follow_time)
    RadioButton mRbTime;
    @BindView(R.id.drawer_root)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.tag_create_time)
    TagFlowLayout mCreateTimeTag;
    @BindView(R.id.tag_follow_time)
    TagFlowLayout mFollowTimeTag;
    @BindView(R.id.recycler_customer_manager)
    RecyclerView mCustomerRecycler;

    private int selectState = 0;
    private int selectTime = 0;

    private List<ItemData> dataList = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_customer_manager;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mCustomerRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCustomerRecycler.addItemDecoration(new SpaceItemDecoration(10));
    }

    @Override
    public void doBusiness(Context mContext) {
        initTagFlowLayout();
        initRecycler();
    }

    private void initRecycler() {
        for (int i = 0; i < 10; i++) {
            CustomerModel data = new CustomerModel();
            data.setName("张达");
            data.setFollowTime("08/01 客户考虑中");
            data.setNeed("预算16-20万 | 标致-标致301 等5款车");
            data.setMessage("2018/07/12 创建 | 销售 王硕");
            data.setState(i % 2);
            dataList.add(new ItemData(0, SettingDelegate.CUSTOMER_MANAGER_TYPE, data));
        }
        dataList.add(new ItemData(0, SettingDelegate.FOOT_TYPE));
        BaseAdapter adapter = new BaseAdapter(dataList, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {

            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });

        mCustomerRecycler.setAdapter(adapter);
    }

    @OnClick({R.id.img_back, R.id.img_add_client, R.id.rb_customer_state, R.id.rb_follow_time, R.id.rb_customer_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.img_add_client:
                // TODO: 2018/8/7 添加客户
                startActivity(CreateNewCustomerActivity.class);
                break;
            case R.id.rb_customer_state:
                // TODO: 2018/8/7 showPop
                showPopUpWindow(R.id.rb_customer_state);
                break;
            case R.id.rb_follow_time:
                // TODO: 2018/8/7 showPop
                showPopUpWindow(R.id.rb_follow_time);
                break;
            case R.id.rb_customer_filter:
                // TODO: 2018/8/7 showDrawer
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                break;
            default:
        }
    }


    public void showPopUpWindow(int id) {

        TagFlowLayout convertView = null;
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        switch (id) {
            case R.id.rb_customer_state:
                convertView = (TagFlowLayout) LayoutInflater.from(this).inflate(R.layout.layout_popup_customer_state, null);
                final List<String> dataSize = new ArrayList<>();
                dataSize.add("全部");
                dataSize.add("未到店");
                dataSize.add("已到店");
                dataSize.add("已预定");
                dataSize.add("已成交");
                dataSize.add("战败");
                final TagFlowLayout finalConvertView = convertView;
                TagAdapter<String> stateAdapter = new TagAdapter<String>(dataSize) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.tv_tag_customer_state, finalConvertView, false);
//                        textView.getLayoutParams().width = getWindowManager().getDefaultDisplay().getWidth() / 5;
                        textView.setText(o);
                        return textView;
                    }
                };
                stateAdapter.setSelectedList(selectState);
                convertView.setAdapter(stateAdapter);
                mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.showAsDropDown(viewLine);
                convertView.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        mRbState.setText(dataSize.get(position));
                        selectState = position;
                        mPopupWindow.dismiss();
                        // TODO: 2018/8/11 刷新数据
                        return true;
                    }
                });
                break;
            case R.id.rb_follow_time:
                final RadioGroup content = (RadioGroup) LayoutInflater.from(this).inflate(R.layout.layout_customer_time_popou, null);
                ((RadioButton) content.getChildAt(selectTime)).setChecked(true);
                content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton selectButton = ((RadioButton) content.findViewById(checkedId));
                        mRbTime.setText(selectButton.getText());
                        selectTime = content.indexOfChild(selectButton);
                        mPopupWindow.dismiss();
                        // TODO: 2018/8/11 刷新数据
                    }
                });
                mPopupWindow = new PopupWindow(content, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.showAsDropDown(viewLine);
                break;
        }

    }

    public void initTagFlowLayout() {
        List<String> dataSize = new ArrayList<>();
        dataSize.add("全部");
        dataSize.add("昨天");
        dataSize.add("今天");
        dataSize.add("本周");
        dataSize.add("本月");
        TagAdapter<String> createTimeAdapter = new TagAdapter<String>(dataSize) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_tag_create_time, mCreateTimeTag, false);
//                        textView.getLayoutParams().width = getWindowManager().getDefaultDisplay().getWidth() / 5;
                textView.setText(o);
                return textView;
            }
        };
        mCreateTimeTag.setAdapter(createTimeAdapter);

        TagAdapter<String> followTimeAdapter = new TagAdapter<String>(dataSize) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.item_tag_create_time, mFollowTimeTag, false);
//                        textView.getLayoutParams().width = getWindowManager().getDefaultDisplay().getWidth() / 5;
                textView.setText(o);
                return textView;
            }
        };
        mFollowTimeTag.setAdapter(followTimeAdapter);
    }
}
