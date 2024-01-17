package org.shiki.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shiki.entity.Food;
import org.shiki.mapper.FoodMapper;
import org.shiki.service.FoodService;
import org.shiki.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FoodServiceImpl implements FoodService {
    @Resource
    FoodMapper foodMapper;


    @Override
    public PageInfo<Food> queryAll(PageData<Food> pageData) {
        PageHelper.startPage(pageData.getPageNum(), pageData.getPageSize());
        return new PageInfo<>(foodMapper.queryAll(pageData.getParams()));
    }
}
