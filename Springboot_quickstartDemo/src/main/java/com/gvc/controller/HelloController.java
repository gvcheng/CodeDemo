package com.gvc.controller;

import com.gvc.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/jdbcTemplate")
    public String jdbcTemplate(){
        return jdbcTemplate.toString();
    }

    @Autowired
    private Person person;
    @RequestMapping("/getPerson")
    public String getPerson(){
        return person.toString();
    }
}
