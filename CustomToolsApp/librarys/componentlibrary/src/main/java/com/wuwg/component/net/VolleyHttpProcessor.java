package com.wuwg.component.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

/**
 * Created by wuwengao on 2017/6/21.
 */
public class VolleyHttpProcessor implements IHttpProcessor {

    private RequestQueue mRequestQueue;
    private Context context;

    public VolleyHttpProcessor(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }

    @Override
    public void post(String url, HashMap<String, Object> params, final ICallBack callBack) {
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFail(error.getMessage());
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public void get(String url, HashMap<String, Object> params, final ICallBack callBack) {
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFail(error.getMessage());
            }
        });
        mRequestQueue.add(request);
    }
}
