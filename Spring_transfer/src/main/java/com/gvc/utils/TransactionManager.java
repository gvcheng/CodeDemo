package com.gvc.utils;

/*
*   事务管理工具：开启事务、提交事务、关闭事务、回滚事务、释放资源
*/

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

//通知类
@Aspect //切面类
@Component("transactionManager")
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    @Around("execution(* com.gvc.service.impl.AccountServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws SQLException {
        Object proceed = null;
        try {
            connectionUtils.getConnection().setAutoCommit(false);
            proceed = pjp.proceed();
            connectionUtils.getConnection().commit();
        } catch (Throwable e) {
            e.printStackTrace();
            connectionUtils.getConnection().rollback();
        } finally {
            connectionUtils.getConnection().setAutoCommit(true);
            connectionUtils.getConnection().close();
            connectionUtils.removeConnection();
        }
        return proceed;
    }

    //开启事务
    public void beginTransaction() {
        //获取connection对象
        Connection connection = connectionUtils.getConnection();
        try {
            //将事务设置为手动提交
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void commitTransaction() {
        Connection connection = connectionUtils.getConnection();
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackTransaction() {
        Connection connection = connectionUtils.getConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void releaseTransaction() {
        Connection connection = connectionUtils.getConnection();
        try {
            //改回自动提交
            connection.setAutoCommit(true);
            //将连接归还连接池
            connectionUtils.getConnection().close();
            //解除线程绑定
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
