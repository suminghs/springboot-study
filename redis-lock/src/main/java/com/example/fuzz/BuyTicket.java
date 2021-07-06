package com.example.fuzz;

import lombok.val;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/6/30 15:14
 */
public class BuyTicket {
    private int tickets = 100;
    RedisLock redisLock = new RedisLock();

    Jedis jedis;

    public BuyTicket() {
        this.jedis = new Jedis("127.0.0.1", 6379, 10000);
    }

    public static void main(String[] args) {
//        (new BuyTicket()).test();
        BuyTicket courseTh = new BuyTicket();
//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> {
//                courseTh.buy();
//            }, "线程" + i).start();
//        }
        courseTh.buy();
    }

    public void buy() {
        String name = Thread.currentThread().getName();
        if (tickets <= 0) {
            System.out.println(name + "库存不足");
            return;
        }
        long lock624 = JredisUtil.getClient().setnx("lock624", "2");
        System.out.println(name + ">>>>>>>" + lock624);;
        if (lock624 < 1) {
            System.out.println(name + "购买失败");
            return;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.tickets -= 2;
        JredisUtil.getClient().del("lock624");
        System.out.println(name + "买了2张, 剩余:" + tickets);
    }

    public void test() {
        val setnx = jedis.setnx("333", "dsdddddddd");
        System.out.println(setnx);
    }
}
