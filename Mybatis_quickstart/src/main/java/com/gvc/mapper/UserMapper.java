package com.gvc.mapper;

import com.gvc.domain.User;

public interface UserMapper {

    //根据id查询用户
    public User findUserById(int id);
}
