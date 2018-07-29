package com.example.com.careasysell.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.view.ZoomImageView;
import com.example.com.careasysell.view.banner.LazyViewPager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

public class ImagPagerUtil {
    private List<String> mPicList;
    private Activity mActivity;
    private Dialog dialog;
    private LazyViewPager mViewPager;
    private LinearLayout mLL_progress,mLinearLayout;
    private TextView tv_loadingmsg;
    private int screenWidth;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private TextView tv_img_current_index;
    private TextView tv_img_count;
    private TextView tv_content;
    private List<ImageView> mDotView;

    public ImagPagerUtil(Activity activity, List<String> mPicList) {
        this.mPicList = mPicList;
        this.mActivity = activity;
        imageLoader = ImageLoader.getInstance();
        setOptions();
        init();
    }

    public ImagPagerUtil(Activity activity, String[] picarr) {
        mPicList = new ArrayList<>();
        for (int i = 0; i < picarr.length; i++) {
            mPicList.add(picarr[i]);
        }
        this.mActivity = activity;
        imageLoader = ImageLoader.getInstance();
        setOptions();
        init();
    }

    /**
     * 设置图片下方的文字
     * @param str
     */
    public void setContentText(String str) {
        if (!TextUtils.isEmpty(str)) {
            tv_content.setText(str);
        }
    }

    public void show() {
        dialog.show();
    }

    private void init() {
        dialog = new Dialog(mActivity, R.style.fullDialog);
        RelativeLayout contentView = (RelativeLayout) View.inflate(mActivity, R.layout.view_dialogpager_img, null);
        mViewPager = getView(contentView, R.id.view_pager);
        mLL_progress = getView(contentView, R.id.vdi_ll_progress);
        tv_loadingmsg = getView(contentView, R.id.tv_loadingmsg);
        tv_img_current_index = getView(contentView, R.id.tv_img_current_index);
        tv_img_count = getView(contentView, R.id.tv_img_count);
        tv_content = getView(contentView, R.id.tv_content);
        mLinearLayout = getView(contentView, R.id.ll_details_top_dot);
        dialog.setContentView(contentView);

        tv_img_count.setText(mPicList.size() + "");
        tv_img_current_index.setText("1");

        int size = mPicList.size();
        ArrayList<ImageView> imageViews = new ArrayList<>();
        ZoomImageView imageView = new ZoomImageView(mActivity);
        imageView.measure(0, 0);
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(screenWidth, display.getHeight());
        imageView.setLayoutParams(marginLayoutParams);
        imageView.setOnClickListener(new View.OnClickListener() {//如果不需要点击图片关闭的需求，可以去掉这个点击事件
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        for (int i = 0; i < size; i++) {
            imageViews.add(imageView);
        }
        mDotView=new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ImageView imageView1 = new ImageView(mActivity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 10;     //设置圆点相距距离
            params.rightMargin = 10;
            if (i == 0) {               //初始化为红点
                imageView1.setBackgroundResource(R.mipmap.icon_point_red);
            } else {
                imageView1.setBackgroundResource(R.mipmap.icon_point_gray);
            }
            mLinearLayout.addView(imageView1,params);
            mDotView.add(imageView1);
        }
        initViewPager(imageViews);
    }

    private void initViewPager(ArrayList<ImageView> list) {
        mViewPager.setOnPageChangeListener(new LazyViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
//                tv_img_current_index.setText("" + (position + 1));
                for (int i = 0; i < mPicList.size(); i++) {
                    if ((position % mPicList.size()) == i) {      //如果是当前的位置就设置为红点
                        mDotView.get(i).setBackgroundResource(R.mipmap.icon_point_red);
                    } else {
                        mDotView.get(i).setBackgroundResource(R.mipmap.icon_point_gray);
                    }
                }
            }
        });

        MyImagPagerAdapter myImagPagerAdapter = new MyImagPagerAdapter(list);
        mViewPager.setAdapter(myImagPagerAdapter);
    }

    class MyImagPagerAdapter extends PagerAdapter {
        ArrayList<ImageView> mList;

        public MyImagPagerAdapter(ArrayList<ImageView> mList) {
            this.mList = mList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mList.get(position);
            showPic(imageView, mPicList.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public int getCount() {
            if (null == mList || mList.size() <= 0) {
                return 0;
            }
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    protected void setOptions() {
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    private void showPic(ImageView imageView, String url) {
        imageView.setImageBitmap(null);
        mLL_progress.setVisibility(View.VISIBLE);
        imageLoader.displayImage(url, imageView, options, animateFirstListener, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i1) {
                float temp = (float) i / i1;
                int progress = (int) (temp * 100);
                if (null != tv_loadingmsg) {
                    tv_loadingmsg.setText(progress + "%");
                }
            }
        });

        dialog.show();
    }

    private class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            mLL_progress.setVisibility(View.GONE);
            tv_loadingmsg.setText("");
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                imageView.setImageBitmap(loadedImage);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static final <E extends View> E getView(View parent, int id) {
        try {
            return (E) parent.findViewById(id);
        } catch (ClassCastException ex) {
            Log.e("ImagPageUtil", "Could not cast View to concrete class \n" + ex.getMessage());
            throw ex;
        }
    }
}
