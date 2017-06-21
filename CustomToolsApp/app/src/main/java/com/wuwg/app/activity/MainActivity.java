package com.wuwg.app.activity;

import android.content.Intent;
import android.view.View;

import com.wuwg.app.R;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_http_framework:
                startActivity(new Intent(mActivity, HttpFrameworkActivity.class));
                break;
        }
    }
}
