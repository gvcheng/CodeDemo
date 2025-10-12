package com.gvc.service;

import com.gvc.domain.Account;

import java.util.List;

public interface AccountService {
    //查询所有账户
    public List<Account> findAll();
    //根据ID查询账户
    public Account findById(int id);
    //添加账户
    public void save(Account account);
    //更新账户
    public void update(Account account);
    //根据id删除账户
    public void deleteById(int id);
}
