package org.shiki.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiki.entity.OrderItem;

import java.util.List;

public interface OrderItemMapper {
    void addBatch(@Param("orderItems") List<OrderItem> orderItems);

    List<OrderItem> queryOrderItems(String orderNum);
}
