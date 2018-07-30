package com.example.com.careasysell.options;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.config.C;
import com.example.com.careasysell.utils.ImagPagerUtil;
import com.example.com.careasysell.utils.ParamManager;
import com.example.com.careasysell.view.CommonDialog;
import com.example.com.careasysell.view.banner.BannerView;
import com.example.com.common.BaseActivity;
import com.example.com.imageloader.LoaderManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    public static String[] titles;
    public static String[] urls;
    List<BannerItem> list = new ArrayList<>();
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.lly_share)
    LinearLayout llyShare;
    private PopupWindow pop;

    @C.INVENTORY
    public int INVENTORY;

    @Override
    public int bindLayout() {
        return R.layout.activity_car_detail;
    }

    @Override
    public void initParams(Bundle params) {

        INVENTORY = ParamManager.getInstance(this).getChannelType();

        titles = new String[]{
                "汽车之家",
                "汽车之家",
                "汽车之家",
        };
        urls = new String[]{//750x500
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532783905498&di=1f7427ff1590d6136c691108f4869041&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fc2fdfc039245d6884720b652aec27d1ed21b2421.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532783933849&di=db9e5ddf0af84ddd69955f55cfeed304&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dbb01900a9945d688b70fbae4cdba1872%2Fac4bd11373f082022253c80640fbfbedaa641bc1.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533378684&di=43748b1e58a528d2557de7f347ad2097&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.doooor.com%2Fimg%2Fforum%2F201407%2F27%2F001137nxb68brur47mb99t.jpg",
        };

        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.image = urls[i];
            item.title = titles[i];

            list.add(item);
        }
    }

    @Override
    public void setView(Bundle savedInstanceState) {

    }

    @Override
    public void doBusiness(Context mContext) {
        switch (INVENTORY) {
            case C.INVENTORY_OPTION:
                llyShare.setVisibility(View.GONE);
                break;
            case C.INVENTORY_DEALER:
                llyShare.setVisibility(View.VISIBLE);
                break;
            case C.INVENTORY_MARKET:
                llyShare.setVisibility(View.GONE);
                break;
        }
        bannerView.setViewFactory(new BannerViewFactory());
        bannerView.setDataList(list);
        bannerView.start();
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
        View popView = LayoutInflater.from(getApplication()).inflate(R.layout.item_pop, null, false);
        Button modifyBtn = popView.findViewById(R.id.btn_modify);
        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ReleaseOptionActivity.class);
                pop.dismiss();
            }
        });
        Button shelvesBtn = popView.findViewById(R.id.btn_xiajia);
        shelvesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("确定下架车辆?","下架后,车辆信息将不可找回","取消","确定");
            }
        });
        pop = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.showAsDropDown(ivMore, 10, 70);
    }

    private void showDialog(String title, String content, String cancel, String confirm) {
        final CommonDialog dialog = new CommonDialog(this, title, content,confirm, cancel);
        dialog.show();
        dialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
                dialog.dismiss();
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
