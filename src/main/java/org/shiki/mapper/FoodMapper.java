package org.shiki.mapper;

import org.shiki.entity.Food;

import java.util.List;

public interface FoodMapper {
    List<Food> queryAll(Food food);
}
