package com.wuwg.app.module.launcher;

import android.content.Intent;
import android.view.View;

import com.wuwg.app.R;
import com.wuwg.custom.base.BaseActivity;

/**
 * Created by wuwengao on 2017/6/24.
 */
public class HookActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_hook);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.btn_hook).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_hook:
                startActivity(new Intent(mActivity, NoRegisterActivity.class));
                break;
        }
    }
}
