package org.shiki.service;

import com.github.pagehelper.PageInfo;
import org.shiki.entity.Food;
import org.shiki.utils.PageData;

public interface FoodService {
    PageInfo<Food> queryAll(PageData<Food> pageData);
}
