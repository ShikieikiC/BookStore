package org.shiki.controller;

import org.shiki.entity.ShoppingCart;
import org.shiki.service.ShoppingCartService;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Resource
    ShoppingCartService shoppingCartService;

    @RequestMapping("/add")
    public ResData add(@RequestBody ShoppingCart shoppingCart, HttpServletRequest request) throws ParseException {
        shoppingCartService.add(shoppingCart, request.getHeader("token"));
        return ResData.success("添加成功");
    }


}
