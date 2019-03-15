package com.enzhi.recruit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.enzhi.recruit.R;
import com.enzhi.recruit.base.BaseActivity;
import com.enzhi.recruit.bean.UsersBean;
import com.enzhi.recruit.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;


    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        tvBack.setVisibility(View.GONE);
        tvTitle.setText("登录");

    }


    @OnClick({R.id.tv_forgot_password, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forgot_password:
                RegisterActivity.start(this);
                break;
            case R.id.tv_login:
                String account = etUser.getText().toString();
                String password = etPassword.getText().toString();
                if (StringUtils.isEmpty(account)) {
                    ToastUtils.showShort("请输入账号");
                    return;
                }
                if (StringUtils.isEmpty(password)) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }

                UsersBean usersBean = SPUtil.login(account, password);

                if (null != usersBean) {
                    SPUtil.setUserInfo(usersBean); //缓存当前登录用户的信息
                    HomePageActivity.start(this);
                    finish();
                } else {
                    ToastUtils.showShort("账号或者密码错误");
                }
                break;
        }
    }
}
