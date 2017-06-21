package com.wuwg.component.net;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by wuwengao on 2017/6/21.
 */
public abstract class HttpCallBack<T> implements ICallBack {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> clz = getGenericClass();
        T entity = (T) gson.fromJson(result, clz);
        onSuccess(entity);
    }

    protected abstract void onSuccess(T result);

    @Override
    public void onFail(String msg) {

    }

    private Class<?> getGenericClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class) params[0];
    }
}
