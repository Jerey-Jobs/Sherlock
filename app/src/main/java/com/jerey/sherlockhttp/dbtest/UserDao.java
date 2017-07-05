package com.jerey.sherlockhttp.dbtest;

import com.jerey.dblib.BaseDao;

/**
 * Created by Xiamin on 2017/7/5.
 */

public class UserDao extends BaseDao<User> {
    @Override
    protected String createTableSQL() {
        return "create table if not exists tb_user( name TEXT, password TEXT);";
    }

}
