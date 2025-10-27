package com.gvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    SpringBoot的启动类通常放在二级包中,比如:com.gvc.SpringBootDemoApplication
    因为SpringBoot项目在做包扫描,会扫描启动类所在的包及其子包下的所有内容｡
 */
@SpringBootApplication  //标识当前类为SpringBoot项目的启动类
public class SpringBootDemoApplication {
    public static void main(String[] args) {
        //样板代码
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
