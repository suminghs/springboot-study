package com.example.fuzz;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/6/30 16:03
 */
public class RedisLock {
    private String LOCK_KEY = "LOCK_KEY";

    private long IN;

    private long timeout = 1000;

    Jedis jedis;

    public RedisLock() {
        this.jedis = JredisUtil.getClient();
    }


    /**
     * 加锁
     *
     * @param id
     * @return
     */
    public boolean lock(String id) {
        Long start = System.currentTimeMillis();
        try {
            for (; ; ) {
                //SET命令返回OK ，则证明获取锁成功
                String lock = jedis.set(LOCK_KEY, id);
                if ("OK".equals(lock)) {
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close();
        }
    }

    /**
     * 解锁
     *
     * @param id
     * @return
     */
    public boolean unlock(String id) {
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        try {
            String result = jedis.eval(script, Collections.singletonList(LOCK_KEY), Collections.singletonList(id)).toString();
            return "1".equals(result) ? true : false;
        } finally {
            jedis.close();
        }
    }
}
