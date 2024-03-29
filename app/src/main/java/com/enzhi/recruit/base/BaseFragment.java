package com.enzhi.recruit.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 实现懒加载的baseFragment，通过重写lazyLoad方法来加载数据
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    Unbinder unbinder;
//    LoadingDialog loadingDialog = null;


    private boolean isViewCreated;//视图是否已经创建
    private boolean isUiVisible;//该fragment是否对用户可见


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(setLayoutId(), container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        LogUtils.d("isvisiible:" + isVisibleToUser);
        if (isVisibleToUser) {
            isUiVisible = true;
            lazyLoad();
        } else {
            isUiVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,
        // 并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUiVisible) {
            lazyLoadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUiVisible = false;

        }
    }

    protected abstract void lazyLoadData();


    //获取布局文件
    protected abstract int setLayoutId();

    //初始化view
    protected abstract void initView();


//    public void showLoading() {
//        if (getActivity() != null && (!getActivity().isFinishing())) {
//            if (loadingDialog == null) {
//                loadingDialog = new LoadingDialog(getContext(), "加载中,请稍等...");
//            }
//            loadingDialog.show();
//        }
//    }
//
//    public void showLoading(String str) {
//        if (!getActivity().isFinishing()) {
//            if (!StringUtils.isEmpty(str)) {
//                if (loadingDialog == null) {
//                    loadingDialog = new LoadingDialog(getContext(), str);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isViewCreated = false;
        isUiVisible = false;
    }

}
