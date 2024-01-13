package org.shiki.service.impl;

import lombok.SneakyThrows;
import org.shiki.entity.Book;
import org.shiki.entity.ShoppingCart;
import org.shiki.exception.BookNotExistException;
import org.shiki.exception.SystemBusyException;
import org.shiki.mapper.BookMapper;
import org.shiki.service.BookService;
import org.shiki.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;
    @Resource
    RedisService redisService;
    ReentrantLock lock = new ReentrantLock();

    @Override
    public List<Book> queryAll() {
        return bookMapper.queryAll();
    }

    @SneakyThrows
    @Override
    public Book queryById(Integer id) {
        Book cacheBook = (Book) redisService.getHash("cache_book", "cache_book_" + id);
        if (cacheBook != null) {
            return cacheBook;
        }

        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                Book book = bookMapper.queryById(id);
                redisService.setHash("cache_book",
                        "cache_book_" + id, book == null ? new Book() : book);

                if (book == null) {
                    throw new BookNotExistException();
                }

                return book;
            }
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        throw new SystemBusyException();
    }

    @Override
    public void updateBatch(List<Book> books) throws InterruptedException {
        List<String> cacheBookKeys = books.stream().map(
                book -> "cache_book_" + book.getId()
        ).toList();

        redisService.delete(cacheBookKeys);
        bookMapper.updateBatch(books);
        Thread.sleep(300);
        redisService.delete(cacheBookKeys);
    }

    @Override
    public void updateBatchStoreCount(List<ShoppingCart> shoppingCarts) throws InterruptedException {
        List<String> cacheBookKeys = shoppingCarts.stream().map(
                shoppingCart -> "cache_book_" + shoppingCart.getBookId()
        ).toList();

        redisService.delete(cacheBookKeys);
        bookMapper.updateBatchStoreCount(shoppingCarts);
        Thread.sleep(300);
        redisService.delete(cacheBookKeys);
    }
}
