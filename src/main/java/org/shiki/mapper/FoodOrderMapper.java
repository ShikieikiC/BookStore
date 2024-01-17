package org.shiki.mapper;

import org.shiki.entity.FoodOrder;

public interface FoodOrderMapper {
    void add(FoodOrder foodOrder);

    FoodOrder queryByOrderNum(String orderNum);

    void update(FoodOrder foodOrder);
}
