/**
 * 
 */
package com.my.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObjectUtils {
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            System.out.println("属性不存在");
            return null;
        }
    }

    public static void setFieldValueByName(String fieldName, Object o, Object[] objs) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String setter = "set" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(setter, new Class[] {});
            method.invoke(o, objs);
        } catch (Exception e) {
            System.out.println("属性不存在");
        }
    }

    public static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    public static Object[] getFiledValues(Object o) {
        String[] fieldNames = ObjectUtils.getFiledName(o);
        Object[] value = new Object[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            value[i] = ObjectUtils.getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }

}
