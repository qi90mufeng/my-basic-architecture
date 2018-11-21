package com.my.base.parameter.aspect;


import java.util.List;

import com.my.base.dto.ResultDTO;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


/**
 * 使用该AOP拦截器时请设置Order
 * 参数校验拦截器
 *
 */
@Aspect
@Component
@Order(1)
public class ParamsValidAspect {
	Logger logger = LoggerFactory.getLogger(ParamsValidAspect.class);
	
	@Pointcut("(execution(public * com.snfq..*.*Controller.*(..))) || (execution(public * com.snfq..*.*Provider.*(..)))")
	public void paramsValid() {} 
	
	@Around("paramsValid()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
	    BindingResult bindingResult = null;
        for(Object arg:pjp.getArgs()){
            if(arg instanceof BindingResult){
                bindingResult = (BindingResult) arg;
            }
        }
        if(bindingResult != null){
            List<ObjectError> errors = bindingResult.getAllErrors();
            if(errors.size()>0){
                StringBuilder msg = new StringBuilder();
                for(ObjectError error :errors){
                    msg.append(error.getDefaultMessage());
                    msg.append("\n");
                }
                if(StringUtils.isNotBlank(msg.toString())){
                	msg.delete(msg.length()-1, msg.length());
                }
                return new ResultDTO<String>(false, msg.toString());
            }
        }
        
        return pjp.proceed();
    }
	
}
