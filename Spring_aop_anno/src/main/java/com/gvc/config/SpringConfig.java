package com.gvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.gvc")
@EnableAspectJAutoProxy //开启AOP自动代理，替代<aop:aspectj-autoproxy>标签
public class SpringConfig {
}
