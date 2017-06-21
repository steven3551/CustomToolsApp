package com.wuwg.component.sqlite;

import android.content.Context;

/**
 * Created by wuwengao on 2017/6/21.
 */
public abstract class AbstractBaseDao<T> implements IBaseDao<T> {

    private DBHelper dbHelper;

    private void init(Context context){
        dbHelper = new DBHelper(context);
    }

    @Override
    public void insert(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void query() {

    }
}
