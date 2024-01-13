package org.shiki.service;

import org.shiki.entity.ShoppingCart;

import java.text.ParseException;
import java.util.List;

public interface ShoppingCartService {

    void add(ShoppingCart shoppingCart, String token) throws ParseException;


    List<ShoppingCart> queryByIds(List<Integer> ids);

    ShoppingCart queryByUserIdAndBookId(Integer userId, Integer bookId);

    List<ShoppingCart> queryAll(String token) throws ParseException;

    void updateStatusBatch(List<Integer> shoppingCartsIds);
}
