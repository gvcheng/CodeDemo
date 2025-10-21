package com.gvc;

import redis.clients.jedis.Jedis;

//测试连接Redis
public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.44.128",6379);
        String pong = jedis.ping();
        System.out.println("pong = " + pong);
    }
}
