package com.example.fuzz.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.fuzz.constant.RabbitMqConstant;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/7/12 14:36
 */
@RestController
@RequestMapping("/pay")
public class OrderController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 下单
    @GetMapping("/do")
    public String pay() {
        String message = RandomUtil.randomNumbers(11);
        rabbitTemplate.convertAndSend(RabbitMqConstant.ORDER_EXCHANGE, RabbitMqConstant.ROUTE_ORDER_CREATE, message);
        return message;
    }

    // 退款
    @GetMapping("/trade")
    public String trade() {
        String message = RandomUtil.randomNumbers(11);
        rabbitTemplate.convertAndSend(RabbitMqConstant.ORDER_EXCHANGE, RabbitMqConstant.ROUTE_ORDER_CANCEL, message);
        return message;
    }

    // 延时下单
    @GetMapping("/delayCreate")
    public String delayCreate() {
        String message = RandomUtil.randomNumbers(11);
        rabbitTemplate.convertAndSend(RabbitMqConstant.ORDER_DELAY_EXCHANGE, RabbitMqConstant.ROUTE_ORDER_CREATE, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 5000);
                return message;
            }
        });
        return message + "    当前时间" + DateUtil.now();
    }

    // 延时退款
    @GetMapping("/delayCancel")
    public String delayCancel() {
        String message = RandomUtil.randomNumbers(11);
        rabbitTemplate.convertAndSend(RabbitMqConstant.ORDER_DELAY_EXCHANGE, RabbitMqConstant.ROUTE_ORDER_CANCEL, message, message1 -> {
            message1.getMessageProperties().setHeader("x-delay", 8000);
            return message1;
        });
        return message + "    当前时间" + DateUtil.now();
    }
}
