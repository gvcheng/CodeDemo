package com.gvc.uitls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate; // 注入 RedisTemplate 实例

    /**
     * 读取缓存（根据 key 获取值）
     * @param key 缓存键
     * @return 缓存值
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存（设置 key-value，有效期 1 天）
     * @param key 缓存键
     * @param value 缓存值
     * @return 操作结果（true：成功，false：失败）
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            // opsForValue()：操作字符串类型数据；set 方法参数：key、value、有效期、时间单位
            redisTemplate.opsForValue().set(key, value, 1, TimeUnit.DAYS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存（先获取旧值，再设置新值）
     * @param key 缓存键
     * @param value 新缓存值
     * @return 操作结果
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存（根据 key 删除）
     * @param key 缓存键
     * @return 操作结果
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
