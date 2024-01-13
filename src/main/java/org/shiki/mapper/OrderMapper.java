package org.shiki.mapper;

import org.shiki.entity.Order;

import java.util.List;

public interface OrderMapper {

    void add(Order order);

    List<Order> queryOrder(Order order);

    void update(Order order);
}
