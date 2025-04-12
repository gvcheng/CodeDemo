package com.gvc.mapper;

import com.gvc.domain.Orders;
import com.gvc.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserMapper {
    //查询用户
    @Select("select * from user")
    public List<User> findAll();

    //添加用户
    @Insert("insert into user(username,birthday,sex,address) values (#{username},#{birthday},#{sex},#{address})")
    public void saveUser(User user);

    //更新用户
    @Update("update user set username = #{username},birthday = #{birthday} where id = #{id}")
    public void update(User user);

    //删除用户
    @Delete("delete from user where id = #{id}")
    public void delete(Integer id);

    //根据id查询用户
    @Select("select * from user where id = #{uid}")
    public User findById(Integer uid);

    //查询所有用户及关联的订单信息
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column = "username"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "address",column = "address"),
            @Result(property = "ordersList",javaType = List.class,column = "id",
                    many = @Many(select = "com.gvc.mapper.OrderMapper.findOrdersByUid")),
    })
    public List<User> findAllWithOrder();

    //查询所有用户及关联角色
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(property = "roleList",javaType = List.class, column = "id",many = @Many(select = "com.gvc.mapper.RoleMapper.findRoleById")),
    })
    public List<User> findAllWithRole();

}
