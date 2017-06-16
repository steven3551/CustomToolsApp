package com.wuwg.custom.base;

import android.app.Application;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class BaseApp extends Application{

    private static BaseApp mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static BaseApp getIntstance() {
        return mApplication;
    }

}
