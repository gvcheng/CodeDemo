package com.gvc.mapper;

import com.gvc.domain.User;

import java.util.List;

public interface UserMapper {
    // 一对多查询：查询所有用户，与此同时查询出该用户具有的订单
    public List<User> findAllWithOrders();

    // 多对多查询：查询所有用户，同时查询出该用户的所有角色
    public List<User> findAllWithRoles();

    //根据id查询用户
    public User findById(Integer id);

    //一对多嵌套查询：查询所有用户，与此同时查询出该用户具有的订单
    public List<User> findAllWithOrders2();

    //多对多嵌套查询：查询用户, 同时查询出该用户的所有角色
    public List<User> findAllWithRoles2();
}
