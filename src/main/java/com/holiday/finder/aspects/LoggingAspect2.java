package com.holiday.finder.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(2)
public class LoggingAspect2 {

    @Pointcut("execution(public void com.holiday.finder.*.*.PlaceServiceImpl.savePlace(..))")
    private void pointCutService(){}


    @Pointcut("execution(public void com.holiday.finder.*.PlaceController.savePlace(..))")
    private void pointCutController(){}

    @Pointcut("pointCutService() || pointCutController())")
    private void pointCutOr(){}

    //    @Before("execution(public void savePlace(..))")
    @Before("pointCutOr()")
    public void beforeSavePlaceAdviceJP(JoinPoint joinPoint) {
        log.info("ASPECT: before add place joinPoint ...");
        log.info("... " + joinPoint.getSignature());
        log.info("... " + joinPoint.getArgs());
    }

}
