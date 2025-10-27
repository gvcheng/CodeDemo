package com.gvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/helloBoot")
    public String helloBoot(){
        return "Hello SpirngBoot!";
    }
}

