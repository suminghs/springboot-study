package com.example.fuzz.listener;

import cn.hutool.core.lang.Console;
import com.example.fuzz.config.RabbitMQConfig;
import com.example.fuzz.pojo.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/12/17 16:27
 */
@Component
@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
public class DefaultQueue {

//    @RabbitHandler
//    public void consumeMessage(String message) {
//        Console.log("consume message {}", message);
//    }

    @RabbitHandler
    public void consumeMessage(Employee o) {
        Console.log("consume message {}", o.toString());
    }
}
