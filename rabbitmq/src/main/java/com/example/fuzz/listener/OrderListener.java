package com.example.fuzz.listener;

import cn.hutool.core.date.DateUtil;
import com.example.fuzz.constant.RabbitMqConstant;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/7/13 15:51
 */
@Component
public class OrderListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMqConstant.QUEUE_ORDER_CREATE),
            key = {RabbitMqConstant.ROUTE_ORDER_CREATE},
            exchange = @Exchange(name = RabbitMqConstant.ORDER_EXCHANGE, type = "direct")
    ))
    public void createOrder(String message) {
        System.out.println("订单创建" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMqConstant.QUEUE_ORDER_CANCEL),
            key = {RabbitMqConstant.ROUTE_ORDER_CANCEL},
            exchange = @Exchange(name = RabbitMqConstant.ORDER_EXCHANGE, type = "direct")
    ))
    public void cancelOrder(String message) {
        System.out.println("订单取消" + message);
    }


    @RabbitListener(queues = RabbitMqConstant.ORDER_DELAY_CREATE_QUEUE)
    public void delayCreateOrder(String message) {
        System.out.println("订单延时创建" + message + "    当前时间" + DateUtil.now());
    }

    @RabbitListener(queues = RabbitMqConstant.ORDER_DELAY_CANCEL_QUEUE)
    public void delayCancelOrder(String message) {
        System.out.println("订单延时取消" + message + "    当前时间" + DateUtil.now());
    }

}
