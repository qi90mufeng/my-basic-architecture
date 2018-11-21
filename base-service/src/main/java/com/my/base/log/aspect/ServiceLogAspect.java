package com.my.base.log.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 使用该AOP拦截服务层日志
 *
 */
@Component
@Aspect
public class ServiceLogAspect {
	Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);
	
	@Pointcut("execution(public * com.my..*.service.*.*(..))")
	public void serviceLogAspect() {} 
	
	@Around("serviceLogAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal;
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        //取出参数
        Object[] args = pjp.getArgs();
        StringBuilder sb = new StringBuilder("调用[{}] 入参");
        for(Object arg : args) {
        	sb.append("[");
        	sb.append(arg);
        	sb.append("] ");
        }
        try {
        	retVal = pjp.proceed();
        	sb.append("返回结果[{}]");
        	logger.info(sb.toString(), method.toGenericString(), retVal);
        	return retVal;
        } catch (Exception e) {
        	sb.append("产生异常[{}]");
        	logger.error(sb.toString(), method.toGenericString(), e.getMessage());
        	throw e;
		}
    }
	
	public static void main(String[] args) {
		Object o = null;
		StringBuilder sb = new StringBuilder();
		sb.append("test").append(o);
		System.out.println(sb.toString());
	}
}
