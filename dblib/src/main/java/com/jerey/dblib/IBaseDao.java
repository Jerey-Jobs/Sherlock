package com.jerey.dblib;

import java.util.List;

/**
 * Created by Xiamin on 2017/7/4.
 */

public interface IBaseDao<T> {
    /**
     * 插入数据库
     * @param entity
     * @return
     */
    Long inset(T entity);

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


    List<T> query(T where, String orderBy, Integer startIndex, Integer limit);


    List<T> query(String sql);
}
