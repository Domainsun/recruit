package com.enzhi.recruit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enzhi.recruit.R;
import com.enzhi.recruit.base.BaseActivity;
import com.enzhi.recruit.bean.UsersBean;
import com.enzhi.recruit.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_sex)
    EditText etSex;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_company)
    EditText etCompany;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.et_job)
    EditText etJob;
    @BindView(R.id.btn_edit)
    Button btnEdit;

    UsersBean usersBean;

    public static void start(Context context) {
        Intent starter = new Intent(context, UserInfoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
         usersBean = SPUtil.getUserInfo();

        etSex.setText(usersBean.getSex());
        etAge.setText(usersBean.getAge());
        etCompany.setText(usersBean.getCompany());
        etJob.setText(usersBean.getJob());

        setClickable(false);

    }


    @OnClick({R.id.tv_back, R.id.btn_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_edit:

                if (btnEdit.getText().toString().equals("保存")) {

                    String age = etAge.getText().toString().trim();
                    String sex = etSex.getText().toString().trim();
                    String company = etCompany.getText().toString().trim();
                    String job = etJob.getText().toString().trim();
                    UsersBean user= new UsersBean(usersBean.getId(),usersBean.getAccount(),usersBean.getPassword(),sex,age,company,job,usersBean.getUserType());
                    SPUtil.editUserInfo(user);
                    setClickable(false);
                } else {
                    setClickable(true);
                }

                break;
        }
    }


    private void setClickable(Boolean clickable) {
        etSex.setEnabled(clickable);
        etAge.setEnabled(clickable);
        etCompany.setEnabled(clickable);
        etJob.setEnabled(clickable);
        if (clickable) {
            btnEdit.setText("保存");
        } else {
            btnEdit.setText("编辑");
        }

    }
}
