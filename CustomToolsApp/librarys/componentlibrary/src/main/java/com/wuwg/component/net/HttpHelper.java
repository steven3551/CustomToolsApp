package com.wuwg.component.net;

/**
 * Created by wuwengao on 2017/6/21.
 */
public class HttpHelper {

    private static IHttpProcessor httpProcessor;

    public static void init(IHttpProcessor processor) {
        httpProcessor = processor;
    }

    public static IHttpProcessor getHttpProcessor() {
        return httpProcessor;
    }

}
