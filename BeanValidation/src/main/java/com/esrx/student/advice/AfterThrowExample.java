package com.esrx.student.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class AfterThrowExample {
    @Pointcut("execution(* com.acc.validate.controller.StudentController.addData(..))")
    public void pointcutExp(){

    }
    @AfterThrowing(pointcut="pointcutExp()", throwing = "exception")
    public void AfterThrowExample(JoinPoint joinPoint,Exception exception){
        if(exception instanceof IllegalArgumentException){
            log.info("After Throw class check and error is {}",exception.getMessage());
        }
//        if(exception instanceof RuntimeException){
//            log.info("After Throw class check and error is {}",exception.getMessage());
//        }
    }
}
