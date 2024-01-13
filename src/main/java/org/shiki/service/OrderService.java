package org.shiki.service;

import com.github.pagehelper.PageInfo;
import org.shiki.entity.Order;
import org.shiki.entity.dto.OrderDTO;
import org.shiki.utils.PageData;

import java.text.ParseException;
import java.util.Map;

public interface OrderService {
    String add(OrderDTO orderDTO) throws ParseException, InterruptedException;

    PageInfo<Order> queryOrder(PageData<Order> pageData) throws ParseException;

    void update(Order order);

    Map<String, Object> queryOrderInfo(String orderNum);
}
