package org.shiki.service.impl;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shiki.entity.Order;
import org.shiki.entity.OrderItem;
import org.shiki.entity.ShoppingCart;
import org.shiki.entity.dto.OrderDTO;
import org.shiki.exception.OutOfStoreException;
import org.shiki.mapper.OrderMapper;
import org.shiki.service.*;
import org.shiki.utils.PageData;
import org.shiki.utils.UserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;
    @Resource
    ShoppingCartService shoppingCartService;
    @Resource
    OrderItemService orderItemService;
    @Resource
    BookService bookService;

    @Resource
    AddressService addressService;

    @Override
    @Transactional
    public String add(OrderDTO orderDTO) throws ParseException, InterruptedException {
        List<ShoppingCart> shoppingCarts = shoppingCartService.queryByIds(orderDTO.getIds());

        List<ShoppingCart> illegalCarts = shoppingCarts.stream().filter(
                sc -> sc.getBuyNum() > sc.getStoreCount()
        ).toList();

        if (!illegalCarts.isEmpty()) {
            throw new OutOfStoreException(
                    illegalCarts.stream().map(ShoppingCart::getBookName).toList()
            );
        }

        Order order = new Order();
        String orderNum = String.valueOf(new SnowflakeGenerator().next());
        order.setOrderNum(orderNum);
        order.setUserId(UserContext.getUserId());
        order.setAddressId(orderDTO.getAddressId());
        order.setTotalPrice(
                shoppingCarts.stream()
                        .map(sc -> sc.getPrice().multiply(BigDecimal.valueOf(sc.getBuyNum())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        orderMapper.add(order);

        orderItemService.addBatch(
                shoppingCarts.stream().map(sc -> {
                    OrderItem orderItem = new OrderItem();
                    BeanUtils.copyProperties(sc, orderItem);
                    orderItem.setTotalPrice(sc.getPrice().multiply(BigDecimal.valueOf(sc.getBuyNum())));
                    orderItem.setOrderNum(order.getOrderNum());
                    orderItem.setCreateTime(order.getCreateTime());
                    return orderItem;
                }).toList()
        );

        bookService.updateBatchStoreCount(shoppingCarts);

        shoppingCartService.updateStatusBatch(
                shoppingCarts.stream().map(ShoppingCart::getId).toList()
        );

        return orderNum;
    }

    @Override
    public PageInfo<Order> queryOrder(PageData<Order> pageData) throws ParseException {
        Order order = pageData.getParams();
        order.setUserId(UserContext.getUserId());
        PageHelper.startPage(pageData.getPageNum(), pageData.getPageSize());
        return new PageInfo<>(orderMapper.queryOrder(order));
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Override
    public Map<String, Object> queryOrderInfo(String orderNum) {
        return Map.of(
                "orderItems", orderItemService.queryOrderItems(orderNum),
                "address", addressService.queryByOrderNum(orderNum)
        );
    }


}

