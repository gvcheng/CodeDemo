package com.gvc.service.impl;

import com.gvc.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    //目标方法（切入点）
    public void transfer() {
        System.out.println("转账方法执行了...");
//        int i = 1/0;
    }
}
