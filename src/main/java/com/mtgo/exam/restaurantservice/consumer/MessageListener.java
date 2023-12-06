package com.mtgo.exam.restaurantservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {
    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener (OrderPlacedMessage orderPlacedMessage){
        System.out.println(orderPlacedMessage);
        log.info("OrDeRNÅMBER: ", orderPlacedMessage.getOrderNumber());
    }
}
