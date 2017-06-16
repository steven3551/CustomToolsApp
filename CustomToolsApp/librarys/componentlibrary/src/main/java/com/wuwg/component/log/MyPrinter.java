package com.wuwg.component.log;

import android.text.TextUtils;
import android.util.Log;

import com.wuwg.component.log.parser.JsonParser;
import com.wuwg.component.log.parser.ObjectParser;
import com.wuwg.component.log.parser.Parser;
import com.wuwg.component.log.parser.UrlParser;
import com.wuwg.component.log.parser.XmlParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class MyPrinter implements Printer {

    public static final int VERBOSE = 2;

    public static final int DEBUG = 3;

    public static final int INFO = 4;

    public static final int WARN = 5;

    public static final int ERROR = 6;

    public static final int ASSERT = 7;

    protected static String MYLOG_CLASSNAME;

    protected static final int CHUNK_SIZE = 4000;

    protected L.Builder logBuilder;

    protected MyPrinter(){
        MYLOG_CLASSNAME = getClass().getPackage().getName();
    }

    @Override
    public L.Builder init() {
        if(logBuilder == null){
            logBuilder = new L.Builder();
        }
        if(logBuilder.parserList  == null){
            logBuilder.parserList = new LinkedList<>();
            logBuilder.parserList.add(new JsonParser());
            logBuilder.parserList.add(new XmlParser());
            logBuilder.parserList.add(new UrlParser());
            logBuilder.parserList.add(new ObjectParser());
        }
        if(TextUtils.isEmpty(logBuilder.tag)){
            logBuilder.tag = L.LOG_TAG_DEFUALT;
        }
        return logBuilder;
    }

    @Override
    public L.Builder getLogBuilder() {
        return logBuilder;
    }

    @Override
    public void setLastMethodClassName(String className) {
        if(TextUtils.isEmpty(className)){
            return;
        }
        MYLOG_CLASSNAME = className;
    }

    @Override
    public void d(Object... args) {
        log(DEBUG, args);
    }

    @Override
    public void e(Object... args) {
        log(ERROR, args);
    }

    @Override
    public void e(Throwable throwable, Object... args) {
        if(args == null){
            return;
        }
        List<Object> list = new ArrayList<>();
        for (int i=0; i<args.length; i++) {
            list.add(args[i]);
        }
        list.add(Log.getStackTraceString(throwable));
        log(ERROR, list.toArray());
    }

    @Override
    public void w(Object... args) {
        log(WARN, args);
    }

    @Override
    public void i(Object... args) {
        log(INFO, args);
    }

    @Override
    public void v(Object... args) {
        log(VERBOSE, args);
    }

    @Override
    public void wtf(Object... args) {
        log(ASSERT, args);
    }

    protected synchronized void log(int logType, Object... args) {

        if(logBuilder == null){
            return;
        }
        if(logBuilder.printType == null){
            logBuilder.printType = L.PRINT.MYLOG;
        }

        if(L.PRINT.NONE == logBuilder.printType){
            return;
        } else if(L.PRINT.SYSTEM == logBuilder.printType){
            print(logType, logBuilder.tag, getContent(args));
            return;
        }
        StringBuilder builder = new StringBuilder();
        if(L.PRINT.MYLOG == logBuilder.printType){
            builder.append(getMethod() + "\n");
        }
        builder.append(getContent(args));
        logChunk(logType, logBuilder.tag, builder.toString());
    }

    protected String getMethod() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        int stackOffset = 0;
        for (int i = trace.length - 1; i >= 0; i--) {
            if (trace[i].getClassName().indexOf(MYLOG_CLASSNAME) >= 0) {
                break;
            }
            stackOffset = i;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("$ ")
                .append("(")
                .append(trace[stackOffset].getFileName())
                .append(":")
                .append(trace[stackOffset].getLineNumber())
                .append(")")
                .append(" Method: " + trace[stackOffset].getMethodName())
                .append(" Thread: " + Thread.currentThread().getName());
        return builder.toString();
    }

    protected String getContent(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (Object object : args) {
            if (object == null) {
                continue;
            }
            String content = null;
            if (logBuilder.parserList != null
                    && L.PRINT.NONE != logBuilder.printType
                    && L.PRINT.SYSTEM != logBuilder.printType) {
                for (Parser parser : logBuilder.parserList) {
                    content = parser.parse(object);
                    if (!TextUtils.isEmpty(content)) {
                        break;
                    }
                }
            }
            builder.append(TextUtils.isEmpty(content) ? object.toString() : content);
            builder.append("\n");
        }
        return builder.toString();
    }

    protected void logChunk(int logType, String tag, String chunk) {
        byte[] bytes = chunk.getBytes();
        int length = bytes.length;
        if (length <= CHUNK_SIZE) {
            printChunk(logType, tag, chunk);
            return;
        }
        for (int i = 0; i < length; i += CHUNK_SIZE) {
            int count = Math.min(length - i, CHUNK_SIZE);
            //create a new String with system's default charset (which is UTF-8 for Android)
            printChunk(logType, tag, new String(bytes, i, count));
        }
    }

    protected void printChunk(int logType, String tag, String chunk) {
        String[] lines = chunk.split(System.getProperty("line.separator"));
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            builder.append(line + "\n");
        }
        print(logType, tag, chunk);
    }

    protected void print(int logType, String tag, String chunk) {
        switch (logType) {
            case ERROR:
                Log.e(tag, chunk);
                break;
            case INFO:
                Log.i(tag, chunk);
                break;
            case VERBOSE:
                Log.v(tag, chunk);
                break;
            case WARN:
                Log.w(tag, chunk);
                break;
            case ASSERT:
                Log.wtf(tag, chunk);
                break;
            case DEBUG:
                // Fall through, log debug by default
            default:
                Log.d(tag, chunk);
                break;
        }
    }
}
