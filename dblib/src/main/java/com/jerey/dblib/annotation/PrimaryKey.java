package com.jerey.dblib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 主键标识
 * @author xiamin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface PrimaryKey {
}
