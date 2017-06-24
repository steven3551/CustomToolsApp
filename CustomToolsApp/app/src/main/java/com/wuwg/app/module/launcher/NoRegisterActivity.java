package com.wuwg.app.module.launcher;

import android.widget.TextView;

import com.wuwg.custom.base.BaseActivity;


/**
 * Created by wuwengao on 2017/6/24.
 */
public class NoRegisterActivity extends BaseActivity {

    @Override
    protected void setContentView() {
        TextView textView = new TextView(mContext);
        textView.setText("这是一个未注册的Activity");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
