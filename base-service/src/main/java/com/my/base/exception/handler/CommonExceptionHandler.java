package com.my.base.exception.handler;

import com.my.base.dto.ResultDTO;
import com.my.base.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.my.base.exception.ValidatorException;

/**
 * 需要在component scan中加入该类所在包名
 *
 */
@RestControllerAdvice
public class CommonExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@ExceptionHandler(BizException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResultDTO<String> handlerBizException(BizException ex) {
		logger.error("产生业务异常", ex);
		return new ResultDTO<>(false, ex.getCode(), ex.getMsg(),null);
	}
	
	@ExceptionHandler(ValidatorException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResultDTO<String> handlerValidatorExcetion(ValidatorException ex) {
		logger.error("产生参数校验异常", ex);
		return new ResultDTO<>(false, ex.getCode(), ex.getMessage(),null);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResultDTO<String> handlerTypeMissMatchException(MethodArgumentTypeMismatchException ex) {
		logger.error("产生参数适配异常", ex);
		return new ResultDTO<>(false, "", "参数错误",null);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResultDTO<String> handlerOtherException(Exception ex) {
		logger.error("产生异常", ex);
		return new ResultDTO<>(false, "", "系统内部错误",null);
	}
}
