package com.example.com.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.com.common.util.AppManager;
import com.example.com.common.util.AppUtils;
import com.example.com.common.util.LogUtils;
import com.example.com.common.util.ToastUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Stack;

import butterknife.ButterKnife;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 * Created by 71033 on 2017/10/9.
 */


public abstract class BaseActivity extends FragmentActivity {
    public static final int GRANTED = 1;
    public static final int SHOULD_SHOWREQUEST = 2;
    public static final int REFUSE = 3;

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = false;
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = false;
    /**
     * 状态栏字体深色模式
     */
    public boolean isDarkStatusBar = false;
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    /**
     * antivity栈
     **/
    private Stack<Activity> stack;

    protected RxPermissions rxPermissions;

    protected Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        try {
            super.setContentView(bindLayout());
            ButterKnife.bind(this);
            Bundle bundle = getIntent().getExtras();
            initParams(bundle);
            if (mAllowFullScreen) {
                this.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if (isSetStatusBar) {
                highApiEffects();
            }
            appContext = getApplicationContext();
            setStatusBarMode(isDarkStatusBar);
            setView(savedInstanceState);
            doBusiness(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void highApiEffects() {
        getWindow().getDecorView().setFitsSystemWindows(true);
        //透明状态栏 @顶部
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }


    /**
     * 设置状态栏字体颜色
     * Flag只有在使用了FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
     * 并且没有使用 FLAG_TRANSLUCENT_STATUS的时候才有效，也就是只有在状态栏全透明的时候才有效。
     *
     * @param bDark
     */
    public void setStatusBarMode(boolean bDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (bDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }


    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [初始化Bundle参数]
     *
     * @param params
     */
    public abstract void initParams(Bundle params);

    /**
     * [初始化页面]
     */
    public abstract void setView(Bundle savedInstanceState);

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishActivity(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    public void addActivity(Activity activity) {
        AppManager.getAppManager().addActivity(activity);
    }

    public void finishActivity(Activity activity) {
        AppManager.getAppManager().finishActivity(activity);
    }

    public void finishAllActivity() {
        ToastUtils.showShort(this, "token失效,请重新登录");
        AppManager.getAppManager().finishAllActivity();
    }

    /**
     * 获取APP版本号
     *
     * @return
     */
    public String getVersionName() {
        return AppUtils.getAppVersionName();
    }

    protected static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("是", okListener)
                .setNegativeButton("否", null)
                .create()
                .show();
    }


    /**
     * 请求权限
     *
     * @param listener
     * @param permission
     */
    @SuppressLint("CheckResult")
    public void reqeustPermission(final requestPermissionListener listener, String... permission) {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }
        rxPermissions
                .requestEach(permission)
                .subscribe(
                        new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {
                                    //授予权限
                                    listener.onNext(permission.name, GRANTED);
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // 拒绝权限
                                    listener.onNext(permission.name, SHOULD_SHOWREQUEST);
                                } else {
                                    // 拒绝权限并且不在询问
                                    listener.onNext(permission.name, REFUSE);
                                }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable t) {
                                LogUtils.e(t.toString());
                            }
                        },
                        new Action() {
                            @Override
                            public void run() {
                                listener.onComplet();
                            }
                        }
                );
    }


    /**
     * 请求权限回调
     */
    public interface requestPermissionListener {
        void onNext(String name, @PermissionState int state);

        void onComplet();
    }

    @IntDef({GRANTED, SHOULD_SHOWREQUEST, REFUSE})
    public @interface PermissionState {
    }


    /**
     * 显示加载数据对话框
     *
     * @return
     */
    public Dialog getLoadingDialog(Context context) {
        final Dialog mDialog = new Dialog(context, R.style.WoDeDialog);
        View mDialogContentView = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
        mDialog.setContentView(mDialogContentView);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(false);
        mDialog.getWindow().setGravity(Gravity.CENTER);
        return mDialog;
    }

}