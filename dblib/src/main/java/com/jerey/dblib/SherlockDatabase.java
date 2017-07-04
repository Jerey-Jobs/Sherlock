package com.jerey.dblib;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Created by Xiamin on 2017/7/4.
 */

public class SherlockDatabase {
    private String sqliteDatebasePath;
    private SQLiteDatabase mSQLiteDatabase;
    private static SherlockDatabase instance;

    private SherlockDatabase() {
        File file = new File(Environment.getExternalStorageDirectory(), "update");
        if (!file.exists()) {
            file.mkdirs();
        }
        sqliteDatebasePath = file.getAbsolutePath() + "/sherlock.db";
        openDatabase();
    }

    /**
     * T为dao子类，BaseDao持有M引用
     * @param tClass
     * @param mClass
     * @param <T>
     * @param <M>
     * @return
     */
    public synchronized <T extends BaseDao<M>, M> T getDataBaseHelper(Class<T> tClass, Class<M> mClass) {
        BaseDao baseDao = null;

        tClass.getClass().newInstance();
    }


    private void openDatabase() {
        this.mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDatebasePath, null);
    }


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
