package org.shiki.controller;

import com.github.pagehelper.PageInfo;
import org.shiki.entity.Food;
import org.shiki.service.FoodService;
import org.shiki.utils.PageData;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Resource
    FoodService foodService;


    @RequestMapping("/queryAll")
    public ResData queryAll(@RequestBody PageData<Food> pageData) {
        PageInfo<Food> foods = foodService.queryAll(pageData);
        return ResData.success(foods);
    }


}
