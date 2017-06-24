package com.wuwg.app;

import android.app.Application;

import com.wuwg.app.module.launcher.ProxyActivity;
import com.wuwg.component.launcher.HookHelper;
import com.wuwg.component.net.HttpHelper;
import com.wuwg.component.net.VolleyHttpProcessor;

/**
 * Created by wuwengao on 2017/6/21.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 隔离化请求框架
        HttpHelper.init(new VolleyHttpProcessor(getApplicationContext()));

        // 通过Hook技术启动未注册的Activity
        HookHelper.initHook(ProxyActivity.class, this);
    }

}
