package com.gvc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接工具类，从数据源中获取一个连接，并将其与线程绑定
 * ThreadLocal: 线程内部的存取类，可以在指定的线程内存储数据 --- <key:ThreadLocal（当前线程）,value:任意类型的值 Connection>
 */

@Component
public class ConnectionUtils {
    @Autowired
    private DataSource dataSource;

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //获取当前线程上的连接: 如果获取的连接为空，就从数据源获取连接，放到ThreadLocal中（绑定到当前线程）
    public Connection getConnection(){
        //1.先从ThreadLocal上获取连接
        Connection connection = threadLocal.get();
        // 2.判断当前线程是否有连接
        if(connection==null){
            // 3.从数据源中获取一个连接，并存入到ThreadLocal中
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    //解除当前线程的连接绑定
    public void removeConnection(){
        threadLocal.remove();
    }
}
