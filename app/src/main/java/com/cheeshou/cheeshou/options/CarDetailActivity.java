package com.cheeshou.cheeshou.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.config.C;
import com.cheeshou.cheeshou.dealer.ui.activity.PutAwayDetailActivity;
import com.cheeshou.cheeshou.dealer.ui.model.SearchResultModel;
import com.cheeshou.cheeshou.dealer.ui.model.response.EasyResponse;
import com.cheeshou.cheeshou.options.model.response.CarDetailResponse;
import com.cheeshou.cheeshou.order.response.OrderDetailResponse;
import com.cheeshou.cheeshou.remote.Injection;
import com.cheeshou.cheeshou.utils.ImagPagerUtil;
import com.cheeshou.cheeshou.utils.ParamManager;
import com.cheeshou.cheeshou.view.CommonDialog;
import com.cheeshou.cheeshou.view.banner.BannerView;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.TimeUtils;
import com.example.com.common.util.ToastUtils;
import com.example.com.imageloader.LoaderManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 71033 on 2018/7/28.
 */
public class CarDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.banner_view)
    BannerView bannerView;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.lly_share)
    LinearLayout llyShare;
    @BindView(R.id.btn_car_share)
    Button btnCarShare;
    @BindView(R.id.tv_advise)
    TextView tvAdvise;
    @BindView(R.id.tv_vname)
    TextView tvVname;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_amplification)
    ImageView ivAmplification;
    @BindView(R.id.tv_car_price)
    TextView tvCarPrice;
    @BindView(R.id.tv_car_guide_price)
    TextView tvCarGuidePrice;
    @BindView(R.id.tv_sell_status)
    TextView tvSellStatus;
    @BindView(R.id.tv_share_shelves_num)
    TextView tvShareShelvesNum;
    @BindView(R.id.tv_create_date)
    TextView tvCreateDate;
    @BindView(R.id.tv_car_color)
    TextView tvCarColor;
    @BindView(R.id.tv_car_type)
    TextView tvCarType;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_saler_area)
    TextView tvSalerArea;
    @BindView(R.id.lly_discounts)
    LinearLayout llyDiscounts;
    @BindView(R.id.tv_discount_content)
    TextView tvDiscountContent;
    @BindView(R.id.tv_car_formality)
    TextView tvCarFormality;
    @BindView(R.id.tv_car_year)
    TextView tvCarYear;
    @BindView(R.id.tv_car_setting)
    TextView tvCarSetting;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.lly_order_infor)
    LinearLayout llyOrderInfor;
    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;
    @BindView(R.id.tv_saler_name)
    TextView tvSalerName;
    @BindView(R.id.tv_deal_valence)
    TextView tvDealValence;
    @BindView(R.id.btn_browse_car)
    Button btnBrowseCar;
    @BindView(R.id.btn_share_car)
    Button btnShareCar;
    @BindView(R.id.tv_reduce)
    TextView tvReduce;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_rebates)
    TextView tvRebates;
    @BindView(R.id.lly_rebates)
    LinearLayout llyRebates;
    private PopupWindow pop;
    private View popView;
    private Button modifyBtn, shelvesBtn, reservateBtn;
    private String carId;
    private String token;
    public static String[] titles;
    public static String[] urls;
    List<BannerItem> list = new ArrayList<>();
    private List<String> discounts = new ArrayList<>();
    private String saleId;

    @C.INVENTORY
    public int INVENTORY;

    private String source;
    private String orderItemId;
    private String dealerSource;
    private CarDetailResponse carDetailResponse;
    private ArrayList<SearchResultModel> mPutAwayData = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_car_detail;
    }

    @Override
    public void initParams(Bundle params) {

        INVENTORY = ParamManager.getInstance(this).getChannelType();

        if (params != null) {
            carId = params.getString("carId");
            source = params.getString("source");
            orderItemId = params.getString("orderItemId");
            dealerSource = params.getString("dealer_source");
            mPutAwayData = params.getParcelableArrayList("shelves_data");
        }

        token = SP.getInstance(C.USER_DB, this).getString(C.USER_TOKEN);

//        titles = new String[]{
//                "汽车之家",
//                "汽车之家",
//                "汽车之家",
//        };
//        urls = new String[]{//750x500
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532783905498&di=1f7427ff1590d6136c691108f4869041&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fc2fdfc039245d6884720b652aec27d1ed21b2421.jpg",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532783933849&di=db9e5ddf0af84ddd69955f55cfeed304&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dbb01900a9945d688b70fbae4cdba1872%2Fac4bd11373f082022253c80640fbfbedaa641bc1.jpg",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533378684&di=43748b1e58a528d2557de7f347ad2097&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.doooor.com%2Fimg%2Fforum%2F201407%2F27%2F001137nxb68brur47mb99t.jpg",
//        };

        popView = LayoutInflater.from(getApplication()).inflate(R.layout.item_pop, null, false);
        modifyBtn = popView.findViewById(R.id.btn_modify);
        shelvesBtn = popView.findViewById(R.id.btn_xiajia);
        reservateBtn = popView.findViewById(R.id.btn_yuding);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(source)) {
            llyOrderInfor.setVisibility(View.VISIBLE);
            llyShare.setVisibility(View.GONE);
            btnCarShare.setVisibility(View.GONE);
            ivMore.setVisibility(View.GONE);
        } else {
            llyOrderInfor.setVisibility(View.GONE);
        }
        tvCarGuidePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public void doBusiness(Context mContext) {
        if (TextUtils.isEmpty(source)) {
            getCarDetail();
        } else {
            tvRebates.setVisibility(View.VISIBLE);
            getOrderDetail();
        }

        switch (INVENTORY) {
            case C.INVENTORY_OPTION:
                llyRebates.setVisibility(View.GONE);
                llyShare.setVisibility(View.GONE);
                btnCarShare.setVisibility(View.GONE);
                LayoutInflateView(INVENTORY);
                break;
            case C.INVENTORY_DEALER:
                if (!TextUtils.isEmpty(dealerSource)) {
                    btnBrowseCar.setVisibility(View.VISIBLE);
                    ivMore.setVisibility(View.GONE);
                } else {
                    btnShareCar.setVisibility(View.VISIBLE);
                }
                llyShare.setVisibility(View.VISIBLE);
                btnCarShare.setVisibility(View.GONE);
                LayoutInflateView(INVENTORY);
                tvName.setVisibility(View.VISIBLE);
                break;
            case C.INVENTORY_MARKET:
                llyShare.setVisibility(View.GONE);
                btnCarShare.setVisibility(View.VISIBLE);
                LayoutInflateView(INVENTORY);
                break;
            default:
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void getOrderDetail() {
        discounts.clear();
        Injection.provideApiService().findOrderDetail(token, orderItemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderDetailResponse>() {
                    @Override
                    public void accept(OrderDetailResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            saleId = response.getData().getCarId();
                            tvName.setText(response.getData().getCarUserName() + "|" + response.getData().getProvinceCode() + response.getData().getCityName());
                            tvAdvise.setText("建议售价" + response.getData().getGuidPrice() + "万|" + "销售提成" + response.getData().getSaleCommission() + "万" + "销售底价" + response.getData().getSaleCarPrice() + "万");
                            tvVname.setText(response.getData().getVname());
                            tvCarPrice.setText(response.getData().getCarPrice() + "万");
                            tvCarGuidePrice.setText(response.getData().getAdvicePrice() + "万");
                            tvReduce.setText("降价" + (response.getData().getAdvicePrice() - response.getData().getCarPrice() + "万"));
                            tvSellStatus.setText(response.getData().getCarStatusName());
                            tvShareShelvesNum.setText("分享" + response.getData().getShareNum() + "次|浏览" + response.getData().getBrowseNum() + "次");
                            tvCreateDate.setText(TimeUtils.millis2String(response.getData().getCreateDate()));
                            tvCarColor.setText("外观" + response.getData().getOutsiteColor() + " " + "内饰" + response.getData().getWithinColor());
                            tvCarType.setText(response.getData().getTypeName());
                            tvProvince.setText(response.getData().getProvinceName() + " " + response.getData().getCityName());
                            tvSalerArea.setText(response.getData().getSaleArea());
                            for (int i = 0; i < response.getData().getDiscounts().size(); i++) {
                                discounts.add(response.getData().getDiscounts().get(i).getDiscountName());
                            }
                            addDiscounts(discounts);
                            tvRebates.setText("保险返点" + response.getData().getInsuranceRebates() + "%" + "|贷款返点" + response.getData().getLoanRebates() + "%");
                            tvDiscountContent.setText(response.getData().getDiscountContent());
                            tvCarFormality.setText(response.getData().getCarFormality());
                            tvCarYear.setText(response.getData().getCarYear());
                            tvCarSetting.setText(response.getData().getCarSeries());
                            tvRemark.setText(response.getData().getRemark());
                            tvCustomerName.setText(response.getData().getCustomerName());
                            tvSalerName.setText(response.getData().getUserName());
                            tvDealValence.setText(response.getData().getOrderPrice() + "万");
                            urls = new String[response.getData().getImgs().size()];
                            for (int i = 0; i < response.getData().getImgs().size(); i++) {
                                urls[i] = response.getData().getImgs().get(i).getImgUrl();
                            }
                            for (int i = 0; i < urls.length; i++) {
                                BannerItem item = new BannerItem();
                                item.image = urls[i];
                                list.add(item);
                            }
                            bannerView.setViewFactory(new BannerViewFactory());
                            bannerView.setDataList(list);
                            bannerView.start();
                        }
                    }
                });
    }


    @SuppressLint("CheckResult")
    private void getCarDetail() {
        discounts.clear();
        Injection.provideApiService().getCarDetail(token, carId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarDetailResponse>() {
                    @Override
                    public void accept(CarDetailResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            carDetailResponse = response;
                            saleId = response.getData().getSaleId();
                            tvName.setText(response.getData().getCarUserName() + "|" + response.getData().getProvinceCode() + response.getData().getCityName());
                            if (INVENTORY == C.INVENTORY_DEALER) {
                                tvAdvise.setText("车源价" + response.getData().getCarPrice() + "万|"
                                        + "建议售价" + response.getData().getGuidPrice() + "万|"
                                        + "销售提成" + response.getData().getSaleCommission() + "万");
                            } else if (INVENTORY == C.INVENTORY_MARKET) {
                                tvAdvise.setText("建议售价" + response.getData().getGuidPrice() + "万");
                            } else {
                                tvAdvise.setText("建议售价" + response.getData().getGuidPrice() + "万|" + "销售提成" + response.getData().getSaleCommission() + "万");
                            }
                            tvVname.setText(response.getData().getBrand() + "-" + response.getData().getVname());
                            tvCarPrice.setText(response.getData().getCarPrice() + "万");
                            tvCarGuidePrice.setText(response.getData().getAdvicePrice() + "万");
                            tvRebates.setText("保险返点" + response.getData().getInsuranceRebates() + "%" + "|贷款返点" + response.getData().getLoanRebates() + "%");
                            tvReduce.setText("降价" + (response.getData().getAdvicePrice() - response.getData().getCarPrice() + "万"));
                            tvSellStatus.setText(response.getData().getCarStatusName());
                            tvShareShelvesNum.setText("上架" + response.getData().getShelvesNum() + "次|分享" + response.getData().getShareNum() + "次|浏览" + response.getData().getBrowseNum() + "次");
                            tvCreateDate.setText(TimeUtils.millis2String(response.getData().getCreateDate()));
                            tvCarColor.setText("外观" + response.getData().getOutsiteColor() + " " + "内饰" + response.getData().getWithinColor());
                            tvCarType.setText(response.getData().getTypeName());
                            tvProvince.setText(response.getData().getProvinceName() + " " + response.getData().getCityName());
                            tvSalerArea.setText(response.getData().getSaleArea());
                            for (int i = 0; i < response.getData().getDiscounts().size(); i++) {
                                discounts.add(response.getData().getDiscounts().get(i).getDiscountName());
                            }
                            addDiscounts(discounts);
                            tvDiscountContent.setText(response.getData().getDiscountContent());
                            tvCarFormality.setText(response.getData().getCarFormality());
                            tvCarYear.setText(response.getData().getCarYear());
                            tvCarSetting.setText(response.getData().getCarSeries());
                            tvRemark.setText(response.getData().getRemark());
                            urls = new String[response.getData().getImgs().size()];
                            for (int i = 0; i < response.getData().getImgs().size(); i++) {
                                urls[i] = response.getData().getImgs().get(i).getImgUrl();
                            }
                            for (int i = 0; i < urls.length; i++) {
                                BannerItem item = new BannerItem();
                                item.image = urls[i];
                                list.add(item);
                            }
                            bannerView.setViewFactory(new BannerViewFactory());
                            bannerView.setDataList(list);
                            bannerView.start();
                        }
                    }
                });
    }

    private void addDiscounts(List<String> discounts) {
        if (discounts == null) {
            return;
        }
        for (int i = 0; i < discounts.size(); i++) {
            final Button button = new Button(CarDetailActivity.this);
            button.setText(discounts.get(i));
            button.setBackgroundResource(R.drawable.bg_edittext_red);
            button.setPadding(10, 10, 10, 10);
            button.setGravity(Gravity.CENTER);
            button.setTextColor(getResources().getColor(R.color.color_FF5754));
            llyDiscounts.addView(button);
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            linearParams.setMargins(20, 20, 10, 10);
            linearParams.height = 80;
            linearParams.width = 240;
            button.setLayoutParams(linearParams);
        }
    }

    private void LayoutInflateView(int inventory) {
        switch (inventory) {
            case C.INVENTORY_OPTION:
                modifyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("carDetailResponse", (Serializable) carDetailResponse);
                        startActivity(ReleaseOptionActivity.class, bundle);
                        pop.dismiss();
                    }
                });
                shelvesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog("确定下架车辆?", "下架后,车辆信息将不可找回", "取消", "确定");
                    }
                });
                reservateBtn.setVisibility(View.GONE);
                break;
            case C.INVENTORY_DEALER:
                reservateBtn.setText("取消预定");
                modifyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("saleId", carDetailResponse.getData().getSaleId());
                        startActivity(ModifyCarInfActivity.class, bundle);
                        pop.dismiss();
                    }
                });
                shelvesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog("确定下架车辆?", "下架后,车辆信息将不可找回", "取消", "确定");
                    }
                });
                reservateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unReserveSaleCarInfo();
                        pop.dismiss();
                    }
                });
                break;
            case C.INVENTORY_MARKET:
                modifyBtn.setVisibility(View.GONE);
                shelvesBtn.setVisibility(View.GONE);
                reservateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reservate();
                        pop.dismiss();
                    }
                });
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void unReserveSaleCarInfo() {
        Injection.provideApiService().unReserveSaleCarInfo(token, saleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EasyResponse>() {
                    @Override
                    public void accept(EasyResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        ToastUtils.showShort(CarDetailActivity.this, response.getMsg());
                        if (response.getCode() == 200) {

                        }

                    }
                });
    }

    //预定车辆
    @SuppressLint("CheckResult")
    private void reservate() {
        Injection.provideApiService().reserveSaleCarInfo(token, saleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EasyResponse>() {
                    @Override
                    public void accept(EasyResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        ToastUtils.showShort(CarDetailActivity.this, response.getMsg());
                        if (response.getCode() == 200) {

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

    @OnClick({R.id.iv_back, R.id.iv_amplification, R.id.iv_more, R.id.btn_browse_car, R.id.btn_share_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_amplification:
                ImagPagerUtil imagPagerUtil = new ImagPagerUtil(CarDetailActivity.this, urls);
                imagPagerUtil.show();
                break;
            case R.id.iv_more:
                showPopWindow();
                break;
            case R.id.btn_browse_car:
                //上架车辆
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("data", mPutAwayData);
                startActivity(PutAwayDetailActivity.class, bundle);
                break;
            case R.id.btn_share_car:
                //分享车辆
                break;
        }
    }

    private void showPopWindow() {
        pop = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.showAsDropDown(ivMore, 0, 2);
    }

    private void showDialog(String title, String content, String cancel, String confirm) {
        final CommonDialog dialog = new CommonDialog(this, title, content, confirm, cancel);
        dialog.show();
        dialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
                soldOutCarInfo();
                dialog.dismiss();
            }

            @Override
            public void doConfirm(String etContent) {

            }

            @Override
            public void doCancel() {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void soldOutCarInfo() {
        Injection.provideApiService().soldOutCarInfo(token, carDetailResponse.getData().getSaleId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EasyResponse>() {
                    @Override
                    public void accept(EasyResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        ToastUtils.showShort(CarDetailActivity.this, response.getMsg());
                    }
                });
    }


    public static class BannerItem {
        public String image;
        public String title;

        @Override
        public String toString() {
            return title;
        }
    }

    public static class BannerViewFactory implements BannerView.ViewFactory<BannerItem> {
        @Override
        public View create(BannerItem item, int position, ViewGroup container) {
            ImageView iv = new ImageView(container.getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            LoaderManager.getLoader().loadNet(iv, item.image);
            return iv;
        }
    }
}
