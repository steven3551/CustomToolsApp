package com.wuwg.component;

import android.content.Context;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class ComponentLibrary {

    public static Context mContext;

    public static void init(Context context){
        mContext = mContext.getApplicationContext();
    }

}
