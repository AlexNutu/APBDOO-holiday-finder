package com.holiday.finder.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
@Slf4j
public class LoggingAspect3 {

    @Around("execution(* com.holiday.finder.model.Hello.get*(..))")
    public void aroundSavePlaceAdvice() {
        log.info("ASPECT: around Hello getName() ...");
    }

    @After("execution(* com.holiday.finder.model.*.*(..))")
    public void afterModelAdvice() {
        log.info("ASPECT: after Holiday Finder model ...");
    }

}