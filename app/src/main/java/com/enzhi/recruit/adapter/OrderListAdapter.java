package com.enzhi.recruit.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.enzhi.recruit.R;
import com.enzhi.recruit.bean.CommentsBean;
import com.enzhi.recruit.bean.ResumesBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Data：2018/10/31-12:16
 * Author: domain
 */

public class OrderListAdapter extends BaseQuickAdapter<ResumesBean, BaseViewHolder> {

    public OrderListAdapter(int layoutResId, @Nullable List<ResumesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResumesBean item) {
        helper.setIsRecyclable(false);
        helper
                .setText(R.id.tv_job, item.getJob())
                .setText(R.id.tv_salary, item.getSalary())
                .setText(R.id.tv_school, item.getSchool())
                .setText(R.id.tv_description, item.getDescription());


        addViews(item, (LinearLayout) helper.getView(R.id.ll_comments));
    }


    private void addViews(ResumesBean item, LinearLayout root) {
        if (item.getComments() != null && item.getComments().size() > 0) {
            for (int i = 0; i < item.getComments().size(); i++) {
                CommentsBean commentsBean = item.getComments().get(i);

                View view = LayoutInflater.from(mContext).inflate(R.layout.item_add_comment, root, false);

                ViewHolder viewHolder = new ViewHolder(view);

                viewHolder.tvComment.setText(commentsBean.getId()+":"+commentsBean.getComment());

                root.addView(view);
            }
        }

    }

    static class ViewHolder {
        @BindView(R.id.tv_comment)
        TextView tvComment;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

