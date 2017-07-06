package com.jerey.dblib;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.jerey.dblib.utils.DBLog;

import java.io.File;

/**
 * @author xiamin
 * @explain 数据库框架入口, 单列
 */

public class SherlockDatabase {
    private String sqliteDatebasePath;
    private SQLiteDatabase mSQLiteDatabase;
    private static volatile SherlockDatabase instance;

    private SherlockDatabase() {
        /** 新建数据库 */
        File file = new File(Environment.getExternalStorageDirectory(), "update");
        if (!file.exists()) {
            file.mkdirs();
        }
        sqliteDatebasePath = file.getAbsolutePath() + "/sherlock.db";
        DBLog.d(sqliteDatebasePath);
        openDatabase();
    }

    /**
     * T为dao子类，BaseDao持有M引用
     * @return
     */
    public synchronized <T> DaoHelper<T> getDaoHelper(Class<T> t) {
        DaoHelper<T> baseDao = null;
        baseDao = new DaoHelper();
        baseDao.init(t, mSQLiteDatabase);
        return baseDao;
    }


    /**
     * 开启数据库
     */
    private void openDatabase() {
        this.mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDatebasePath, null);
    }


    public SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }

    /**
     * @return
     */
    public static SherlockDatabase getInstance() {
        if (instance == null) {
            synchronized (SherlockDatabase.class) {
                if (instance == null) {
                    instance = new SherlockDatabase();
                }
            }
        }
        return instance;
    }
}
