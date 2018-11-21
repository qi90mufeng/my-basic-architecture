package com.my.base.exception.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.my.base.exception.ExceptionHandlerConfiguration;


/**
 * 开启异常传播
 * 开之后服务提供方抛出的异常将传播到服务调用方
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(value={ExceptionHandlerConfiguration.class})
public @interface EnableExceptionPropagation {
	
}
