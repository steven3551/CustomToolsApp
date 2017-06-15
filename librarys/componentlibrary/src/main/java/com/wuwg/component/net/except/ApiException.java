package com.wuwg.component.net.except;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class ApiException extends Exception{

    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(String msg) {
        super(msg);
    }
}
