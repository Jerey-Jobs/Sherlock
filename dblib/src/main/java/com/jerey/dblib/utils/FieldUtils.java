package com.jerey.dblib.utils;

import java.lang.reflect.Field;

/**
 * Created by xiamin on 7/6/17.
 */

public class FieldUtils {

    public static String getTypeStringByField(Field field) {
        // 得到属性的类名
        String className = field.getType().getSimpleName();
        // 得到属性值
        if (className.equalsIgnoreCase("String")) {
            return "TEXT";
        } else if (className.equalsIgnoreCase("boolean")) {
            return "BOOLEAN";
        } else if (className.equalsIgnoreCase("int")) {
            return "INTEGER";
        } else if (className.equalsIgnoreCase("float")) {
            return "FLOAT";
        } else if (className.equalsIgnoreCase("double")) {
            return "DOUBLE";
        } else if (className.equalsIgnoreCase("Long")) {
            return "LONG";
        }
        return "TEXT";
    }

}
