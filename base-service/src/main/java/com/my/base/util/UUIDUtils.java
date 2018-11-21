package com.my.base.util;

import java.util.UUID;

/**
 * UUIDUtils，生成UUID的工具类
 */
public class UUIDUtils {

    private UUIDUtils() {

    }

    /**
     * 随机生成UUID
     * @return
     */
    public static synchronized String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 根据字符串生成固定UUID
     * @param name
     * @return
     */
    public static synchronized String getUUID(String name) {
        return UUID.nameUUIDFromBytes(name.getBytes()).toString().replace("-", "");
    }

}
