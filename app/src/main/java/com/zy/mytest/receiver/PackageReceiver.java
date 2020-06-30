package com.zy.mytest.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by aiyang on 2018/3/8.
 * <p>
 * 监听应用安装
 */
public class PackageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        /*
         * 获取（安装/替换/卸载）应用的 信息
         */
        String packages = intent.getDataString();
        packages = packages.split(":")[1];

        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            Log.d("PackageReceiver", packages + "应用程序安装了，需要进行该应用安全扫描吗");
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            Log.d("PackageReceiver", packages + "应用程序卸载了，需要清理垃圾有缓存吗");
        } else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
            Log.d("PackageReceiver", packages + "应用程序覆盖了");
        }
    }
}

