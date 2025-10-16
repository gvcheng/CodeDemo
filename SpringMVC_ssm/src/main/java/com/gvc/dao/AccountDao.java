package com.gvc.dao;

import com.gvc.domain.Account;

import java.util.List;

public interface AccountDao {
    //查询所有账户
    public List<Account> findAll();
    //添加账户
    public void save(Account account);
    //根据ID查询账户
    public Account findById(Integer id);
    //更新账户
    public void update(Account account);
    //批量删除账户
    public void deleteBatch(Integer[] ids);
}
