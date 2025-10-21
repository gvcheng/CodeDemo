package com.gvc;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private JedisPoolUtil(){}

    private volatile static JedisPool jedisPool = null;
    private volatile static Jedis jedis = null;
    //返回一个连接池
    private static JedisPool getInstance(){
        //双重检测锁
        if(jedisPool == null){ //第一层
            synchronized (JedisPoolUtil.class){
                if(jedisPool == null){ //第二层
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(1000);
                    jedisPoolConfig.setMaxIdle(30);
                    jedisPoolConfig.setMaxWaitMillis(60*1000);
                    jedisPoolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(jedisPoolConfig, "192.168.44.128", 6379);
                }
            }
        }
        return jedisPool;
    }

    public static Jedis getJedis(){
        if(jedis == null){
            jedis = getInstance().getResource();
        }
        return jedis;
    }

}
