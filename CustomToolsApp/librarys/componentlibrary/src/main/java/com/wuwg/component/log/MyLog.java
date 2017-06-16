package com.wuwg.component.log;

import com.wuwg.component.util.SystemUtil;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class MyLog {

    static {
        L.setLastMethodClassName("com.wuwg.component.log.MyLog");
    }

    private static boolean check(Object... args) {
        if(args == null || args.length == 0) {
            return false;
        }
        if(!SystemUtil.isCanLog()) {
            return false;
        }
        return true;
    }

    public static void d(Object... args) {
        if(!check(args)) {
            return;
        }
        L.d(args);
    }

    public static void e(Object... args) {
        if(!check(args)) {
            return;
        }
        L.e(args);
    }

    public static void e(Throwable throwable, Object... args) {
        if(!check(args)) {
            return;
        }
        L.e(throwable, args);
    }

    public static void w(Object... args) {
        if(!check(args)) {
            return;
        }
        L.w(args);
    }

    public static void i(Object... args) {
        if(!check(args)) {
            return;
        }
        L.i(args);
    }

    public static void v(Object... args) {
        if(!check(args)) {
            return;
        }
        L.v(args);
    }

    public static void wtf(Object... args) {
        if(!check(args)) {
            return;
        }
        L.wtf(args);
    }

}
