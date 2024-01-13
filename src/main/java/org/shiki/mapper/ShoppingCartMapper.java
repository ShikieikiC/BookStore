package org.shiki.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiki.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartMapper {
    void add(ShoppingCart shoppingCart);

    ShoppingCart queryByUserIdAndBookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    List<ShoppingCart> queryByIds(@Param("ids") List<Integer> ids);

    void update(ShoppingCart ShoppingCart);

    void updateStatusBatch(@Param("ids") List<Integer> ids);

    List<ShoppingCart> queryByUserId(Integer userId);
}
