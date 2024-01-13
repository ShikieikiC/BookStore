package org.shiki.service;

import org.shiki.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    void addBatch(List<OrderItem> orderItems);

    List<OrderItem> queryOrderItems(String orderNum);
}
