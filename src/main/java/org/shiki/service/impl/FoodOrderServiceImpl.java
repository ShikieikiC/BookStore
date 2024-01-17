package org.shiki.service.impl;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import org.shiki.entity.FoodOrder;
import org.shiki.mapper.FoodOrderMapper;
import org.shiki.producer.Producer;
import org.shiki.service.FoodOrderService;
import org.shiki.utils.UserContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {
    @Resource
    FoodOrderMapper foodOrderMapper;
    @Resource
    Producer producer;

    @Override
    public String add(FoodOrder foodOrder) throws ParseException {
        String orderNum = String.valueOf(new SnowflakeGenerator().next());
        foodOrder.setOrderNum(orderNum);
        foodOrder.setUserId(UserContext.getUserId());

        producer.send(foodOrder, "addFoodOrder", 0);
        producer.send(orderNum, "checkFoodOrder", 10 * 1000);

        return orderNum;
    }

    @Override
    public FoodOrder queryByOrderNum(String orderNum) {
        return foodOrderMapper.queryByOrderNum(orderNum);
    }

    @Override
    public void update(FoodOrder foodOrder) {
        foodOrderMapper.update(foodOrder);
    }
}
