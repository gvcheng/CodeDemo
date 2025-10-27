package com.gvc.service.impl;

import com.gvc.mapper.UserMapper;
import com.gvc.pojo.User;
import com.gvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserByID(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addUser(User user) {
//        int inserted = userMapper.insert(user);//插入任意值
        userMapper.insertSelective(user); //属性非空才会拼入sql语句,高效
    }

    @Override
    public boolean updateUser(User user) {
        int updated = userMapper.updateByPrimaryKeySelective(user);
        return updated > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        return userMapper.deleteByPrimaryKey(id) > 0;
    }
}
