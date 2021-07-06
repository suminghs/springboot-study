package com.example.fuzz.controller;

import lombok.val;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/7/1 13:30
 */
@RestController
public class IndexController {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RedissonClient redisson;

    // 传统方式锁  适用单体架构
    @GetMapping("/buy0")
    public String buy0() {

        synchronized (this) {
            Integer sku = Integer.valueOf(redisTemplate.opsForValue().get("sku"));
            if (sku > 0) {
                sku = sku - 1;
                redisTemplate.opsForValue().set("sku", sku.toString());
                System.out.println("成功， 剩余库存：" + sku);
                return "购买成功";
            }
        }


        return "库存不足";
    }

    // redis锁  利用setnx并设置过期时间保证死锁
    @GetMapping("/buy")
    public String buy() {
        String lockKey = "sukLock";
        String clientId = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, Duration.ofSeconds(5));

        try {
            if (!lock) {
                System.out.println("获取锁失败");
                return "购买失败";
            }

            Integer sku = Integer.valueOf(redisTemplate.opsForValue().get("sku"));
            if (sku > 0) {
                Thread.sleep(1000);
                sku = sku - 1;
                redisTemplate.opsForValue().set("sku", sku.toString());
                System.out.println("成功， 剩余库存：" + sku);
                return "购买成功";
            }

            System.out.println("库存不足");

            return "库存不足";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (clientId.equals(redisTemplate.opsForValue().get(lockKey))) {
                System.out.println("删除锁");
                redisTemplate.delete(lockKey);
            }
        }


    }

    // redisson锁
    @GetMapping("/buy2")
    public String buy2() {
        String lockKey = "sukLock";
        RLock lock = redisson.getLock(lockKey);
        lock.lock(10, TimeUnit.MINUTES);

        try {
            Integer sku = Integer.valueOf(redisTemplate.opsForValue().get("sku"));
            if (sku > 0) {
                Thread.sleep(1000);
                sku = sku - 1;
                redisTemplate.opsForValue().set("sku", sku.toString());
                System.out.println("成功， 剩余库存：" + sku);
                return "购买成功";
            }

            System.out.println("库存不足");

            return "库存不足";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "";
        } finally {
            lock.unlock();
        }
    }

}
