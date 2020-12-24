package com.example.fuzz.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis操作通用类
 *
 * @author
 */
@Data
@Slf4j
public class RedisServiceImpl implements IRedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final StringRedisTemplate strTemplate;

    private final static String PREFIX = "cca:";

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate strTemplate) {
        this.redisTemplate = redisTemplate;
        this.strTemplate = strTemplate;
    }

    @SneakyThrows
    @Override
    public boolean hasKey(@NotBlank String key) {
        Boolean hasKey = redisTemplate.hasKey(PREFIX + key);
        if (hasKey == null) return false;
        return hasKey;
    }

    @SneakyThrows
    @Override
    public boolean setObject(@NotBlank String key, Object value, Long time) {
        try {
            redisTemplate.opsForValue().set(PREFIX + key, value);
            if (null != time && time > 0) redisTemplate.expire(PREFIX + key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("Redis Error: {}", e.getMessage());
            return false;
        }
    }

    @SneakyThrows
    @Override
    public Object getObject(@NotBlank String key) {
        return redisTemplate.opsForValue().get(PREFIX + key);
    }

    public Long nextLong(@NotBlank String key) {
        RedisAtomicLong counter = new RedisAtomicLong(PREFIX + key, redisTemplate.getConnectionFactory());
        Long increment = counter.getAndIncrement();

        return increment;
    }

    @SneakyThrows
    @Override
    public boolean setString(@NotBlank String key, String value) {
        try {
            strTemplate.opsForValue().set(PREFIX + key, value);
            return true;
        } catch (Exception e) {
            log.error("Redis Error: {}", e.getMessage());
            return false;
        }
    }

    @SneakyThrows
    @Override
    public String getString(@NotBlank String key) {
        return strTemplate.opsForValue().get(PREFIX + key);
    }

    @SneakyThrows
    @Override
    public <T> boolean setModel(@NotBlank String key, @NotNull T model) {
        return this.setModel(key, model, null);
    }

    @SneakyThrows
    @Override
    public <T> boolean setModel(@NotBlank String key, @NotNull T model, Long expireTime) {
        try {
            String value = JSONObject.toJSONString(model);
            this.setString(key, value);
            if (null != expireTime && expireTime > 0) redisTemplate.expire(PREFIX + key, expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("Redis Error: {}", e.getMessage());
            return false;
        }
    }

    @SneakyThrows
    @Override
    public <T> T getModel(@NotBlank String key, Class<T> modelClass) {
        Object value = strTemplate.opsForValue().get(PREFIX + key);
        if (null == value) return null;
        return JSONObject.parseObject(value.toString(), modelClass);
    }

    @SneakyThrows
    @Override
    public <T> boolean setModelList(@NotBlank String key, @NotNull List<T> modelList) {
        return this.setModelList(PREFIX + key, modelList, null);
    }

    @SneakyThrows
    @Override
    public <T> boolean setModelList(@NotBlank String key, @NotNull List<T> modelList, Long expireTime) {
        try {
            String value = JSONArray.toJSONString(modelList);
            this.setString(key, value);
            if (null != expireTime && expireTime > 0) redisTemplate.expire(PREFIX + key, expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("Redis Error: {}", e.getMessage());
            return false;
        }
    }

    @SneakyThrows
    @Override
    public <T> List<T> getModelList(@NotBlank String key, Class<T> modelClass) {
        Object value = strTemplate.opsForValue().get(PREFIX + key);
        if (null == value) return null;
        return JSONArray.parseArray(value.toString(), modelClass);
    }
}
