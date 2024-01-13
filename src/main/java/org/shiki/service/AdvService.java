package org.shiki.service;

import com.github.pagehelper.PageInfo;
import org.shiki.entity.Adv;
import org.shiki.utils.PageData;

import java.util.List;

public interface AdvService {
    void add(Adv adv);

    PageInfo<Adv> queryAdv(PageData<Adv> pageData);

    void push(Adv adv);

    void changeStatus(Integer id);

    void deleteBatch(List<Integer> ids);

    void update(Adv adv);
}
