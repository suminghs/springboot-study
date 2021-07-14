package com.example.fuzz.constant;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/7/13 15:29
 */
@Component
@Data
public class RabbitMqConstant {

    public static final String ORDER_EXCHANGE = "order_exchange";
    public static final String QUEUE_ORDER_CREATE = "order_create_queue";
    public static final String QUEUE_ORDER_CANCEL = "order_cancel_queue";

    public static final String ROUTE_ORDER_CREATE = "order.create";
    public static final String ROUTE_ORDER_CANCEL = "order.cancel";

    public static final String ORDER_DELAY_EXCHANGE = "order_delay_exchange";
    public static final String ORDER_DELAY_CREATE_QUEUE = "order_delay_create_queue";
    public static final String ORDER_DELAY_CANCEL_QUEUE = "order_delay_cancel_queue";

}
