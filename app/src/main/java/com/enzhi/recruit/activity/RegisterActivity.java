package com.enzhi.recruit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.enzhi.recruit.R;
import com.enzhi.recruit.base.BaseActivity;
import com.enzhi.recruit.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.rg)
    RadioGroup rg;
    public static void start(Context context) {
        Intent starter = new Intent(context, RegisterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tvTitle.setText("注册");
    }



    @OnClick({R.id.tv_back, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
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
                int type = rg.getCheckedRadioButtonId() == R.id.rb_type_find_job ? 1 : 0;
                SPUtil.register(account,password,type);
                LoginActivity.start(this);
                break;
        }
    }
}
