package org.shiki.mapper;

import org.shiki.entity.Adv;

import java.util.List;

public interface AdvMapper {
    void add(Adv adv);

    List<Adv> queryAdv(Adv adv);


    void setPushTime(Adv adv);

    void setStatus(Integer id);

    void deleteBatch(List<Integer> ids);

    void update(Adv adv);
}
