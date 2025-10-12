package com.gvc.dao.impl;

import com.gvc.dao.AccountDao;
import com.gvc.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    //查询所有账户
    public List<Account> findAll() {
        String sql = "select * from account";
        List<Account> accountList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class));

        return accountList;
    }

    @Override
    //根据ID查询账户
    public Account findById(int id) {
        String sql = "select * from account where id = ?";
        Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), id);

        return account;
    }

    @Override
    //添加账户
    public void save(Account account) {
        String sql = "insert into account (name, money) values (?, ?)";
        jdbcTemplate.update(sql, account.getName(), account.getMoney());
    }

    @Override
    //修改账户
    public void update(Account account) {
        String sql = "update account set name = ?, money = ? where id = ?";
        jdbcTemplate.update(sql, account.getName(), account.getMoney(), account.getId());
    }

    @Override
    //删除账户
    public void deleteById(int id) {
        String sql = "delete from account where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
