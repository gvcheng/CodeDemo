package com.gvc.service.impl;

import com.gvc.dao.AccountDao;
import com.gvc.domain.Account;
import com.gvc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        List<Account> accountList = accountDao.findAll();
        return accountList;
    }

    @Override
    public Account findById(int id) {
        Account account = accountDao.findById(id);
        return account;
    }

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void deleteById(int id) {
        accountDao.deleteById(id);
    }
}
