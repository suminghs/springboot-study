package com.example.fuzz.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Redis服务接口
 *
 * @author kevin
 * @version 0.1
 * @since 2019-12-25
 */
public interface IRedisService {

    boolean hasKey(@NotBlank String key);

    /**
     * time 是以秒为单位
     * @param key
     * @param value
     * @param time
     * @return
     */
    boolean setObject(@NotBlank String key, Object value, Long time);

    Object getObject(@NotBlank String key);

    /**
     * 自增长获取下一个long数字
     * @param key
     * @return
     */
    Long nextLong(@NotBlank String key);

    boolean setString(@NotBlank String key, String value);

    String getString(@NotBlank String key);

    <T> boolean setModel(@NotBlank String key, @NotNull T model);

    <T> boolean setModel(@NotBlank String key, @NotNull T model, Long expireTime);

    <T> T getModel(@NotBlank String key, Class<T> modelClass);

    <T> boolean setModelList(@NotBlank String key, @NotNull List<T> modelList);

    <T> boolean setModelList(@NotBlank String key, @NotNull List<T> modelList, Long expireTime);

    <T> List<T> getModelList(@NotBlank String key, Class<T> modelClass);
}
