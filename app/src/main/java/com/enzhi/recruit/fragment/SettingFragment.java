package com.enzhi.recruit.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.enzhi.recruit.R;
import com.enzhi.recruit.activity.LoginActivity;
import com.enzhi.recruit.activity.ReleaseActivity;
import com.enzhi.recruit.activity.UserInfoActivity;
import com.enzhi.recruit.base.BaseFragment;


import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class SettingFragment extends BaseFragment {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tx_version)
    TextView txVersion;
    @BindView(R.id.tx_version_update)
    TextView txVersionUpdate;
    @BindView(R.id.tx_modify_password)
    TextView txModifyPassword;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    public static SettingFragment newInstance(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView() {
        tvBack.setVisibility(View.GONE);
        tvTitle.setText("设置");
        txVersion.setText(AppUtils.getAppVersionName());
        txVersionUpdate.setText("当前已经是最新版本");
    }


    @OnClick({R.id.tx_modify_password, R.id.btn_logout, R.id.tv_user_info,R.id.tv_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tx_modify_password:
                break;
            case R.id.btn_logout:
                LoginActivity.start(getContext());
                getActivity().finish();
                break;
            case R.id.tv_user_info:
                UserInfoActivity.start(getContext());
                break;

            case R.id.tv_release:
                ReleaseActivity.start(getContext());
                break;
        }
    }


}
