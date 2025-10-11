package com.gvc.dao;

import com.gvc.domain.Account;

public interface AccountDao {
    //转出操作
    public void out(String outUser,Double outMoney);

    //转入操作
    public void in(String inUser,Double inMoney);
}
