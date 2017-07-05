package com.jerey.dblib;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.jerey.dblib.annotation.DBFiled;
import com.jerey.dblib.annotation.DBTable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @param <T> dao类型
 */
public abstract class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase mSQLiteDatabase;

    /**
     * 只允许实例化一次
     */
    private boolean isInit = false;
    /**
     * 持有操作数据库表所对应的类型
     */
    private Class<T> entityClass;

    /**
     * 表名，通过注解拿
     */
    private String mTableName;

    /**
     * 维护着表名和成员变量名字的映射关系
     * Key 表名
     */
    private HashMap<String, Field> mCacheMap;

    /**
     * 初始化表
     * @param entity
     * @param sqLiteDatabase
     * @return
     */
    protected boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {
        if (!isInit) {
            this.mSQLiteDatabase = sqLiteDatabase;
            this.entityClass = entity;
            /**
             * 赋值表明
             */
            if (entity.getAnnotation(DBTable.class) == null) {
                this.mTableName = entity.getClass().getSimpleName();
            } else {
                this.mTableName = entity.getAnnotation(DBTable.class).value();
            }
            /**
             * 检测数据库是否打开
             */
            if (!mSQLiteDatabase.isOpen()) {
                return false;
            }
            /**
             * 检测表是否存在
             */
            if (!TextUtils.isEmpty(createTableSQL())) {
                mSQLiteDatabase.execSQL(createTableSQL());
            }

            /**
             * 初始化映射
             */
            initCacheMap();
            isInit = true;
        }
        return isInit;
    }

    /**
     * 初始化
     */
    private void initCacheMap() {
        mCacheMap = new HashMap<>();
        String sql = "select * from " + this.mTableName + " limit 1 , 0";

        Cursor cursor = mSQLiteDatabase.rawQuery(sql, null);
        /**
         * 表的列名数组
         */
        String[] columNames = cursor.getColumnNames();
        /**
         * 拿到Field名字
         */
        Field[] columFields = entityClass.getFields();

        for (Field field : columFields) {
            field.setAccessible(true);
        }

        try {
            for (String name : columNames) {
                Field clumeFiled = null;
                for (Field field : columFields) {
                    String fieldName = null;
                    /**
                     * 找field列名
                     */
                    if (field.getAnnotation(DBFiled.class) != null) {
                        fieldName = field.getAnnotation(DBFiled.class).value();
                    } else {
                        fieldName = field.getName();
                    }
                    /**
                     * 如果表的列名等于成员变量注解名字
                     */
                    if (name.equals(fieldName)) {
                        clumeFiled = field;
                        break;
                    }
                }

                if (clumeFiled != null) {
                    mCacheMap.put(name, clumeFiled);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }


    }

    @Override
    public Long insert(T entity) {
        Map<String, String> map = getValues(entity);

        ContentValues contentValues = getContentValues(map);

        Long result = mSQLiteDatabase.insert(mTableName,null,contentValues);

        return null;
    }

    protected ContentValues getContentValues(Map<String, String> map) {

        ContentValues values = new ContentValues();
        Set keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            if (value != null) {
                values.put(key, value);
            }
        }
        return values;
    }

    protected Map<String, String> getValues(T entity) {
        HashMap<String, String> ret = new HashMap<>();

        Iterator<Field> fieldIterator = mCacheMap.values().iterator();

        while (fieldIterator.hasNext()) {


            Field columField = fieldIterator.next();
            String cacheKey = null;
            String cacheValue = null;

            if (columField.getAnnotation(DBFiled.class) != null) {
                cacheKey = columField.getAnnotation(DBFiled.class).value();
            } else {
                cacheKey = columField.getName();
            }

            try {
                if (columField.get(entity.toString()) == null) {
                    continue;
                }
                cacheValue = columField.get(entity).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            ret.put(cacheKey, cacheValue);
        }


        return ret;
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


    protected abstract String createTableSQL();
}
