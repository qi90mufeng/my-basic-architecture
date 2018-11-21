package com.my.base.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 脱敏工具类
 */
public class DesensitiveUtils {

    private DesensitiveUtils() {

    }

    /**
     * 密码脱敏
     */
    public static final String pswDesensitization(String msg) {

        StringBuffer result = new StringBuffer();
        if (StringUtils.isNotBlank(msg)) {
            for (int i = 0; i < msg.length(); i++) {
                result.append('*');
            }
        }
        return result.toString();
    }

}
