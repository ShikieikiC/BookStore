package org.shiki.controller;

import com.github.pagehelper.PageInfo;
import org.shiki.entity.Order;
import org.shiki.entity.dto.OrderDTO;
import org.shiki.exception.NoSelectException;
import org.shiki.service.OrderService;
import org.shiki.utils.PageData;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Resource
    OrderService orderService;


    @RequestMapping("/add")
    public ResData add(@RequestBody OrderDTO order) throws ParseException, InterruptedException {
        if (order.getIds().isEmpty()) {
            throw new NoSelectException();
        }
        String orderNum = orderService.add(order);

        return ResData.success(orderNum);
    }


    @RequestMapping("/queryOrder")
    public ResData queryByUserId(@RequestBody PageData<Order> pageData) throws ParseException {
        PageInfo<Order> orders = orderService.queryOrder(pageData);
        return ResData.success(orders);
    }

    @RequestMapping("queryByOrderNum")
    public ResData queryByOrderNum(String orderNum) {
        Order order = orderService.queryByOrderNum(orderNum);
        return ResData.success(order);
    }

    @RequestMapping("/queryOrderInfo")
    public ResData queryOrderItems(String orderNum) {
        Map<String, Object> orderInfo = orderService.queryOrderInfo(orderNum);
        return ResData.success(orderInfo);
    }

    @RequestMapping("/update")
    public ResData update(@RequestBody Order order) {
        System.err.println(order);
        orderService.update(order);
        return ResData.success();
    }
}
