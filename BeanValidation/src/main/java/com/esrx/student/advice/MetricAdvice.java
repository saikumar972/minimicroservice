package com.esrx.student.advice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MetricAdvice {
    @Autowired
    ObservationRegistry observationRegistry;
    @Pointcut(value="execution(* com.acc.validate.controller.StudentController.*(..))")
    public void controllerPointCut(){

    }
    @After("controllerPointCut()")
    public void metrics(JoinPoint joinPoint){
        log.info("Application collecting metrics");
        Observation.createNotStarted(joinPoint.getSignature().getName(),observationRegistry)
                .observe(()->joinPoint.getArgs());
        log.info("Application sent the metrics");
    }
}
