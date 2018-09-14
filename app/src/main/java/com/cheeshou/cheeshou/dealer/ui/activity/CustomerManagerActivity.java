package com.cheeshou.cheeshou.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CarStateModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerResponse;
import com.cheeshou.cheeshou.main.login.LoginActivity;
import com.cheeshou.cheeshou.options.ChooseBrandActivity;
import com.cheeshou.cheeshou.options.ChooseCarsActivity;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.utils.EndlessRecyclerOnScrollListener;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.adapter.onItemClickListener;
import com.example.com.common.util.SP;
import com.example.com.common.util.ToastUtils;
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


    private final int REQUEST_BRAND = 0;
    private static final int REQUEST_CLIENT = 1;
    private final int REQUEST_CAR = 2;
    private final int REQUEST_CAR_TYPE = 3;
    private static final int REQUEST_CREATE = 4;
    private String carBrand;

    @BindView(R.id.rb_customer_state)
    RadioButton mRbState;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.rb_follow_time)
    RadioButton mRbTime;
    @BindView(R.id.drawer_root)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.et_search)
    EditText mEtSearch;

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
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.rg_filter)
    RadioGroup mRadioGroup;
    @BindView(R.id.ll_want_brand)
    LinearLayout mWantBrand;
    @BindView(R.id.ll_want_type)
    LinearLayout mWantType;
    @BindView(R.id.ll_create_person)
    LinearLayout mCreatePerson;
    @BindView(R.id.tv_want_brand)
    TextView mTvWantBrand;
    @BindView(R.id.tv_want_type)
    TextView mTvWantType;
    @BindView(R.id.tv_username)
    TextView mTvUserName;
    @BindView(R.id.tv_want_car_type)
    TextView mTvCarType;
    private int count;
    private String TAG_FILTER = "tag_filter";
    private String TAG_LOAD_MORE = "tag_load_more";

    private List<ItemData> stateData;
    private List<ItemData> orderDate;

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
    private String orderType = "PROGRESS";
    private String userId = "";
    private String queryKey = "";

    private int selectState = 0;
    private int selectTime = 0;

    private int CURRENT_PAGE = 1;

    private List<ItemData> dataList = new ArrayList<>();
    private String token;
    private BaseAdapter mAdapter;
    private String carType = "";

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
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                queryKey = mEtSearch.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        initPopupWindowData();
        initTagFlowLayout();
        initRecycler(TAG_LOAD_MORE);
    }

    private void initPopupWindowData() {
        stateData = new ArrayList<>();
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("全部", "", true)));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("未到店", "NO_STORE")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已到店", "YES_STORE")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已预订", "RESERVE")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("已成交", "SUCCESS")));
        stateData.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_STATE_TYPE, new CarStateModel("战败", "FAIL")));

        orderDate = new ArrayList<>();
        orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("最新跟进时间", "PROGRESS", true)));
        orderDate.add(new ItemData(0, SettingDelegate.POPUP_WINDOW_CAR_ORDER_TYPE, new CarStateModel("最新创建时间", "CREATE")));

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
        params.put("queryKey", toRequestBody(queryKey));
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
                        } else if (s.getCode() == 402 || s.getCode() == 401) {
                            //token失效
                            SP.getInstance(C.USER_DB, CustomerManagerActivity.this).put(C.USER_ACCOUNT, "");
                            SP.getInstance(C.USER_DB, CustomerManagerActivity.this).put(C.USER_PASSWORD, "");
                            finishAllActivity();
                            startActivity(LoginActivity.class);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SP.getInstance(C.USER_DB, CustomerManagerActivity.this).put(C.USER_ACCOUNT, "");
                        SP.getInstance(C.USER_DB, CustomerManagerActivity.this).put(C.USER_PASSWORD, "");
                        finishAllActivity();
                        startActivity(LoginActivity.class);
                    }
                });
        mAdapter = new BaseAdapter(dataList, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                if (data instanceof CustomerResponse.DataBean.ListsBean) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("customer", ((CustomerResponse.DataBean.ListsBean) data));
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

    @OnClick({R.id.img_back, R.id.img_add_client, R.id.rb_customer_state, R.id.rb_follow_time, R.id.rb_customer_filter, R.id.btn_sure, R.id.btn_reset, R.id.tv_search, R.id.ll_want_brand, R.id.ll_want_type, R.id.ll_create_person, R.id.ll_want_car_type})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.img_add_client:
                startActivityForResult(new Intent(this, CreateNewCustomerActivity.class), REQUEST_CREATE);
                break;
            case R.id.rb_customer_state:
                showPopupWindow(R.id.rb_customer_state);
                break;
            case R.id.rb_follow_time:
                showPopupWindow(R.id.rb_follow_time);
                break;
            case R.id.rb_customer_filter:
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.btn_reset:
                // TODO: 2018/8/30  
                CURRENT_PAGE = 1;
                userId = "";
                selectState = 0;
                selectTime = 0;
                mEtMaxMoney.setText("");
                mEtMinMoney.setText("");
                mEtCreateStartTime.setText("");
                mEtCreateEndTime.setText("");
                mEtFollowStartTime.setText("");
                mEtFollowEndTime.setText("");
                carType = "";
                carBrand = "";
                brandId = "";
                versionId = "";
                mTvCarType.setText("不限");
                mTvCarType.setTextColor(Color.parseColor("#333333"));
                mTvWantBrand.setText("不限");
                mTvWantBrand.setTextColor(Color.parseColor("#333333"));
                mTvWantType.setText("不限");
                mTvWantType.setTextColor(Color.parseColor("#333333"));
                mTvUserName.setText("不限");
                mTvUserName.setTextColor(Color.parseColor("#333333"));
                mCreateTimeTag.getAdapter().setSelectedList(0);
                mFollowTimeTag.getAdapter().setSelectedList(0);
                break;
            case R.id.btn_sure:
                initRecycler(TAG_FILTER);
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_search:
                initRecycler(TAG_FILTER);
                break;
            case R.id.ll_want_brand:
                if (!TextUtils.isEmpty(carType)) {
                    bundle.putString("params", "filter");
                    bundle.putString("optionId", carType);
                    startActivityForResult(ChooseBrandActivity.class, bundle, REQUEST_BRAND);
                } else {
                    ToastUtils.showShort(this, "请选择车源类型");
                }

                break;
            case R.id.ll_want_type:
                if ("".equals(brandId)) {
                    bundle.putString("params", "filter");
                    bundle.putString("optionId", carType);
                    startActivityForResult(ChooseBrandActivity.class, bundle, REQUEST_BRAND);
                } else {
                    bundle.putString("carBrand", carBrand);
                    bundle.putString("brandId", brandId);
                    bundle.putString("params", "filter");
                    startActivityForResult(ChooseCarsActivity.class, bundle, REQUEST_CAR);
                }
                break;
            case R.id.ll_create_person:
                Intent intent = new Intent(this, SalerManagerActivity.class);
                startActivityForResult(intent, REQUEST_CLIENT);
                break;
            case R.id.ll_want_car_type:
                Intent inten2t = new Intent(this, CarSourceTypeActivity.class);
                startActivityForResult(inten2t, REQUEST_CAR_TYPE);
                break;
            default:
        }
    }

    public void showPopupWindow(final int viewId) {
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        switch (viewId) {
            case R.id.rb_customer_state:
                LinearLayout convertFrame = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_popup_car_state_recycler, null);
                RecyclerView stateRecycler = (RecyclerView) convertFrame.findViewById(R.id.recycler_car_state);
                initStateRecycler(stateRecycler);
                mPopupWindow = new PopupWindow(convertFrame, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setBackgroundDrawable(dw);
                mPopupWindow.showAsDropDown(mRadioGroup, 0, 0);
                break;
            case R.id.rb_follow_time:
                LinearLayout convertLinear = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_popup_car_order_recycler, null);
                RecyclerView orderRecycler = (RecyclerView) convertLinear.findViewById(R.id.recycler_car_order);
                orderRecycler.addItemDecoration(new SpaceItemDecoration(5));
                initOrderRecycler(orderRecycler);
                mPopupWindow = new PopupWindow(convertLinear, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setBackgroundDrawable(dw);
                mPopupWindow.showAsDropDown(mRadioGroup, 0, 0);
                break;
            default:
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
                textView.setText(o);
                return textView;
            }
        };
        mFollowTimeTag.setAdapter(followTimeAdapter);
    }

    /**
     * 初始化 状态recycler
     *
     * @param stateRecycler
     */
    private void initStateRecycler(RecyclerView stateRecycler) {
        stateRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        stateRecycler.setAdapter(new BaseAdapter(stateData, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                for (ItemData bean : stateData) {
                    ((CarStateModel) bean.getData()).setSelected(false);
                }
                if (data instanceof CarStateModel) {
                    ((CarStateModel) data).setSelected(true);
                    status = ((CarStateModel) data).getStateCode();
                    mRbState.setText(((CarStateModel) data).getStateName());
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                initRecycler(TAG_FILTER);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        }));
    }

    /**
     * 初始化 order recycler
     *
     * @param orderRecycler
     */
    private void initOrderRecycler(RecyclerView orderRecycler) {
        orderRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        orderRecycler.setAdapter(new BaseAdapter(orderDate, new SettingDelegate(), new onItemClickListener() {
            @Override
            public void onClick(View v, Object data) {
                for (ItemData bean : orderDate) {
                    ((CarStateModel) bean.getData()).setSelected(false);
                }
                if (data instanceof CarStateModel) {
                    ((CarStateModel) data).setSelected(true);
                    orderType = ((CarStateModel) data).getStateCode();
                    mRbTime.setText(((CarStateModel) data).getStateName());
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                initRecycler(TAG_FILTER);
            }

            @Override
            public boolean onLongClick(View v, Object data) {
                return false;
            }
        }));
    }

    public RequestBody toRequestBody(String value) {
        if (value == null) {
            value = "";
        }
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_BRAND:
                    carBrand = data.getStringExtra("carBrand");
                    mTvWantBrand.setText(carBrand);
                    mTvWantBrand.setTextColor(Color.parseColor("#FF5745"));
                    brandId = data.getStringExtra("brandId");
                    break;
                case REQUEST_CAR:
                    String carCombinate = data.getStringExtra("carCombinate");
                    String audiId = data.getStringExtra("audiId");
                    mTvWantType.setTextColor(Color.parseColor("#FF5745"));
                    mTvWantType.setText(carCombinate);
                    versionId = audiId;
                    break;
                case REQUEST_CLIENT:
                    mTvUserName.setText(data.getStringExtra("saleName"));
                    mTvUserName.setTextColor(Color.parseColor("#FF5745"));
                    userId = data.getStringExtra("xsUserId");
                    break;
                case REQUEST_CAR_TYPE:
                    String carTypeName = data.getStringExtra("carTypeName");
                    String carTypeId = data.getStringExtra("carTypeId");
                    mTvCarType.setText(carTypeName);
                    mTvCarType.setTextColor(Color.parseColor("#FF5745"));
                    carType = carTypeId;
                    break;
                case REQUEST_CREATE:
                    initRecycler(TAG_FILTER);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            mDrawerLayout.closeDrawer(Gravity.RIGHT);
            return;
        }
        super.onBackPressed();
    }
}
