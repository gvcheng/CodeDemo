package com.gvc.controller;

import com.gvc.pojo.User;
import com.gvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //restful风格

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public User getUserByID(@PathVariable int id) {
        return userService.getUserByID(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "删除成功";
    }

    @PostMapping("/addUser")
    public String addUser(User user) {
        userService.addUser(user);
        return "新增成功";
    }

    @PutMapping("/updateUser")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "修改成功";
    }





}
