package org.shiki.controller;

import org.shiki.entity.Book;
import org.shiki.service.BookService;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @RequestMapping("/queryAll")
    public ResData queryAll() {
        List<Book> books = bookService.queryAll();
        return ResData.success(books);
    }

    @RequestMapping("/bookInfo/{id}")
    public ResData bookInfo(@PathVariable("id") Integer id) {
        Book book = bookService.queryById(id);
        return ResData.success(book);
    }


}
