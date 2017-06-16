package com.wuwg.component.util;


import com.wuwg.component.BuildConfig;

/**
 * Created by sj on 2016/11/25.
 */
public class SystemUtil {

    /**
     * 是否支持log输出
     */
    public static boolean isDeveloperLog = false;

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    public static boolean isCanLog() {
        return BuildConfig.DEBUG || isDeveloperLog;
    }

    public static void updateDeveloperLogControl(boolean b) {
        isDeveloperLog = b;
    }
}
