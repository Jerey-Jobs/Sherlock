package com.jerey.sherlockhttp.dbtest;

import com.jerey.dblib.annotation.DBField;
import com.jerey.dblib.annotation.DBTable;
import com.jerey.dblib.annotation.PrimaryKey;

/**
 * Created by Xiamin on 2017/7/5.
 */

@DBTable("tb_xiamin2")
public class User {
    @PrimaryKey
    public int id;
    @DBField("user_name")
    public String name;
    @DBField("user_password")
    public String password;
    @DBField("user_score")
    public float score;
    @DBField("user_boolean")
    public boolean isOk;
    @DBField("user_money")
    public double money;
}
