package com.enzhi.recruit.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.enzhi.recruit.R;
import com.enzhi.recruit.base.BaseActivity;
import com.enzhi.recruit.fragment.HomeFragment;
import com.enzhi.recruit.fragment.SettingFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HomePageActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    private AdapterFragmentPager adapter;

    private int colorTextGray, colorTextBlue;

    public static void start(Context context) {
        Intent starter = new Intent(context, HomePageActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void initView() {
        colorTextBlue = ContextCompat.getColor(this, R.color.colorPrimary);
        colorTextGray = ContextCompat.getColor(this, R.color.gray_text_assist1);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("home"));
        fragments.add(SettingFragment.newInstance("setting"));

        adapter = new AdapterFragmentPager(
                getSupportFragmentManager(),
                fragments
        );
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        onPageSelected(0);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        setBottomText(i);
    }



    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @OnClick({R.id.tv_home,  R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_setting:
                viewPager.setCurrentItem(1);
        }
    }

    private void setBottomText(int currentIndex){
        switch (currentIndex) {
            case 0:
                tvHome.setTextColor(colorTextBlue);
                tvHome.setCompoundDrawablesWithIntrinsicBounds(null,
                        ContextCompat.getDrawable(this, R.mipmap.ic_home_records),
                        null, null
                );

                tvSetting.setTextColor(colorTextGray);
                tvSetting.setCompoundDrawablesWithIntrinsicBounds(null,
                        ContextCompat.getDrawable(this, R.mipmap.ic_home_setting_gray),
                        null, null
                );
                break;
            case 1:
                tvSetting.setTextColor(colorTextBlue);
                tvSetting.setCompoundDrawablesWithIntrinsicBounds(null,
                        ContextCompat.getDrawable(this, R.mipmap.ic_home_setting),
                        null, null
                );
                tvHome.setTextColor(colorTextGray);
                tvHome.setCompoundDrawablesWithIntrinsicBounds(null,
                        ContextCompat.getDrawable(this, R.mipmap.ic_home_records_gray),
                        null, null
                );

                break;
        }
    }

}
