package org.shiki.service;

import org.shiki.entity.FoodOrder;

import java.text.ParseException;

public interface FoodOrderService {
    String add(FoodOrder foodOrder) throws ParseException;

    FoodOrder queryByOrderNum(String orderNum);

    void update(FoodOrder foodOrder);
}
