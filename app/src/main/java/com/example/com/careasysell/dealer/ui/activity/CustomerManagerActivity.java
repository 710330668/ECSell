package com.example.com.careasysell.dealer.ui.activity;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.dealer.ui.model.CustomerModel;
import com.example.com.careasysell.dealer.ui.model.response.CustomerResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.remote.SettingDelegate;
import com.example.com.careasysell.utils.EndlessRecyclerOnScrollListener;
import com.example.com.careasysell.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    @BindView(R.id.et_min_money)
    EditText mEtMinMoney;
    @BindView(R.id.et_max_money)
    EditText mEtMaxMoney;
    @BindView(R.id.et_create_start_time)
    EditText mEtCreateStartTime;
    @BindView(R.id.et_create_end_time)
    EditText mEtCreateEndTime;
    @BindView(R.id.et_follow_start_time)
    EditText mEtFollowStartTime;
    @BindView(R.id.et_follow_end_time)
    EditText mEtFollowEndTime;
    @BindView(R.id.btn_reset)
    Button mBtnReset;
    @BindView(R.id.btn_sure)
    Button mBtnSure;
    private int count;
    private String TAG_FILTER = "tag_filter";
    private String TAG_LOAD_MORE = "tag_load_more";

    private String pageSize = "10";
    private String page = "1";
    private String createStartTime = "";
    private String createEndTime = "";
    private String progressStartTime = "";
    private String progressEndTime = "";
    private String minBudget = "";
    private String maxBudget = "";
    private String status = "";
    private String brandId = "";
    private String versionId = "";
    private String orderType = "CREATE";
    private String userId = "";

    private int selectState = 0;
    private int selectTime = 0;

    private int CURRENT_PAGE = 1;

    private List<ItemData> dataList = new ArrayList<>();
    private String token;
    private BaseAdapter mAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_customer_manager;
    }

    @Override
    public void initParams(Bundle params) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mCustomerRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCustomerRecycler.addItemDecoration(new SpaceItemDecoration(10));
        mCustomerRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mAdapter.setLoadState(mAdapter.LOADING);
                if (dataList.size() < count) {
                    CURRENT_PAGE++;
                    initRecycler(TAG_LOAD_MORE);
                } else {
                    mAdapter.setLoadState(mAdapter.LOADING_END);
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        initTagFlowLayout();
        initRecycler(TAG_LOAD_MORE);
    }

    @SuppressLint("CheckResult")
    private void initRecycler(final String tag) {
        if (dataList.size() > 0) {
            if (dataList.get(dataList.size() - 1).getHolderType() == SettingDelegate.FOOT_TYPE) {
                dataList.remove(dataList.size() - 1);
            }
        }
        if (TAG_FILTER.equals(tag)) {
            dataList.clear();
            CURRENT_PAGE = 1;
        }
        minBudget = mEtMinMoney.getText().toString();
        maxBudget = mEtMaxMoney.getText().toString();
        progressStartTime = mEtFollowStartTime.getText().toString();
        progressEndTime = mEtFollowEndTime.getText().toString();
        createStartTime = mEtCreateStartTime.getText().toString();
        createEndTime = mEtCreateEndTime.getText().toString();
        page = CURRENT_PAGE + "";
        Map<String, RequestBody> params = new HashMap<>();
        params.put("pageSize", toRequestBody(pageSize));
        params.put("page", toRequestBody(page));
        params.put("createStartTime", toRequestBody(createStartTime));
        params.put("createEndTime", toRequestBody(createEndTime));
        params.put("progressStartTime", toRequestBody(progressStartTime));
        params.put("progressEndTime", toRequestBody(progressEndTime));
        params.put("minBudget", toRequestBody(minBudget));
        params.put("maxBudget", toRequestBody(maxBudget));
        params.put("status", toRequestBody(status));
        params.put("brandId", toRequestBody(brandId));
        params.put("versionId", toRequestBody(versionId));
        params.put("orderType", toRequestBody(orderType));
        params.put("userId", toRequestBody(userId));
        Injection.provideApiService().getCustomerList(token, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CustomerResponse>() {
                    @Override
                    public void accept(CustomerResponse s) throws Exception {
                        if (s != null && s.getCode() == 200) {
                            count = s.getData().getCount();
                            for (int i = 0; i < s.getData().getLists().size(); i++) {
                                ItemData e = new ItemData(0, SettingDelegate.CUSTOMER_MANAGER_TYPE, s.getData().getLists().get(i));
                                dataList.add(e);
                                mAdapter.notifyDataSetChanged();
                            }
                            dataList.add(new ItemData(0, SettingDelegate.FOOT_TYPE));
                        }
                    }
                });
        mAdapter = new BaseAdapter(dataList, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                if (data instanceof CustomerResponse.DataBean.ListsBean) {
                    Bundle bundle = new Bundle();
                    bundle.putString("customerId", ((CustomerResponse.DataBean.ListsBean) data).getCustomerId());
                    startActivity(CustomerDetailActivity.class, bundle);
                }
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        });

        mCustomerRecycler.setAdapter(mAdapter);
    }

    @OnClick({R.id.img_back, R.id.img_add_client, R.id.rb_customer_state, R.id.rb_follow_time, R.id.rb_customer_filter, R.id.btn_sure, R.id.btn_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.img_add_client:
                startActivity(CreateNewCustomerActivity.class);
                break;
            case R.id.rb_customer_state:
                showPopUpWindow(R.id.rb_customer_state);
                break;
            case R.id.rb_follow_time:
                showPopUpWindow(R.id.rb_follow_time);
                break;
            case R.id.rb_customer_filter:
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.btn_reset:
                selectState = 0;
                selectTime = 0;
                mEtMaxMoney.setText("");
                mEtMinMoney.setText("");
                mEtCreateStartTime.setText("");
                mEtCreateEndTime.setText("");
                mEtFollowStartTime.setText("");
                mEtFollowEndTime.setText("");
                break;
            case R.id.btn_sure:
                initRecycler(TAG_FILTER);
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
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
                        if (position == 0) {
                            status = "";
                        } else {
                            status = dataSize.get(position);
                        }
                        initRecycler(TAG_FILTER);
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
                        switch (checkedId) {
                            case R.id.latest_follow_time:
                                orderType = "PROGRESS";
                                break;
                            case R.id.latest_create_time:
                                orderType = "CREATE";
                                break;
                            default:
                        }
                        initRecycler(TAG_FILTER);
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

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }
}
