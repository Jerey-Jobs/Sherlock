package com.jerey.dblib.annotation;

/**
 * Created by Xiamin on 2017/7/4.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 修饰成员变量
 * 作用在类上
 * 作用在运行时间
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBField {
    String value();
}
