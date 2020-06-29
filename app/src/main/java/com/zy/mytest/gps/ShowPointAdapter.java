package com.zy.mytest.gps;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zy.mytest.R;
import com.zy.mytest.bean.GpsPoint;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowPointAdapter extends BaseQuickAdapter<GpsPoint, BaseViewHolder> {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public ShowPointAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GpsPoint gpsPoint) {
        baseViewHolder.setText(R.id.tvDesc, "描述：" + gpsPoint.desc);
        baseViewHolder.setText(R.id.tvla, "维度：" + gpsPoint.latitude);
        baseViewHolder.setText(R.id.tvlo, "经度：" + gpsPoint.longitude);
        baseViewHolder.setText(R.id.tvAccuracy, "经度：" + gpsPoint.accuracy + "米");
        baseViewHolder.setText(R.id.tvTime, toDate(gpsPoint.time));
    }

    private String toDate(long time) {
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }


}
