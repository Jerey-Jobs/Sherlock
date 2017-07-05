package com.jerey.sherlockhttp.dbtest;

import com.jerey.dblib.annotation.DBFiled;
import com.jerey.dblib.annotation.DBTable;

/**
 * Created by Xiamin on 2017/7/5.
 */

@DBTable("tb_user_xiamin")
public class User {
    @DBFiled("user_name")
    public String name;
    @DBFiled("user_password")
    public String password;
}
