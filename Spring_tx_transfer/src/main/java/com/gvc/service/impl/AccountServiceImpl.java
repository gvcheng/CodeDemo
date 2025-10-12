package com.gvc.service.impl;

import com.gvc.dao.AccountDao;
import com.gvc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, readOnly = false, timeout = -1)
    public void transfer(String inUser, String outUser, Double money) {
        accountDao.out(outUser,money);
//        int i = 1/0;
        accountDao.in(inUser,money);
    }
}
