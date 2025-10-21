package com.gvc;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    Jedis jedis = new Jedis("192.168.44.128",6379);
    @Test
    public void test1() throws InterruptedException {
        // 获取当前余额
        int balance = Integer.parseInt(jedis.get("balance"));
        // 本次支出金额
        int expense = 10;
        // 监控余额键，防止并发修改
        jedis.watch("balance");
        //模拟网络延迟5秒
        Thread.sleep(5000);

        if (balance < expense) {
            // 余额不足，解除监控
            jedis.unwatch();
            System.out.println("余额不足");
        }else {
            // 开启事务
            Transaction tx = jedis.multi();
            tx.decrBy("balance",expense);
            tx.incrBy("expense",expense);
            tx.exec();
        System.out.println("目前余额： " + jedis.get("balance"));
        System.out.println("累计支出： " + jedis.get("expense"));
        }


    }
}
