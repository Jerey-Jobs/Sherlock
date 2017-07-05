package com.jerey.dblib;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Xiamin on 2017/7/4.
 */

public class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase mSQLiteDatabase;

    protected boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {

        this.mSQLiteDatabase = sqLiteDatabase;

        return true;
    }

    @Override
    public Long insert(T entity) {
        return null;
    }

    @Override
    public int update(T entity, T where) {
        return 0;
    }

    @Override
    public int delete(T where) {
        return 0;
    }

    @Override
    public List<T> query(T where) {
        return null;
    }

    @Override
    public List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {
        return null;
    }

    @Override
    public List<T> query(String sql) {
        return null;
    }
}
