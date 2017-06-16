package com.wuwg.component.net.except;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class ServerException extends RuntimeException {

    public int code;
    public String message;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 权限失效
     * @return
     */
    public boolean isForbidden() {
        if(code == -2) {
            return true;
        }
        return false;
    }
}
