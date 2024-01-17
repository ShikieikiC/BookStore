package org.shiki.controller;

import org.shiki.entity.FoodOrder;
import org.shiki.service.FoodOrderService;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;

@RestController
@RequestMapping("/foodOrder")
public class FoodOrderController {
    @Resource
    FoodOrderService foodOrderService;

    @RequestMapping("/add")
    public ResData add(@RequestBody FoodOrder foodOrder) throws ParseException {
        String orderNum = foodOrderService.add(foodOrder);
        return ResData.success(orderNum);
    }

    @RequestMapping("/update")
    public ResData update(@RequestBody FoodOrder foodOrder) {
        foodOrderService.update(foodOrder);
        return ResData.success();
    }

    @RequestMapping("/queryByOrderNum")
    public ResData queryByOrderNum(String orderNum) {
        FoodOrder foodOrder = foodOrderService.queryByOrderNum(orderNum);
        return ResData.success(foodOrder);
    }
}
