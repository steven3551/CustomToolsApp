package com.wuwg.component.net;

import java.util.HashMap;

/**
 * Created by wuwengao on 2017/6/21.
 */
public interface IHttpProcessor {

    void post(String url, HashMap<String, Object> params, ICallBack callBack);


    void get(String url, HashMap<String, Object> params, ICallBack callBack);

}
