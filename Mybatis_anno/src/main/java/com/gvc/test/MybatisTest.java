package com.gvc.test;


import com.gvc.domain.Orders;
import com.gvc.domain.User;
import com.gvc.mapper.OrderMapper;
import com.gvc.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest    {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    //在 @Test 标注的方法执行之前来执行
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }

    //测试查询方法
    @Test
    public void testSelect() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    //测试添加方法
    @Test
    public void testInsert() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("陶喆");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("香港");

        userMapper.saveUser(user);
    }

    //测试更新方法
    @Test
    public void testUpdate() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setId(11);
        user.setUsername("方大同");
        user.setBirthday(new Date());

        userMapper.update(user);
    }


    //测试更新方法
    @Test
    public void testDelete() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.delete(10);
    }

    //一对一查询：注解方式
    @Test
    public void testOneToOne() throws IOException {
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> orders = mapper.findAllWithUser();
        for (Orders order : orders) {
            System.out.println(order);
        }
    }

    //一对多查询：注解方式
    @Test
    public void testOneToMany() throws IOException {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAllWithOrder();
        for (User user : userList) {
            System.out.println(user);
            System.out.println(user.getOrdersList());
        }
    }

    //多对多查询：注解方式
    @Test
    public void testManyToMany() throws IOException {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAllWithRole();
        for (User user : userList) {
            System.out.println(user);
            System.out.println(user.getRoleList());
        }
    }
}
