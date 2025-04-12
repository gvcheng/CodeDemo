package com.gvc.mapper;

import com.gvc.domain.Orders;

import java.util.List;

public interface OrderMapper {
    //一对一关联查询： 查询所有订单，同时查出每个订单所属的用户信息
    public List<Orders> findAllWithUser();

    //一对一嵌套查询： 查询所有订单，同时查出每个订单所属的用户信息
    public List<Orders> findAllWithUser2();

    // 根据uid查询对应订单
    public List<Orders> findByUid(Integer uid);
}
