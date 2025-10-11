package com.gvc.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/*
 通知类
*/
public class MyAdvice {
    public void beforeAdvice() {
        System.out.println("前置通知执行了...");
    }

    public void afterReturningAdvice() {
        System.out.println("后置通知执行了...");
    }

    public void afterThrowingAdvice() {
        System.out.println("异常通知执行了...");
    }

    public void afterAdvice() {
        System.out.println("最终通知执行了...");
    }

    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object proceed = null;
        try {
            System.out.println("前置通知执行了。。。");
            //切点方法执行
            proceed = pjp.proceed();
            System.out.println("后置通知执行了。。。");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("异常通知执行了。。。");
        } finally {
            System.out.println("最终通知执行了。。。");
        }
        return proceed;
    }
}
