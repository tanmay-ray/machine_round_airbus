package com.challenge.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Aspect
@Slf4j
@Order(0)
public class LogMethodEntryExit {

    @Around("execution(public * com.challenge..*.*(..))")
    public Object logMethodEntryAndMethodExitLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Method entry {} and the args are: {}", proceedingJoinPoint, proceedingJoinPoint.getArgs());
        Object ret = proceedingJoinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
        log.debug("Method exit {} and Time taken is {} milli seconds", proceedingJoinPoint, executionTime);
        return ret;
    }
}
