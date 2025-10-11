package com.gvc.dao.impl;

import com.gvc.dao.AccountDao;
import com.gvc.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("accountDao") //生成该类实例存到IoC容器中
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private QueryRunner queryRunner;
    @Autowired
    private ConnectionUtils connectionUtils;

    @Override
    //转出操作
    public void out(String outUser, Double outMoney) {
        String sql = "update account set money=money-? where name=?";
        try {
            queryRunner.update(connectionUtils.getConnection(),sql,outMoney,outUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override

    //转入操作
    public void in(String inUser, Double inMoney) {
        String sql = "update account set money=money+? where name=?";
        try {
            queryRunner.update(connectionUtils.getConnection(),sql,inMoney,inUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
