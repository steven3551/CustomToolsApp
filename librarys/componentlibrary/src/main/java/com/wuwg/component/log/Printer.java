package com.wuwg.component.log;

/**
 * Created by wuwengao on 2017/6/15.
 */
public interface Printer {

    L.Builder init();

    L.Builder getLogBuilder();

    void setLastMethodClassName(String className);

    void d(Object... args);

    void e(Object... args);

    void e(Throwable throwable, Object... args);

    void w(Object... args);

    void i(Object... args);

    void v(Object... args);

    void wtf(Object... args);

}
