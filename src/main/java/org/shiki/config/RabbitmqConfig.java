package org.shiki.config;

import org.shiki.utils.Constant;
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
    public Queue checkFoodOrderQueue() {
        return new Queue("checkFoodOrder");
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
    public Queue addFoodOrderQueue() {
        return new Queue("addFoodOrder");
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange(Constant.DELAY_EXCHANGE, "x-delayed-message", true, false, map);
    }

    @Bean
    public Binding bindingCheck() {
        return BindingBuilder.bind(checkFoodOrderQueue()).to(delayExchange()).with("checkFoodOrder").noargs();
    }

    @Bean
    public Binding bindingCheckFoodOrder() {
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

    @Bean
    public Binding bindingAddFoodOrder() {
        return BindingBuilder.bind(addFoodOrderQueue()).to(delayExchange()).with("addFoodOrder").noargs();
    }


}
