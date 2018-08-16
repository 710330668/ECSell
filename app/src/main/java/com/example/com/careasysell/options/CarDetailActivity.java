package com.example.com.careasysell.options;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.options.model.response.CarDetailResponse;
import com.example.com.careasysell.remote.Injection;
import com.example.com.careasysell.utils.ImagPagerUtil;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.careasysell.view.CommonDialog;
import com.example.com.careasysell.view.banner.BannerView;
import com.example.com.common.BaseActivity;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.SP;
import com.example.com.common.util.TimeUtils;
import com.example.com.imageloader.LoaderManager;

import java.util.ArrayList;
import java.util.Arrays;
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
    @BindView(R.id.btn_release_options)
    Button btnReleaseOptions;
    private PopupWindow pop;
    private View popView;
    private Button modifyBtn, shelvesBtn, reservateBtn;
    private String carId;
    private String token;
    public static String[] titles;
    public static String[] urls;
    List<BannerItem> list = new ArrayList<>();
    private List<String> discounts;

    @C.INVENTORY
    public int INVENTORY;

    @Override
    public int bindLayout() {
        return R.layout.activity_car_detail;
    }

    @Override
    public void initParams(Bundle params) {

        INVENTORY = ParamManager.getInstance(this).getChannelType();

        if (params != null) {
            carId = params.getString("carId");
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

    }

    @Override
    public void doBusiness(Context mContext) {
        getCarDetail();
        switch (INVENTORY) {
            case C.INVENTORY_OPTION:
                llyShare.setVisibility(View.GONE);
                btnCarShare.setVisibility(View.GONE);
                LayoutInflateView(INVENTORY);
                break;
            case C.INVENTORY_DEALER:
                llyShare.setVisibility(View.VISIBLE);
                btnCarShare.setVisibility(View.GONE);
                LayoutInflateView(INVENTORY);
                break;
            case C.INVENTORY_MARKET:
                llyShare.setVisibility(View.GONE);
                btnCarShare.setVisibility(View.VISIBLE);
                LayoutInflateView(INVENTORY);
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void getCarDetail() {
        Injection.provideApiService().getCarDetail(token, carId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarDetailResponse>() {
                    @Override
                    public void accept(CarDetailResponse response) throws Exception {
                        LogUtils.e(response.getMsg());
                        if (response.getCode() == 200) {
                            tvAdvise.setText("建议售价" + response.getData().getAdvicePrice() + "万|" + "销售提成" + response.getData().getSaleCommission() + "万");
                            tvVname.setText(response.getData().getVname());
                            tvCarPrice.setText(response.getData().getCarPrice()+"万");
                            tvCarGuidePrice.setText(response.getData().getGuidPrice()+"万");
                            tvSellStatus.setText(response.getData().getCarStatusName());
                            tvShareShelvesNum.setText("上架"+response.getData().getShelvesNum()+"次|分享"+response.getData().getShareNum()+"次");
                            tvCreateDate.setText(TimeUtils.millis2String(response.getData().getCreateDate()));
                            tvCarColor.setText("外观"+response.getData().getOutsiteColor() + " " + "内饰"+response.getData().getWithinColor());
                            tvCarType.setText(response.getData().getCarType());
                            tvProvince.setText(response.getData().getProvinceName());
                            tvSalerArea.setText(response.getData().getSaleArea());
                            for(int i = 0;i<response.getData().getDiscounts().size();i++){
                                discounts= Arrays.asList(response.getData().getDiscounts().get(i).getDiscountName().split(","));
                            }
                            addDiscounts(discounts);
                            tvDiscountContent.setText(response.getData().getDiscountContent());
                            tvCarFormality.setText(response.getData().getCarFormality());
                            tvCarYear.setText(response.getData().getCarYear());
                            tvCarSetting.setText(response.getData().getCarSeries());
                            tvRemark.setText(response.getData().getRemark());
                            urls = new String[response.getData().getImgs().size()];
                            for (int i = 0 ;i<response.getData().getImgs().size();i++){
                                urls[i] = response.getData().getImgs().get(i).getImgThumUrl();
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
        for(int i =0 ;i<discounts.size();i++) {
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
            linearParams.width = 200;
            button.setLayoutParams(linearParams);
        }
    }

    private void LayoutInflateView(int inventory) {
        switch (inventory) {
            case C.INVENTORY_OPTION:
                modifyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(ReleaseOptionActivity.class);
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
                        startActivity(ModifyCarInfActivity.class);
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
                        startActivity(ReleaseOptionActivity.class);
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
                        startActivity(ReleaseOptionActivity.class);
                        pop.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_amplification, R.id.iv_more})
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
        }
    }

    private void showPopWindow() {
        pop = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.showAsDropDown(ivMore, 10, 70);
    }

    private void showDialog(String title, String content, String cancel, String confirm) {
        final CommonDialog dialog = new CommonDialog(this, title, content, confirm, cancel);
        dialog.show();
        dialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
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
            LoaderManager.getLoader().loadNet(iv, item.image);
            return iv;
        }
    }
}
