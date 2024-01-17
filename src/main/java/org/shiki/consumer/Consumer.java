package org.shiki.consumer;

import com.rabbitmq.client.Channel;
import org.shiki.entity.FoodOrder;
import org.shiki.entity.KillBook;
import org.shiki.entity.KillOrder;
import org.shiki.entity.Order;
import org.shiki.mapper.FoodOrderMapper;
import org.shiki.mapper.KillBookMapper;
import org.shiki.service.KillOrderService;
import org.shiki.service.OrderService;
import org.shiki.service.RedisService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class Consumer {
    @Resource
    OrderService orderService;
    @Resource
    KillBookMapper killBookMapper;
    @Resource
    KillOrderService killOrderService;
    @Resource
    RedisService redisService;
    @Resource
    FoodOrderMapper foodOrderMapper;

    @RabbitListener(queues = "check")
    public void check(String orderNum, Channel channel, Message message) throws IOException {
        try {
            Order order = orderService.queryByOrderNum(orderNum);
            if (order.getStatus() == 1) {
                order.setStatus(4);
                orderService.update(order);
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "checkFoodOrder")
    public void checkFoodOrder(String orderNum, Channel channel, Message message) throws IOException {
        try {
            FoodOrder foodOrder = foodOrderMapper.queryByOrderNum(orderNum);
            if (foodOrder.getStatus() == 1) {
                foodOrder.setStatus(3);
                foodOrderMapper.update(foodOrder);
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "addKill")
    public void addKill(KillBook killBook, Channel channel, Message message) throws IOException {
        try {
            killBookMapper.add(killBook);
            redisService.setHash("cache_kill", "cache_kill_" + killBook.getBookId(), killBook);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "syncKill")
    public void syncKill(Integer killBookId, Channel channel, Message message) throws IOException {
        try {
            KillBook killBook = (KillBook) redisService.getHash("cache_kill", "cache_kill_" + killBookId);
            killBookMapper.update(killBook);
            redisService.delete("cache_kill", "cache_kill_" + killBookId);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "addKillOrder")
    public void addKillOrder(KillOrder killOrder, Channel channel, Message message) throws IOException {
        try {
            if (redisService.setValueAbsent(killOrder.getKillOrderNum(), "avoid duplication")) {
                killOrderService.add(killOrder);
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "addFoodOrder")
    public void addFoodOrder(FoodOrder foodOrder, Channel channel, Message message) throws IOException {
        try {
            if (redisService.setValueAbsent(foodOrder.getOrderNum(), "avoid duplication")) {
                foodOrderMapper.add(foodOrder);
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }


}
