package com.my.base.util;

import java.util.Collection;
import java.util.Map;

import com.my.base.common.ErrorCodeEnum;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.my.base.exception.BizException;

/**
 * 用来替代springframework的Assert工具类
 */
public class Assert {

    private Assert() {

    }

    /**
     * 为true，否则抛出BizException异常
     */
    public static void isTrue(boolean expression, ErrorCodeEnum errorCodeEnum) {
        if (!expression) {
            throw new BizException(errorCodeEnum);
        }
    }

    /**
     * 为空，否则抛出BizException异常
     */
    public static void isNull(Object object, ErrorCodeEnum errorCodeEnum) {
        if (!StringUtils.isEmpty(object)) {
            throw new BizException(errorCodeEnum);
        }
    }

    /**
     * 不为空，否则抛出BizException异常
     */
    public static void notNull(Object object, ErrorCodeEnum errorCodeEnum) {
        if (StringUtils.isEmpty(object)) {
            throw new BizException(errorCodeEnum);
        }
    }

    /**
     * 不为空，否则抛出BizException异常
     */
    public static void notNull(Object object, String code, String msg) {
        if (StringUtils.isEmpty(object)) {
            throw new BizException(code, msg);
        }
    }

    /**
     * 长度>0，否则抛出BizException异常
     */
    public static void hasLength(String text, ErrorCodeEnum errorCodeEnum) {
        if (!StringUtils.hasLength(text)) {
            throw new BizException(errorCodeEnum);
        }
    }

    public static void hasText(String text, ErrorCodeEnum errorCodeEnum) {
        if (!StringUtils.hasText(text)) {
            throw new BizException(errorCodeEnum);
        }
    }

    /**
     * 字符串textToSearch不包含substring，否则抛出BizException异常
     */
    public static void doesNotContain(String textToSearch, String substring,
                                      ErrorCodeEnum errorCodeEnum) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
            && textToSearch.contains(substring)) {
            throw new BizException(errorCodeEnum);
        }
    }

    /**
     * 数组不为空，否则抛出BizException异常
     */
    public static void notEmpty(Object[] array, ErrorCodeEnum errorCodeEnum) {
        if (ObjectUtils.isEmpty(array)) {
            throw new BizException(errorCodeEnum);
        }
    }

    /**
     * 数组不包含null元素，否则抛出BizException异常
     */
    public static void noNullElements(Object[] array, ErrorCodeEnum errorCodeEnum) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new BizException(errorCodeEnum);
                }
            }
        }
    }

    /**
     * Collection不为空，否则抛出BizException异常
     */
    public static void notEmpty(Collection<?> collection, ErrorCodeEnum errorCodeEnum) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(errorCodeEnum);
        }
    }

    /**
     * map不为空，否则抛出BizException异常
     */
    public static void notEmpty(Map<?, ?> map, ErrorCodeEnum errorCodeEnum) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(errorCodeEnum);
        }
    }

}
