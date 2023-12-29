package com.mtgo.exam.restaurantservice.consumer;

import com.mtgo.exam.restaurantservice.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {
    @RabbitListener(queues = MQConfig.ORDER_PLACED_QUEUE)
    public void listener (OrderPlacedMessage orderPlacedMessage){
        System.out.println(orderPlacedMessage);
        log.info("New Order with Order Number: {} has been placed", orderPlacedMessage.getOrderNumber());
    }
}
