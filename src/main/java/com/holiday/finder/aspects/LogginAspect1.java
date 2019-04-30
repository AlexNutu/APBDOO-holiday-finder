package com.holiday.finder.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Slf4j
public class LogginAspect1 {

    @Before("execution(public void savePlace(..))")
    public void beforeSavePlaceAdvice() {
        log.info("ASPECT: before adding place ...");
    }

    @After("execution(public void com.holiday.finder.*.*.PlaceServiceImpl.savePlace(..))")
    public void afterSavePlaceAdvice() {
        log.info("ASPECT: after adding place ...");
    }

}
