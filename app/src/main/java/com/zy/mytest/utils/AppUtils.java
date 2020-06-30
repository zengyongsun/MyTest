package com.zy.mytest.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class AppUtils {

    /**
     * 静默安装App
     *
     * @param apkPath apk路径
     * @return 是否安装成功
     */
    public static boolean installApp(String apkPath) {
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();
        try {
            process = new ProcessBuilder("pm", "install", "-r", apkPath).start();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            if (process != null) {
                process.destroy();
            }
        }
        //如果含有“success”单词则认为安装成功
        return successMsg.toString().equalsIgnoreCase("success");
    }

    /**
     * 静默卸载App
     *
     * @param packageName 包名
     * @return 是否卸载成功
     */
    public static boolean uninstallApp(String packageName) {
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();
        try {
            process = new ProcessBuilder("pm", "uninstall", packageName).start();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }
        //如果含有“success”单词则认为卸载成功
        return successMsg.toString().equalsIgnoreCase("success");
    }

    /**
     * 判断应用是否存在
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 是否存在
     */
    private boolean appExist(Context context, String packageName) {
        try {
            List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(0);
            for (PackageInfo packageInfo : packageInfoList) {
                if (packageInfo.packageName.equalsIgnoreCase(packageName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
