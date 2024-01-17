package org.shiki.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue checkOrderQueue() {
        return new Queue("check");
    }

    @Bean
    public Queue addKillBookQueue() {
        return new Queue("addKill");
    }

    @Bean
    public Queue syncKillBookQueue() {
        return new Queue("syncKill");
    }

    @Bean
    public Queue addKillOrderQueue() {
        return new Queue("addKillOrder");
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange("delayExchange", "x-delayed-message", true, false, map);
    }

    @Bean
    public Binding bindingCheck() {
        return BindingBuilder.bind(checkOrderQueue()).to(delayExchange()).with("check").noargs();
    }

    @Bean
    public Binding bindingAddKill() {
        return BindingBuilder.bind(addKillBookQueue()).to(delayExchange()).with("addKill").noargs();
    }

    @Bean
    public Binding bindingSyncKill() {
        return BindingBuilder.bind(syncKillBookQueue()).to(delayExchange()).with("syncKill").noargs();
    }

    @Bean
    public Binding bindingAddKillOrder() {
        return BindingBuilder.bind(addKillOrderQueue()).to(delayExchange()).with("addKillOrder").noargs();
    }

}
