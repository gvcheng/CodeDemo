package com.gvc.config;

import com.gvc.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //当前类为配置类，SpringBoot会扫描该类，将标识@Bean注解的方法返回值注入容器中
public class MyConfig {
    @Bean
    public MyService myService(){
        return new MyService();
    }
    @Bean("myService__")
    public MyService myService2(){
        return new MyService();
    }
}
