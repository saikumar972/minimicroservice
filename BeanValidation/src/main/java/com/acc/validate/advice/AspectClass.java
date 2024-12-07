package com.acc.validate.advice;

import com.acc.validate.utils.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AspectClass {
    @Pointcut("execution(* com.acc.validate.controller.*.*(..))")
    private void controllerPointCut(){
    }
    @Pointcut("execution(* com.acc.validate.service.StudentService.*(..))")
    private void servicePointcut(){
    }
    //using target
    @Pointcut("target(com.acc.validate.controller.StudentController)")
    public void controllerPointcutTarget(){

    }
    //@Before("controllerPointCut()")
    public void controllerAdvice(JoinPoint joinPoint){
        log.info("StudentController :: Before invoked className and methodName {}, {}",joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName());
        log.info("StudentController :: Before invoked parameters  in controller {}",StudentMapper.JasonMapper(joinPoint.getArgs()));
    }

    //@Before("servicePointcut()")
    public void ServiceAdvice(JoinPoint joinPoint){
        log.info("StudentService :: Before invoked className and methodName {}, {}",joinPoint.getTarget(),joinPoint.getSignature().getName());
        log.info("StudentService :: Before invoked parameters  in service {}",StudentMapper.JasonMapper(joinPoint.getArgs()));
    }
    //AfterReturn
   // @AfterReturning(value = "controllerPointcutTarget()",returning = "object")
    public void ControllerAfterAdvice(JoinPoint joinPoint,Object object){
        log.info("StudentController :: AfterReturning invoked className and methodName {}, {}",joinPoint.getTarget(),joinPoint.getSignature().getName());
        log.info("StudentController :: AfterReturning invoked parameters  in controller {}",StudentMapper.JasonMapper(object));
    }
}
