
package com.my.base.util;

import com.my.base.common.ErrorCodeEnum;
import com.my.base.exception.BizException;
import org.slf4j.Logger;

import com.github.pagehelper.util.StringUtil;

/**
 * 抛出业务异常工具类
 * @version $Id: ThrowExceptionUtil.java, v 0.1 2017年8月24日 上午11:24:18 longyuxiang Exp $
 */
public class ThrowExceptionUtils {

    public static void throwException(Logger logger, Object obj, String code, String msg) {
        if (obj == null) {
            throwException(logger, code, msg);
        }
    }

    public static void throwException(Logger logger, Object obj, ErrorCodeEnum codeEnum) {
        if (obj == null) {
            throwException(logger, codeEnum);
        }
    }
    
    public static void throwException(Logger logger, String value, String code, String msg) {
        if (StringUtil.isEmpty(value)) {
            ThrowExceptionUtils.throwException(logger, code, msg);
        }
    }

    public static void throwException(Logger logger, String value, ErrorCodeEnum codeEnum) {
        if (StringUtil.isEmpty(value)) {
            ThrowExceptionUtils.throwException(logger, codeEnum);
        }
    }

    public static void throwException(Logger logger, String code, String msg) {
        logger.error(msg);
        throw new BizException(code, msg);
    }

    public static void throwException(Logger logger, ErrorCodeEnum codeEnum) {
        logger.error(codeEnum.getMsg());
        throw new BizException(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static void throwException(Logger logger, String code, String msg, Exception e) {
        logger.error(msg, e);
        throw new BizException(code, msg);
    }

    public static void throwException(Logger logger, ErrorCodeEnum codeEnum, Exception e) {
        logger.error(codeEnum.getMsg(), e);
        throw new BizException(codeEnum.getCode(), codeEnum.getMsg());
    }
}
