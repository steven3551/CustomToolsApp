package com.wuwg.app.entity;

/**
 * Created by wuwengao on 2017/6/21.
 */
public class WeatherInfo {


    /**
     * status : 201
     * message : 1111
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
