package org.shiki.mapper;

import org.shiki.entity.Item;
import org.shiki.entity.ItemType;

import java.util.List;

public interface ItemMapper {
    void add(Item item);

    List<Item> queryAll(Item item);

    List<ItemType> queryAllType();

    void deleteBatch(List<Integer> ids);

}
