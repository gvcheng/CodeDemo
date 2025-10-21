package com.gvc;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//测试Jedis连接池
public class TestJedisPool {
    @Test
    public void testJedisPool(){
        Jedis jedis1 = JedisPoolUtil.getJedis();
        Jedis jedis2 = JedisPoolUtil.getJedis();
        System.out.println(jedis1 == jedis2);
    }
}
