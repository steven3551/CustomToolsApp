package com.wuwg.custom.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by zm on 2017/6/7.
 */

public class MyApplication extends Application{
    private static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MyApplication getIntstance() {
        return mApplication;
    }
}
