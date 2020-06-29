package com.zy.mytest.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class GpsPoint {

    @Id
    public long id;

    public float accuracy;//精确度，以密为单位
    public double altitude;//获取海拔高度
    public double longitude;//经度
    public double latitude;//纬度
    public float speed;//速度
    public long time;//时间
    public String desc;
}
