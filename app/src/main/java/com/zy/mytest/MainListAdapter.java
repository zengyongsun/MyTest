package com.zy.mytest;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zy.mytest.bean.MainListBean;

import org.jetbrains.annotations.NotNull;

public class MainListAdapter extends BaseQuickAdapter<MainListBean, BaseViewHolder> {


    public MainListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, final MainListBean mainListBean) {
        TextView textView = baseViewHolder.getView(R.id.desc);
        textView.setText(mainListBean.desc);
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), mainListBean.clazz));
            }
        });
    }
}
