package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // set up pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){

        // display method we are calling
        String methodSignature =  theJoinPoint.getSignature().toShortString();
        myLogger.info("----->>>> in @Before: calling method : "+methodSignature);

        // display the arguments to the method
        Object[] args = theJoinPoint.getArgs();
        for(Object tempArg : args){
            myLogger.info("---->>>>>  argument : "+tempArg);
        }

    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result")
    public void after(JoinPoint theJoinPoint, Object result){
        // display method we are returning from
        String methodSignature =  theJoinPoint.getSignature().toShortString();
        myLogger.info("----->>>> in @AfterReturning: from method : "+methodSignature);

        // display the result
        myLogger.info("---->>>> result : "+result);
    }


}

