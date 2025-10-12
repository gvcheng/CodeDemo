package com.gvc.test;

import com.gvc.domain.Account;
import com.gvc.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test//测试添加
    public void testSaveAccount() {
        Account account = new Account();
        account.setName("Lucy");
        account.setMoney(1000d);
        accountService.save(account);
    }

    @Test//测试查询全部
    public void testFindAll() {
        List<Account> accountList = accountService.findAll();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    @Test//测试根据ID查询
    public void testFindById() {
       Account account = accountService.findById(1);
       System.out.println(account);
    }

    @Test//测试更新
    public void testUpdate() {
        Account account = new Account();
        account.setId(1);
        account.setName("Tom");
        account.setMoney(1000d);
        accountService.update(account);
    }

    @Test//测试根据ID删除
    public void testDeleteById() {
        accountService.deleteById(3);
    }

}
