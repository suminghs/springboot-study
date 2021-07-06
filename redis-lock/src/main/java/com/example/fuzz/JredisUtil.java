package com.example.fuzz;

import redis.clients.jedis.Jedis;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/6/30 16:18
 */
public class JredisUtil {

    static Jedis jedis = new Jedis("127.0.0.1", 6379, 1000000000);

    public static Jedis getClient() {
        return jedis;
    }

}
