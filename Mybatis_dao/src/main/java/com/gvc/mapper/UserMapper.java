package com.gvc.mapper;

import com.gvc.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //根据id查询用户
    public User findUserById(int id);

    //查询所有用户
    public List<User> findAllResultMap();

    //进行多条件查询方式一
    public User findByIdAndUsername1(int id, String username);

    //进行多条件查询方式二
    public User findByIdAndUsername2(@Param("id") int id, @Param("username") String username);

    //进行多条件查询方式三
    public User findByIdAndUsername3(User user);

    //模糊查询：方式一
    public List<User> findByUsername1(String username);

    //模糊查询：方式二
    public List<User> findByUsername2(String username);

    //添加用户，获取返回主键：方式一
    public void saveUser1(User user);

    //添加用户，获取返回主键：方式二
    public void saveUser2(User user);

    //动态sql的 <if>标签：多条件查询
    public List<User> findByIdAndUsernameIf(User user);

    //动态sql的 <set>标签：动态更新
    public void updateIf(User user);

    //动态sql的 <foreach>标签：多值查询 (集合)
    public List<User> findByList(List<Integer> userIdList);

    //动态sql的 <foreach>标签：多值查询 (数组)
    public List<User> findByArray(Integer[] idArray);
}
