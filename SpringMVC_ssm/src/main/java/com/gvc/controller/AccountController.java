package com.gvc.controller;

import com.gvc.domain.Account;
import com.gvc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //查询所有用户
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return "list";
    }

    //添加用户
    @RequestMapping("/save")
    public String save(Account account){
        accountService.save(account);
        //跳转到findAll方法重新查阅一次数据库
        return "redirect:/account/findAll";
    }

    //根据ID查询账户信息，完成账户回显
    @RequestMapping("/findById")
    public String findById(Integer id,Model model){
        Account account = accountService.findById(id);
        //存到Model中
        model.addAttribute("account",account);
        return "update";
    }

    //修改账户
    @RequestMapping("/update")
    public String update(Account account){
       accountService.update(account);
       return "redirect:/account/findAll";
    }

    //批量删除账户
    @RequestMapping("/deleteBatch")
    public String deleteBatch(Integer[] ids){
        accountService.deleteBatch(ids);
        return "redirect:/account/findAll";
    }
}
