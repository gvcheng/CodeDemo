package com.gvc.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
    通知类
*/
@Component
@Aspect //升级为切面类: 配置切入点和通知的关系
public class MyAdvice {

    @Pointcut("execution(* com.gvc.service.impl.AccountServiceImpl.*(..))")
    public void myPointCut(){}

   /* @Before("MyAdvice.myPointCut()")
    public void beforeAdvice() {
        System.out.println("前置通知执行了...");
    }

    @AfterReturning("MyAdvice.myPointCut()")
    public void afterReturningAdvice() {
        System.out.println("后置通知执行了...");
    }

    @AfterThrowing("MyAdvice.myPointCut()")
    public void afterThrowingAdvice() {
        System.out.println("异常通知执行了...");
    }

    @After("MyAdvice.myPointCut()")
    public void afterAdvice() {
        System.out.println("最终通知执行了...");
    }*/

    @Around("MyAdvice.myPointCut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object proceed = null;
        try {
            System.out.println("前置通知执行了...");
            proceed = pjp.proceed();
            System.out.println("后置通知执行了...");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("异常通知执行了...");
        } finally {
            System.out.println("最终通知执行了...");
        }
        return proceed;
    }
}
