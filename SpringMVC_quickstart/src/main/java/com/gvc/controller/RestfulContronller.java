package com.gvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController // 组合了@Controller和@ResponseBody
@RequestMapping("/restful")
public class RestfulContronller {
    /*根据ID进行查询 method = RequestMethod.GET只能通过GET方式访问
        localhost:8080/项目名/restful/user/2 + get请求方式
        404 return结果"findById:2",不是jsp
    */

//    @ResponseBody //直接响应给浏览器，不会再交给解析器处理
    @GetMapping("/user/{id}") //相当于@RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String findById(@PathVariable Integer id){
        //调用service方法完成对id为2这条记录的封装
        //findById()方法 如何获取 restful风格中url内占位符的值？
        return "findById:" + id;
    }

    //新增
    @PostMapping("/user") //相当于@RequestMapping(value = "/user/{id}",method = RequestMethod.POST)
    public String post(){
        return "post";
    }

    //更新
    @PutMapping("/user")
    public String put(){
        return "put";
    }

    //删除
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable Integer id){
        return "delete: " + id;
    }
}
