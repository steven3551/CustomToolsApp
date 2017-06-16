package com.wuwg.component.log;

import android.text.TextUtils;

import com.wuwg.component.log.parser.Parser;

import java.util.LinkedList;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class L {

    public static final String LOG_TAG_DEFUALT = "MyLog";

    public enum PRINT {
        /**
         * 不打印Log
         */
        NONE,
        /**
         * 使用系统默认的Log
         */
        SYSTEM,
        /**
         * 使用MyLog格式打印
         */
        MYLOG,
        /**
         * 使用MyLog格式化打印，但是只打印内容，不打印方法和堆栈信息
         */
        MY_LOG_NOMETHOD
    }

    private static Printer mPrinter;

    public static void d(Object... args) {
        getPrinter().d(args);
    }

    public static void e(Object... args) {
        getPrinter().e(args);
    }

    public static void e(Throwable throwable, Object... args) {
        getPrinter().e(throwable, args);
    }

    public static void w(Object... args) {
        getPrinter().w(args);
    }

    public static void i(Object... args) {
        getPrinter().i(args);
    }

    public static void v(Object... args) {
        getPrinter().v(args);
    }

    public static void wtf(Object... args) {
        getPrinter().wtf(args);
    }

    public static Printer getPrinter() {
        if (mPrinter != null) {
            return mPrinter;
        }
        mPrinter = new MyPrinter();
        mPrinter.init();
        return mPrinter;
    }

    public static Builder initPrinter(Printer printer) {
        if (printer != null) {
            mPrinter = printer;
            return mPrinter.init();
        }
        mPrinter = mPrinter == null ? new MyPrinter() : mPrinter;
        return mPrinter.init();
    }

    public static void setTag(String tag) {
        getPrinter().getLogBuilder().setTag(tag);
    }

    public static void setPrint(PRINT printType) {
        getPrinter().getLogBuilder().setPrint(printType);
    }

    public static void setParserList(Parser... parsers) {
        getPrinter().getLogBuilder().setParserList(parsers);
    }

    public static void setLastMethodClassName(String className) {
        getPrinter().setLastMethodClassName(className);
    }

    public static class Builder {

        public Builder setTag(String tag) {
            this.tag = tag;
            if (TextUtils.isEmpty(tag)) {
                this.tag = LOG_TAG_DEFUALT;
            }
            return this;
        }

        public Builder setPrint(PRINT printType) {
            this.printType = printType;
            return this;
        }

        public Builder setParserList(Parser... parsers) {
            if (this.parserList == null) {
                this.parserList = new LinkedList<>();
            }
            this.parserList.clear();
            if (parsers == null) {
                return this;
            }
            for (Parser parser : parsers) {
                this.parserList.add(parser);
            }
            return this;
        }

        public String tag;
        public PRINT printType = PRINT.MYLOG;
        public LinkedList<Parser> parserList;

        public Builder() {
        }
    }
}
