package org.shiki.service;

import org.shiki.entity.KillBook;

import java.util.List;

public interface KillBookService {
    void add(KillBook killBook);

    List<KillBook> queryAll();

    void startKill(Integer id);
}
