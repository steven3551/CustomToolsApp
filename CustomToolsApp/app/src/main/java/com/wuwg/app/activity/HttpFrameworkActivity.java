package com.wuwg.app.activity;

import android.view.View;
import android.widget.TextView;

import com.wuwg.app.R;
import com.wuwg.app.entity.WeatherInfo;
import com.wuwg.component.net.HttpCallBack;
import com.wuwg.component.net.HttpHelper;
import com.wuwg.custom.base.BaseActivity;

import java.util.HashMap;

/**
 * Created by wuwengao on 2017/6/21.
 */
public class HttpFrameworkActivity extends BaseActivity implements View.OnClickListener {

    private TextView txtHttpResult;
    private static final String url = "http://api.map.baidu.com/telematics/v3/weather?location=%E5%98%89%E5%85%B4&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_http_framework);
    }

    @Override
    protected void initView() {
        txtHttpResult = (TextView) findViewById(R.id.txt_http_result);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.btn_post).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
                HashMap<String, Object> params = new HashMap<>();
                HttpHelper.getHttpProcessor().post(url, params, new HttpCallBack<WeatherInfo>() {

                    @Override
                    protected void onSuccess(WeatherInfo result) {
                        txtHttpResult.setText(result.toString());
                    }
                });
                break;
        }
    }
}
