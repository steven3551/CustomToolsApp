package com.wuwg.app;

import android.app.Application;

import com.wuwg.component.net.HttpHelper;
import com.wuwg.component.net.VolleyHttpProcessor;

/**
 * Created by wuwengao on 2017/6/21.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.init(new VolleyHttpProcessor(getApplicationContext()));
    }

}
