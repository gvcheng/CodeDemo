package com.gvc.mapper;

import com.gvc.domain.Orders;
import com.gvc.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrderMapper {
    //查询所有订单，同时查询订单所属的用户信息
    @Select("SELECT * FROM orders")
    @Results({//代替<resultMap>标签
        @Result(property = "id",column = "id",id = true),  //id = true表示为主键字段
        @Result(property = "ordertime",column = "ordertime"),
        @Result(property = "total",column = "total"),
        @Result(property = "uid",column = "uid"),
        @Result(property = "user", javaType = User.class,column = "uid",
                one = @One(select = "com.gvc.mapper.UserMapper.findById",fetchType = FetchType.EAGER)) //namespace.id, EAGER立即加载
    })
    public List<Orders> findAllWithUser();

    //根据用户id查询订单
    @Select("select * from orders where uid = #{uid}")
    public List<Orders> findOrdersByUid(Integer uid);

}
