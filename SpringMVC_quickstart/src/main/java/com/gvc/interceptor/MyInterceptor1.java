package com.gvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {

    @Override
    //在目标方法执行之前进行拦截 return默认为false:不放行(不执行后续的方法 目标方法,postHandle(),afterCompletion()...)
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle方法1执行了...");
        return true;
    }

    @Override
    // postHandle: 目标方法执行之后，视图对象返回之前所执行的方法
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle方法1执行了...");
    }

    @Override
    // afterCompletion：执行时机：在流程都执行完成后再执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion方法1执行了...");
    }
}
