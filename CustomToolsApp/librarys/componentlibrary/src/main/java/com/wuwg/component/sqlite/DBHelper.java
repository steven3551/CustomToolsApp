package com.wuwg.component.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wuwengao on 2017/6/21.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context){
        this(context, DB_NAME, null, DB_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
