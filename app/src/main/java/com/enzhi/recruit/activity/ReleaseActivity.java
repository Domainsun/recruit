package com.enzhi.recruit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.enzhi.recruit.R;
import com.enzhi.recruit.base.BaseActivity;
import com.enzhi.recruit.bean.ResumesBean;
import com.enzhi.recruit.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReleaseActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.et_job)
    EditText etJob;
    @BindView(R.id.et_school)
    EditText etSchool;
    @BindView(R.id.et_education)
    EditText etEducation;
    @BindView(R.id.tv_salary)
    TextView tvSalary;
    @BindView(R.id.et_salary)
    EditText etSalary;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    @BindView(R.id.et_description)
    EditText etDescription;

    public static void start(Context context) {
        Intent starter = new Intent(context, ReleaseActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_release;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.tv_back, R.id.tv_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                break;
            case R.id.tv_release:

                String school = etSchool.getText().toString().trim();
                String education = etEducation.getText().toString().trim();
                String job = etJob.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                String salary = etSalary.getText().toString().trim();


                ResumesBean resumesBean = new ResumesBean(school,education,job,description,salary,null);
                SPUtil.addResume(resumesBean);

                break;
        }
    }

}
