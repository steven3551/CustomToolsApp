package com.wuwg.custom.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by wuwengao on 2017/6/15.
 */
public abstract class BaseActivity extends Activity {

    protected Activity mActivity;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        mContext = mActivity;

        setContentView();
        initView();
        initData();
        initEvent();
    }

    protected abstract void setContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
