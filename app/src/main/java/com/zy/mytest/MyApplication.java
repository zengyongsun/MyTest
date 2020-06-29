package com.zy.mytest;

import android.app.Application;

import com.zy.mytest.utils.ObjectBox;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }
}
