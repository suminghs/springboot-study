package com.example.fuzz.config;

import com.example.fuzz.constant.RabbitMqConstant;
import org.assertj.core.util.Maps;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/7/14 11:01
 */
public class OrderConfig {

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(RabbitMqConstant.ORDER_EXCHANGE);
    }

    @Bean
    public Queue orderCreateQueue() {
        return new Queue(RabbitMqConstant.QUEUE_ORDER_CREATE);
    }

    @Bean
    public Queue orderCancelQueue() {
        return new Queue(RabbitMqConstant.QUEUE_ORDER_CANCEL);
    }
//
//    @Bean
//    public BindingBuilder.DirectExchangeRoutingKeyConfigurer directBinding(Queue orderCreateQueue, DirectExchange orderExchange) {
//        return BindingBuilder.bind(orderCreateQueue).to(orderExchange);
//    }
//
//    @Bean
//    public BindingBuilder.DirectExchangeRoutingKeyConfigurer directBinding2(Queue orderCancelQueue, DirectExchange orderExchange) {
//        return BindingBuilder.bind(orderCancelQueue).to(orderExchange);
//    }


    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitMqConstant.ORDER_DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }


    @Bean
    public Queue delayCreateQueue() {
        return new Queue(RabbitMqConstant.ORDER_DELAY_CREATE_QUEUE);
    }

    @Bean
    public Queue delayCancelQueue() {
        return new Queue(RabbitMqConstant.ORDER_DELAY_CANCEL_QUEUE);
    }

    @Bean
    public Binding delayBinding(Queue delayCreateQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayCreateQueue).to(delayExchange).with(RabbitMqConstant.ROUTE_ORDER_CREATE).noargs();
    }

    @Bean
    public Binding delayBinding2(Queue delayCancelQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayCancelQueue).to(delayExchange).with(RabbitMqConstant.ROUTE_ORDER_CANCEL).noargs();
    }


}
