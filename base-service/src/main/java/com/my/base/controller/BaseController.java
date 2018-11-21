package com.my.base.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import com.my.base.dto.ResultDTO;
import com.my.base.exception.BizException;
import com.my.base.exception.ValidatorException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class BaseController {
    protected transient final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 中文解码
     * @param param
     * @return
     * @throws UnsupportedEncodingException
     */
    public String decodeStr(String param) {
        if (StringUtils.isNotBlank(param)) {
            try {
                param = URLDecoder.decode(param, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return param;
    }

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultDTO<String> handlerBizException(HttpServletResponse resp, BizException ex) {
        logger.error("产生业务异常：{}", ex.toString(), ex);
        //resp.setStatus(ResultDTO.ERROR_HTTP_STATUS);
        return new ResultDTO<>(false, ex.getCode(), ex.getMsg(), null);
    }

    @ExceptionHandler(ValidatorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDTO<String> handlerValidatorException(ValidatorException ex) {
        logger.error("产生参数校验异常", ex);
        return new ResultDTO<>(false, ex.getCode(), ex.getMsg(), null);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDTO<String> handlerTypeMissMatchException(Exception ex) {
        logger.error("产生参数适配异常", ex);
        return new ResultDTO<>(false, "参数错误");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultDTO<String> handlerIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("产生业务异常", ex);
        return new ResultDTO<>(false, ex.getMessage());
    }
}
