package com.example.fuzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/10 9:57
 */
@RestController
public class HelloController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        Long count = redisTemplate.opsForValue().increment("count", 1);
        return "hello, docker num =>" + count;
    }


}
