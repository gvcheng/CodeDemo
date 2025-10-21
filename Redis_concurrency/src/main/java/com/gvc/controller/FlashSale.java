package com.gvc.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
public class FlashSale {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Redisson redisson;
    @RequestMapping("/flashSale")
    //synchronized 只能解决一个Tomcat的并发问题，锁的是一个进程下的线程并发，如果分布式环境多个进程并发这种方案失效
    public @ResponseBody synchronized String flashSale(){

        //定义商品id
        String productKey = "iPhone-17-ProMax";
        // 通过redisson获取锁,rLock的底层源码集成setnx,过期时间等操作
        RLock rLock = redisson.getLock(productKey);
        // rLock上锁，过期时间 30秒
        rLock.lock(10, TimeUnit.SECONDS);

        try {
            //1.从redis获取数据：手机的库存数量
            int phoneCount = Integer.parseInt(stringRedisTemplate.opsForValue().get("phone"));
            //2. 判断手机的数量是否足够
            if(phoneCount > 0){
                phoneCount--;
                //库存减少后将数据保存回Redis
                stringRedisTemplate.opsForValue().set("phone", phoneCount + "");
                System.out.println("phones-1, remain: " + phoneCount);
            }else{
                System.out.println("not enough phones!");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            rLock.unlock();
        }
        return "over!";

    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        //单个redis服务器
        config.useSingleServer().setAddress("redis://192.168.44.128:6379").setDatabase(0);
        //集群redis服务器
        //config.useClusterServers().setScanInterval(2000).addNodeAddress("redis://192.168.44.128:6379","redis://192.168.44.129:6379","redis://192.168.44.130:6379");
        return (Redisson) Redisson.create(config);
    }
}
