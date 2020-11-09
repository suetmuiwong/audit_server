package com.et.auditServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLockCommon {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁操作
     * @param key 关键字
     * @param value 值
     * @return true: 加锁成功 false: 加锁失败
     */
    public Boolean tryLock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            redisTemplate.expire(key, 30000, TimeUnit.MILLISECONDS);
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间 如果高并发的情况可能会出现已经被修改的问题  所以多一次判断保证线程的安全
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            return !StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue);
        }
        return false;
    }


    /**
     * Redis解锁的操作
     * @param key 关键字
     * @param value 值
     */
    public void unlock(String key, String value) {
        String currentValue = redisTemplate.opsForValue().get(key);
        try {
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * 通过自增的原子性操作实现任务的串行
     * @param key 关键字
     * @return 值 返回 1 时 加锁成功
     */
    public Long incrementLock(String key){
        return incrementLock(key,1000L);
    }

    public void decrementLock(String key){
        RedisAtomicLong lock = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        lock.decrementAndGet();
    }


    public Long getIncrementLockValue(String key){
        RedisAtomicLong lock = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        return lock.get();
    }

    public Long incrementLock(String key,Long liveTime){
        RedisAtomicLong lock = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        long increment = lock.incrementAndGet();
        if (increment == 1 && liveTime > 0) {//初始设置过期时间
            lock.expire(liveTime, TimeUnit.SECONDS);
        }
        return increment;
    }

}
