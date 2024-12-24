package com.esrx.student.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AroundExampleWithAnnotation {
    @Pointcut("@annotation(com.acc.validate.annotations.CustomAnnotation)")
    public void annotationPointcut(){

    }

    @Around("annotationPointcut()")
    public Object aroundExample(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //before
        log.info("Before invoking method {}, respective class{}",proceedingJoinPoint.getSignature(),proceedingJoinPoint.getClass().getName());
        log.info("Arguments that were passed in the method {}",proceedingJoinPoint.getArgs());
        //calling method
        Object object=proceedingJoinPoint.proceed();
        //After
        log.info("After invoking method the results were {}",new ObjectMapper().writeValueAsString(object));
        return object;
    }
}
