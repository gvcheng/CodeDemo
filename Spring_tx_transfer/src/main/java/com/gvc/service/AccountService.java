package com.gvc.service;

public interface AccountService {
    //转账方法
    public void transfer(String inUser,String outUser,Double money);
}
