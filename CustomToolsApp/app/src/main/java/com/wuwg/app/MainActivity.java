package com.wuwg.app;

import android.content.Intent;
import android.view.View;

import com.wuwg.app.module.advancedui.SlidingCardActivity;
import com.wuwg.app.module.animator.AnimatorActivity;
import com.wuwg.app.module.compress.PhotoCompressActivity;
import com.wuwg.app.module.http.HttpActivity;
import com.wuwg.app.module.launcher.HookActivity;
import com.wuwg.custom.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.btn_http_framework).setOnClickListener(this);
        findViewById(R.id.btn_animator_framework).setOnClickListener(this);
        findViewById(R.id.btn_photo_compress).setOnClickListener(this);
        findViewById(R.id.btn_sliding_card).setOnClickListener(this);
        findViewById(R.id.btn_hook_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_http_framework:
                startActivity(new Intent(mActivity, HttpActivity.class));
                break;
            case R.id.btn_animator_framework:
                startActivity(new Intent(mActivity, AnimatorActivity.class));
                break;
            case R.id.btn_photo_compress:
                startActivity(new Intent(mActivity, PhotoCompressActivity.class));
                break;
            case R.id.btn_sliding_card:
                startActivity(new Intent(mActivity, SlidingCardActivity.class));
                break;
            case R.id.btn_hook_activity:
                startActivity(new Intent(mActivity, HookActivity.class));
                break;
        }
    }
}
