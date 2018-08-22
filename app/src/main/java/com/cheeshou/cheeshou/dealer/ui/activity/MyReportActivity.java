package com.cheeshou.cheeshou.dealer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.model.response.MyReportResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/8/6.
 */
public class MyReportActivity extends BaseActivity {

    @BindView(R.id.tv_xinzeng)
    TextView tvXinzeng;
    @BindView(R.id.tv_daodian)
    TextView tvDaodian;
    @BindView(R.id.tv_yishou)
    TextView tvYishou;
    @BindView(R.id.tv_month_xinzeng)
    TextView tvMonthXinzeng;
    @BindView(R.id.tv_month_daodian)
    TextView tvMonthDaodian;
    @BindView(R.id.tv_month_yishou)
    TextView tvMonthYishou;
    private String token;

    @Override
    public int bindLayout() {
        return R.layout.activity_my_report;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doBusiness(Context mContext) {
        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);
        Injection.provideApiService().findCustomerCount(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyReportResponse>() {
                    @Override
                    public void accept(MyReportResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            tvXinzeng.setText(response.getData().getDayCustomerCount()+"");
                            tvDaodian.setText(response.getData().getDayEnterCount()+"");
                            tvYishou.setText(response.getData().getDaySaleCount()+"");
                            tvMonthXinzeng.setText(response.getData().getMonthCustomerCount()+"");
                            tvMonthDaodian.setText(response.getData().getMonthEnterCount()+"");
                            tvMonthYishou.setText(response.getData().getMonthSaleCount()+"");
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.rl_day_new, R.id.rl_day_store, R.id.rl_day_shouchu, R.id.rl_store_report, R.id.rl_month_shouchu, R.id.rl_month_new, R.id.rl_month_store})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_day_new:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_DAY_NEW);
                startActivity(ClientListActivity.class, bundle);
                break;
            case R.id.rl_day_store:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_DAY_SHOP);
                startActivity(ClientListActivity.class, bundle);
                break;
            case R.id.rl_month_new:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_MONTH_NEW);
                startActivity(ClientListActivity.class, bundle);
                break;
            case R.id.rl_month_store:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_MONTH_SHOP);
                startActivity(ClientListActivity.class, bundle);
                break;
            case R.id.rl_day_shouchu:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_DAY_SELL);
                startActivity(ReportCarListActivity.class, bundle);
                break;
            case R.id.rl_store_report:
                startActivity(StoreReportActivity.class);
                break;
            case R.id.rl_month_shouchu:
                bundle = new Bundle();
                bundle.putString("source", C.SOURCE_MONTH_SELL);
                startActivity(ReportCarListActivity.class, bundle);
                break;
        }
    }
}
