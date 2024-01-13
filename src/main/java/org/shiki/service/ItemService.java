package org.shiki.service;

import com.github.pagehelper.PageInfo;
import org.shiki.entity.Item;
import org.shiki.entity.ItemType;
import org.shiki.utils.PageData;

import java.util.List;

public interface ItemService {
    void add(Item item);

    PageInfo<Item> queryAll(PageData<Item> pageData);

    List<ItemType> queryAllType();

    void deleteBatch(List<Integer> ids);

}
