package org.shiki.service.impl;

import org.shiki.entity.OrderItem;
import org.shiki.mapper.OrderItemMapper;
import org.shiki.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Resource
    OrderItemMapper orderItemMapper;


    @Override
    public void addBatch(List<OrderItem> orderItems) {
        orderItemMapper.addBatch(orderItems);
    }

    @Override
    public List<OrderItem> queryOrderItems(String orderNum) {
        return orderItemMapper.queryOrderItems(orderNum);
    }


}
