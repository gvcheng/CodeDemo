package com.gvc.domain;

import java.util.Date;

public class Orders {
    private Integer id;
    private String ordertime;
    private Double total;
    private Integer uid;

    //表示当前订单属于哪个用户
    private User user;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", ordertime='" + ordertime + '\'' +
                ", total=" + total +
                ", uid=" + uid +
                ", user=" + user +
                '}';
    }

    public Orders(Integer id, String ordertime, Double total, Integer uid, User user) {
        this.id = id;
        this.ordertime = ordertime;
        this.total = total;
        this.uid = uid;
        this.user = user;
    }

    public Orders() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
