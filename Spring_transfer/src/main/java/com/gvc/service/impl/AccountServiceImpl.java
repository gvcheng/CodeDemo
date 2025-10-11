package com.gvc.service.impl;

import com.gvc.dao.AccountDao;
import com.gvc.service.AccountService;
import com.gvc.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransactionManager transactionManager;

    //转账方法
    @Override
    public void transfer(String from, String to, Double money) {

            accountDao.out(from,money);
//            int i = 1/0;
            accountDao.in(to,money);

    }

    @Override
    public void save() {
        System.out.println("调用save方法。。。");
    }

    @Override
    public void update() {
        System.out.println("调用update方法。。。");
    }

    @Override
    public void delete() {
        System.out.println("调用delete方法。。。");
    }
}
