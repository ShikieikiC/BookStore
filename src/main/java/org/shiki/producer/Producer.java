package org.shiki.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Producer {
    @Resource
    RabbitTemplate rabbitTemplate;

    public void send(Object msg, String routingKey, Integer ttl) {
        rabbitTemplate.convertAndSend("delayExchange", routingKey, msg, message -> {
            message.getMessageProperties().setDelay(ttl);
            return message;
        });
    }

}
