package com.gvc;

import com.gvc.controller.HelloController;
import com.gvc.pojo.Person;
import com.gvc.pojo.Product;
import com.gvc.pojo.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  SpringJUnit4ClassRunner.class: Spring运行环境
 *  JUnit4.class: JUnit运行环境
 *  SpringRunner.class: Spring Boot运行环境
 */
@RunWith(SpringRunner.class) //@RunWith运行器
@SpringBootTest //标记当前类为springboot的测试类，加载项目名的ApplicationContext上下文环境
class SpringbootQuickstartDemoApplicationTests {

    @Autowired
    private HelloController helloController;

    @Test
    void contextLoads() {
        String hello = helloController.sayHello();
        System.out.println(hello);
    }

    @Autowired
    private Person person;
    @Test
    void showPersonInfo(){
        System.out.println(person.toString());
    }

    @Autowired
    private Student student;
    @Test
    void getStudent(){
        System.out.println(student);
    }

    @Autowired
    private Product product;
    @Test
    void getProduct(){
        System.out.println(product);
    }

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void testMyConfig(){
        System.out.println(applicationContext.containsBean("myService"));
        System.out.println(applicationContext.containsBean("myService__"));
    }

}
