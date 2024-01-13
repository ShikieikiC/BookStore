package org.shiki.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiki.entity.Book;
import org.shiki.entity.ShoppingCart;

import java.util.List;

public interface BookMapper {
    List<Book> queryAll();

    Book queryById(Integer id);

    void updateBatch(@Param("books") List<Book> books);

    void updateBatchStoreCount(@Param("shoppingCarts") List<ShoppingCart> shoppingCarts);
}
