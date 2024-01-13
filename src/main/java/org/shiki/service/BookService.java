package org.shiki.service;

import org.shiki.entity.Book;
import org.shiki.entity.ShoppingCart;

import java.util.List;

public interface BookService {
    List<Book> queryAll();

    Book queryById(Integer id);


    void updateBatch(List<Book> books) throws InterruptedException;

    void updateBatchStoreCount(List<ShoppingCart> shoppingCarts) throws InterruptedException;
}
