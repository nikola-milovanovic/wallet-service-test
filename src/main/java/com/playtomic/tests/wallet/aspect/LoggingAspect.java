package com.playtomic.tests.wallet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LoggingAspect {
    private Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.playtomic.tests.wallet.controller..*.*(..))")
    public void executedControllerMethodsLogger(JoinPoint joinPoint) {
        log.info("[ Executed method {} ]", joinPoint);
    }
}
