package com.gvc;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//常用API
public class TestAPI {

    Jedis jedis = new Jedis("192.168.44.128",6379);

    @Test
    public void testTypeString(){
        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        //查看所有键值
        Set<String> set = jedis.keys("*");
        Iterator<String> iterator = set.iterator();
        for (set.iterator(); iterator.hasNext();){
            String key = iterator.next();
            System.out.println("key: " + key + "; value: " + jedis.get(key));
        }
        //查看k2是否存在
        Boolean k2Exist = jedis.exists("k2");
        System.out.println("k2是否存在：" + k2Exist);
        //查看k1过期时间
        System.out.println(jedis.ttl("k1"));
        //String
        jedis.mset("k4","v4","k5","v5");
        System.out.println(jedis.mget("k1","k2","k3","k4","k5"));
        System.out.println("--------------------------------------");
    }

    @Test
    //list
    public void testTypeList() {
        jedis.lpush("list01","l1","l2","l3","l4","l5");
        System.out.println(jedis.lrange("list01",0,-1));
//        jedis.lpop("list01",9);
    }

    @Test
    //set
    public void testTypeSet() {
        jedis.sadd("order","jd001","jd002","jd003");
        Set<String> order = jedis.smembers("order");
        for (String s : order){
            System.out.println(s);
        }
        jedis.srem("order","jd002");
        System.out.println(jedis.smembers("order").size());
    }

    @Test
    //hash
    public void testTypeHash() {
        jedis.hset("user1","username","Leblanc");
        System.out.println(jedis.hget("user1","username"));

        HashMap<String, String> userMap = new HashMap<String, String>();
        userMap.put("username","Tom");
        userMap.put("gender","male");
        userMap.put("address","Beijing");
        userMap.put("phone","123-8888-8888");
        jedis.hmset("user2",userMap);
        List<String> List = jedis.hmget("user2", "username", "gender", "phone");
        for (String s : List){
            System.out.println(s);
        }
    }

    @Test
    //zset
    public void testTypeZSet(){
        jedis.zadd("zset01",60d,"zs1");
        jedis.zadd("zset01",70d,"zs2");
        jedis.zadd("zset01",80d,"zs3");
        jedis.zadd("zset01",90d,"zs4");
        List<String> zset01 = jedis.zrange("zset01", 0, -1);
        for (String s : zset01){
            System.out.println(s);
        }
    }
}
