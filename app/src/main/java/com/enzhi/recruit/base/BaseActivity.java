package com.enzhi.recruit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.enzhi.recruit.R;
import com.gyf.barlibrary.ImmersionBar;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;
//    LoadingDialog loadingDialog = null;
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
        // 添加 Activity 到堆栈
        ActivityManager.getInstance().addActivity(this);
        setContentView(setLayoutId());
        unbinder = ButterKnife.bind(this);
        initImmersionBar();
        initView();
    }

    protected abstract int setLayoutId();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 清除堆栈的 Activity
//        dismissLoading();//dialog清空
        ActivityManager.getInstance().finishActivity(this);
        unbinder.unbind();
        OkHttpUtils.getInstance().cancelTag(this);//取消以Activity.this作为tag的请求
    }

    //**************************************************************************
    // * 界面样式
    //**************************************************************************

//    public void showLoading() {
//        if (!isFinishing()) {
//            if (loadingDialog == null) {
//                loadingDialog = new LoadingDialog(this, "加载中,请稍等...");
//            }
//            loadingDialog.show();
//        }
//    }
//
//    public void showLoading(String str) {
//        if (!isFinishing()) {
//            if (!StringUtils.isEmpty(str)) {
//                if (loadingDialog == null) {
//                    loadingDialog = new LoadingDialog(this, str);
//                }
//                loadingDialog.show();
//            }
//        }
//    }
//
//    public void dismissLoading() {
//        if (loadingDialog != null && loadingDialog.isShowing()) {
//            loadingDialog.dismiss();
//            loadingDialog = null;
//        }
//    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        //在BaseActivity里初始化
//        mImmersionBar = ImmersionBar.with(this);
////        mImmersionBar.titleBar(R.id.rl_title).init();
//        mImmersionBar.init();

        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.flymeOSStatusBarFontColor("#333333");
        mImmersionBar.statusBarDarkFont(true);//解决部分手机默认白色状态栏
        mImmersionBar.statusBarColor(R.color.colorPrimary);//解决部分手机默认白色状态栏
        mImmersionBar.init();
    }

}
