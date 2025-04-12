package com.gvc.test;

import com.gvc.domain.Orders;
import com.gvc.domain.User;
import com.gvc.mapper.OrderMapper;
import com.gvc.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MybatisTest {

    //一对一关联查询： 查询所有订单，同时查询每个订单所属的用户信息
    @Test
    public void testFindAllWithUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> orders = mapper.findAllWithUser();
        for (Orders order : orders) {
            System.out.println(order);
        }

        sqlSession.close();
    }

    //一对多关联查询： 查询所有用户，同时查询每个用户关联的订单信息
    @Test
    public void testFindAllWithOrders() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAllWithOrders();
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }


    //多对多关联查询：查询所有用户，同时查询出用户关联的的角色信息
    @Test
    public void testFindAllWithRole() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAllWithRoles();
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    //一对一嵌套查询： 查询所有订单，同时查出每个订单所属的用户信息
    @Test
    public void testFindAllWithUser2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> ordersList2 = mapper.findAllWithUser2();

        for (Orders orders : ordersList2) {
            System.out.println(orders);
        }

        sqlSession.close();
    }

    //一对多嵌套查询：查询所有用户，与此同时查询出该用户具有的订单
    @Test
    public void testFindAllWithOrders2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList2 = mapper.findAllWithOrders2();
        for (User user : userList2) {
            System.out.println(user);

//            System.out.println(user.getOrdersList());
        }


        sqlSession.close();
    }

    //多对多嵌套查询：查询用户, 同时查询出该用户的所有角色
    @Test
    public void testFindAllWithRoles2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> rolesList2 = mapper.findAllWithRoles2();
        for (User user : rolesList2) {
            System.out.println(user);
        }

        sqlSession.close();
    }


    //验证mybatis中的一级缓存
    @Test
    public void testOneCache() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //根据id查询用户信息
        //第一次查询，查询的是数据库
        User user1 = mapper.findById(1);
        System.out.println(user1);

        //手动清空缓存
//        sqlSession.close();

        //第二次查询，查询到是一级缓存
        User user2 = mapper.findById(1);
        System.out.println(user2);

        sqlSession.close();
    }

    //验证mybatis中的二级缓存
    @Test
    public void testTwoCache() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        //第一次查询
        User user1 = mapper1.findById(1);
        System.out.println(user1);
        //只有执行sqlSession.commit()或sqlSession.close(),一级缓存中的内容才会刷新到二级缓存
        sqlSession1.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.findById(1);
        System.out.println(user2);
        sqlSession2.close();
    }

}
