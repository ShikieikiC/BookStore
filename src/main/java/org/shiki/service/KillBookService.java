package org.shiki.service;

import org.shiki.entity.KillBook;

import java.text.ParseException;
import java.util.List;

public interface KillBookService {
    void add(KillBook killBook);

    List<KillBook> queryAll();

    String startKill(Integer id) throws ParseException;

}
