package com.gvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.gvc.mapper")
public class SpringbootUserCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUserCrudApplication.class, args);
    }

}
