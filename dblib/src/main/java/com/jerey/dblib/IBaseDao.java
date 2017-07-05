package com.jerey.dblib;

import java.util.List;


/**
 * Dao接口,提供增删改查接口
 * @param <T>
 */
public interface IBaseDao<T> {
    /**
     * 插入数据库
     * @param entity
     * @return
     */
    Long insert(T entity);

    /**
     * @param entity
     * @param where
     * @return
     */
    int update(T entity, T where);

    /**
     * 删除数据
     * @param where
     * @return
     */
    int delete(T where);

    /**
     * 查询数据
     */
    List<T> query(T where);

    /**
     * @param where
     * @param orderBy
     * @param startIndex
     * @param limit
     * @return
     */
    List<T> query(T where, String orderBy, Integer startIndex, Integer limit);

    /**
     * @param sql
     * @return
     */
    List<T> query(String sql);
}
