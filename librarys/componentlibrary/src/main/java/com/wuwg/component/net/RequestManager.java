package com.wuwg.component.net;

import java.util.HashMap;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class RequestManager {

    protected static HashMap<Class, Object> sRequestManager = new HashMap<>();

    public static <T> T getRequest(Class<T> clazz, String baseUrl) {
        T t = (T) sRequestManager.get(clazz);
        if (t == null) {
            t = RetrofitClient.createApi(clazz, baseUrl);
            sRequestManager.put(clazz, t);
        }
        return t;
    }

}
