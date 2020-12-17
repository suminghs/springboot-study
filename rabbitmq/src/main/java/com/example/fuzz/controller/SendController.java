package com.example.fuzz.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.fuzz.config.RabbitMQConfig;
import com.example.fuzz.pojo.Employee;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/12/17 16:32
 */
@RestController
public class SendController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
        return message;
    }

    @GetMapping("/sendObject")
    public String send() {
        Employee employee = new Employee(RandomUtil.randomInt(1, 100), RandomUtil.randomString(5), RandomUtil.randomInt(10, 100), RandomUtil.randomDouble());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, employee);
        return "success";
    }

}
