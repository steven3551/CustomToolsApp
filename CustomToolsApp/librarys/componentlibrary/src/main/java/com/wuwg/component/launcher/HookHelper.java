package com.wuwg.component.launcher;

import android.content.Context;

/**
 * Created by wuwengao on 2017/6/24.
 */
public class HookHelper {

    /**
     * 初始化
     * @param proxyActivity
     * @param context
     */
    public static void initHook(Class proxyActivity, Context context) {
        HookUtils hookUtils = new HookUtils(proxyActivity, context);
        hookUtils.hookAms();
        hookUtils.hookSystemHandler();
    }


}
