package com.gvc.service;

import com.gvc.domain.Account;

public interface AccountService {

    public void transfer(String from, String to, Double money);

    public void save();

    public void update();

    public void delete();
}
