package com.enzhi.recruit.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enzhi.recruit.R;
import com.enzhi.recruit.adapter.OrderListAdapter;
import com.enzhi.recruit.base.BaseFragment;
import com.enzhi.recruit.bean.CommentsBean;
import com.enzhi.recruit.bean.ResumesBean;
import com.enzhi.recruit.bean.UsersBean;
import com.enzhi.recruit.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    OrderListAdapter missionListAdapter = null;

    public static HomeFragment newInstance(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        tvBack.setVisibility(View.GONE);


        UsersBean usersBean = SPUtil.getUserInfo();
        if (usersBean.getUserType() == 0) { //公司
            tvTitle.setText("简历列表");
        } else {
            tvTitle.setText("招聘列表");
        }

        missionListAdapter = new OrderListAdapter(R.layout.item_order_list, null);
        LinearLayoutManager layoutManage = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManage);
        recycleView.setAdapter(missionListAdapter);

        List<ResumesBean> resumesBeanList = new ArrayList<>();

        for (int i = 0; i <15 ; i++) {

            List<CommentsBean> commentsBeanList = new ArrayList<>();
            for (int j = 0; j <5 ; j++) {
                CommentsBean commentsBean = new CommentsBean("13097340262","欢迎来实习");
                commentsBeanList.add(commentsBean);
            }
            ResumesBean resumesBean = new ResumesBean("南昌大学","本科","安卓开发工程师","熟悉mvvm,mvc","10k",commentsBeanList);
            resumesBeanList.add(resumesBean);
        }

        missionListAdapter.setNewData(resumesBeanList);

    }


}
