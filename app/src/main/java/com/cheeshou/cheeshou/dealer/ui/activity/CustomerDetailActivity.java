package com.cheeshou.cheeshou.dealer.ui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerDetailWantModel;
import com.cheeshou.cheeshou.dealer.ui.model.CustomerFollowModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerDetailResponse;
import com.cheeshou.cheeshou.dealer.ui.model.response.CustomerResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.remote.SettingDelegate;
import com.cheeshou.cheeshou.view.SpaceItemDecoration;
import com.cheeshou.cheeshou.R;
import com.example.com.common.BaseActivity;
import com.example.com.common.adapter.BaseAdapter;
import com.example.com.common.adapter.ItemData;
import com.example.com.common.util.SP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class CustomerDetailActivity extends BaseActivity {

    @BindView(R.id.recycler_want_car)
    RecyclerView mRecyclerWantCar;

    @BindView(R.id.recycler_follow_list)
    RecyclerView mRecyclerFollow;

    @BindView(R.id.tv_not_arrive_shop)
    TextView mTvState1;
    @BindView(R.id.tv_arrive_shop)
    TextView mTvState2;
    @BindView(R.id.tv_reserve)
    TextView mTvState3;
    @BindView(R.id.tv_deal)
    TextView mTvState4;

    @BindView(R.id.view_line_1)
    View mLine1;
    @BindView(R.id.view_line_2)
    View mLine2;
    @BindView(R.id.view_line_3)
    View mLine3;
    @BindView(R.id.view_line_4)
    View mLine4;
    @BindView(R.id.view_line_5)
    View mLine5;
    @BindView(R.id.view_line_6)
    View mLine6;
    @BindView(R.id.tv_car_price)
    TextView mTvPrice;
    @BindView(R.id.tv_car_brand)
    TextView mTvBrand;
    @BindView(R.id.tv_customer_name)
    TextView mTvName;
    @BindView(R.id.tv_wechat_number)
    TextView mTvWechat;
    @BindView(R.id.tv_telephone_number)
    TextView mTvPhone;
    @BindView(R.id.tv_customer_sex)
    TextView mTvSex;
    @BindView(R.id.tv_offer_name)
    TextView mTvOfferName;
    @BindView(R.id.tv_need_text)
    TextView mTvNeedText;

    @BindView(R.id.tv_customer_remark)
    TextView mTvRemark;
    @BindView(R.id.ll_customer_phone)
    LinearLayout mLinearPhone;
    @BindView(R.id.ll_customer_wechat)
    LinearLayout mLinearWechat;
    @BindView(R.id.ll_customer_message)
    LinearLayout mLinearMessage;
    @BindView(R.id.tv_follow)
    TextView mTvFollow;
    private static final String TAG = "CustomerDetailActivity";
    private List<ItemData> mData = new ArrayList<>();
    private List<ItemData> mFollowData = new ArrayList<>();
    private String customerId;
    private String token;
    private CustomerResponse.DataBean.ListsBean customer;

    @Override
    public int bindLayout() {
        return R.layout.activity_customer_detail;
    }

    @Override
    public void initParams(Bundle params) {
        customer = (CustomerResponse.DataBean.ListsBean) params.getSerializable("customer");
        customerId = customer.getCustomerId();
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        mRecyclerWantCar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerWantCar.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerWantCar.setNestedScrollingEnabled(false);

        mRecyclerFollow.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerFollow.setNestedScrollingEnabled(false);

        mTvBrand.setText(customer.getBrandName());

        initStatus(customer.getStatusName());
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, RequestBody> params = new HashMap<>();
        params.put("customerId", RequestBody.create(MediaType.parse("text/plain"), customerId));
        Injection.provideApiService().getCustomerDetailInfo(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CustomerDetailResponse>() {
            @Override
            public void accept(CustomerDetailResponse s) throws Exception {
                if (s != null && s.getCode() == 200) {
                    mTvWechat.setText(s.getData().getWeBcat());
                    mTvPhone.setText(s.getData().getPhone());
                    mTvName.setText(s.getData().getName());
                    mTvSex.setText(s.getData().getSex() == 0 ? "先生" : "女士");
                    mTvOfferName.setText(s.getData().getUserName());
                    mTvPrice.setText(s.getData().getMinBudget() + "-" + s.getData().getMaxBudget() + "万");
                    List<String> lists = s.getData().getLists();
                    StringBuffer stringBuffer = new StringBuffer();
                    if (lists != null) {
                        for (String s1 : lists) {
                            stringBuffer.append(s1);
                        }
                    }
                    mTvBrand.setText(stringBuffer.toString());
                    mTvNeedText.setText(s.getData().getNeedTxt());
                    mTvRemark.setText(s.getData().getRemark());

                    mData.clear();
                    List<CustomerDetailResponse.DataBean.SaleCarInfoBean> saleCarInfos = s.getData().getSaleCarInfos();
                    for (CustomerDetailResponse.DataBean.SaleCarInfoBean bean : saleCarInfos) {
                        CustomerDetailWantModel data = new CustomerDetailWantModel();
                        data.setDeduct("销售提成" + bean.getSaleCommission() + "元   ");
                        data.setMessage(bean.getCarUserName() + " | " + bean.getProvinceName() + " | " + bean.getCityName());
                        data.setPrice(bean.getCarPrice() + "万");
                        data.setState(bean.getCarStatusName());
                        data.setTime("今天");
                        data.setTitle(bean.getBrand() + "  " + bean.getCarSeries() + "  " + bean.getVname());
                        mData.add(new ItemData(0, SettingDelegate.CUSTOMER_DETAIL_WANT_TYPE, data));
                    }
                    BaseAdapter adapter = new BaseAdapter(mData, new SettingDelegate());
                    mRecyclerWantCar.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    mFollowData.clear();
                    List<CustomerDetailResponse.DataBean.ProgressesBean> progresses = s.getData().getProgresses();
                    for (CustomerDetailResponse.DataBean.ProgressesBean bean : progresses) {
                        CustomerFollowModel data = new CustomerFollowModel();
                        data.setDate(formatDate("yyyy-MM-dd", bean.getCreateDate()));
                        data.setTime(formatDate("hh:mm", bean.getCreateDate()));
                        data.setMessage(bean.getContent());
                        data.setFrom(bean.getSource());
                        mFollowData.add(new ItemData(0, SettingDelegate.CUSTOMER_DETAIL_FOLLOW_TYPE, data));
                    }
                    mRecyclerFollow.setAdapter(new BaseAdapter(mFollowData, new SettingDelegate()));

                }
            }
        });
    }

    @OnClick({R.id.tv_message_edit, R.id.tv_customer_need_edit, R.id.tv_want_car_edit, R.id.ll_customer_message, R.id.ll_customer_phone, R.id.ll_customer_wechat, R.id.tv_follow})
    public void onViewClicked(View view) {
        Intent intent;
        Bundle bundle = new Bundle();
        bundle.putString("customerId", customerId);
        switch (view.getId()) {
            case R.id.tv_message_edit:
                startActivity(EditCustomerMessageActivity.class, bundle);
                break;
            case R.id.tv_customer_need_edit:
                startActivity(EditCustomerNeedActivity.class);
                break;
            case R.id.tv_want_car_edit:
                startActivity(CustomerCarWantActivity.class);
                break;
            case R.id.ll_customer_message:
                // TODO: 2018/8/20 短信
                break;
            case R.id.ll_customer_phone:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.ll_customer_wechat:
                if (isWeixinAvilible(this)) {
                    intent = new Intent();
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                } else {
                    Toast.makeText(appContext, "尚未安装微信", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_follow:
                startActivity(CustomerFollowActivity.class, bundle);
                // TODO: 2018/8/20 跟进
                break;

        }
    }

    public void getCustomerInfo() {

    }

    private void initStatus(String statusName) {
        switch (statusName) {
            case "已成交":
                mTvState4.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine6.setBackgroundColor(Color.parseColor("#FF5755"));
            case "已预定":
                mTvState3.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine5.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine4.setBackgroundColor(Color.parseColor("#FF5755"));
            case "已到店":
                mTvState2.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine3.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine2.setBackgroundColor(Color.parseColor("#FF5755"));
            case "未到店":
                mTvState1.setBackgroundColor(Color.parseColor("#FF5755"));
                mLine1.setBackgroundColor(Color.parseColor("#FF5755"));
                break;
        }
    }

    private String formatDate(String format, long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date1 = new Date(date);
        return simpleDateFormat.format(date1);
    }

    private boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
}
