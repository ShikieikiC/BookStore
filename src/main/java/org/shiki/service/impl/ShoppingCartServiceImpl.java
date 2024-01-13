package org.shiki.service.impl;

import org.shiki.entity.ShoppingCart;
import org.shiki.mapper.ShoppingCartMapper;
import org.shiki.service.BookService;
import org.shiki.service.ShoppingCartService;
import org.shiki.utils.UserContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    ShoppingCartMapper shoppingCartMapper;

    @Resource
    BookService bookService;

    @Override
    public void add(ShoppingCart shoppingCart, String token) throws ParseException {
        Integer userId = UserContext.getUserId();
        ShoppingCart existingShoppingCart = queryByUserIdAndBookId(userId, shoppingCart.getBookId());
        BigDecimal price = bookService.queryById(shoppingCart.getBookId()).getPrice();

        shoppingCart.setBuyNum(shoppingCart.getBuyNum()
                + (existingShoppingCart == null ? 0 : existingShoppingCart.getBuyNum()));
        shoppingCart.setBuyPrice(price);
        shoppingCart.setTotalPrice(price.multiply(BigDecimal.valueOf(shoppingCart.getBuyNum())));

        if (existingShoppingCart == null) {
            shoppingCart.setUserId(userId);
            shoppingCartMapper.add(shoppingCart);
        } else {
            shoppingCart.setId(existingShoppingCart.getId());
            shoppingCartMapper.update(shoppingCart);
        }
    }

    @Override
    public List<ShoppingCart> queryByIds(List<Integer> ids) {
        return shoppingCartMapper.queryByIds(ids);
    }

    @Override
    public ShoppingCart queryByUserIdAndBookId(Integer userId, Integer bookId) {
        return shoppingCartMapper.queryByUserIdAndBookId(userId, bookId);
    }

    @Override
    public List<ShoppingCart> queryAll(String token) throws ParseException {
        Integer userId = UserContext.getUserId();
        return shoppingCartMapper.queryByUserId(userId);
    }

    @Override
    public void updateStatusBatch(List<Integer> shoppingCartsIds) {
        shoppingCartMapper.updateStatusBatch(shoppingCartsIds);
    }

}
