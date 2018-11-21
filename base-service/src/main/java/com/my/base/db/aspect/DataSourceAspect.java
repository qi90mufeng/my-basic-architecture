package com.my.base.db.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import com.my.base.db.DataSourceContextHolder;
import com.my.base.db.annotation.DataSource;


/**
 * 使用该AOP拦截器时请设置Order
 * 事务处理器Order请务必大于该AOP拦截器Order
 *
 */
@Aspect
//@Component
@Order(0)
public class DataSourceAspect {
	Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
	
	@Pointcut("@annotation(DataSource)")
	public void dataSourceAspect() {} 
	
	@Around("dataSourceAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal;
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        logger.info("method {}", method);
        DataSource dataSource = null;
        if (method != null) {
        	dataSource = method.getAnnotation(DataSource.class);
        }
        if (dataSource != null) {
        	DataSourceContextHolder.setDataSourceType(dataSource.value());
        } 
        try {
        	retVal = pjp.proceed();
        	return retVal;
        } finally {
        	DataSourceContextHolder.clearDataSourceType();
		}
    }
}
