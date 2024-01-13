package org.shiki.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shiki.entity.Item;
import org.shiki.entity.ItemType;
import org.shiki.mapper.ItemMapper;
import org.shiki.service.ItemService;
import org.shiki.service.RedisService;
import org.shiki.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    ItemMapper itemMapper;

    @Resource
    RedisService redisService;

    @Override
    public void add(Item item) {
        itemMapper.add(item);
    }

    @Override
    public PageInfo<Item> queryAll(PageData<Item> pageData) {
        Item item = pageData.getParams();
        PageHelper.startPage(pageData.getPageNum(), pageData.getPageSize());
        return new PageInfo<>(itemMapper.queryAll(item));
    }

    @Override
    public List<ItemType> queryAllType() {
        List<ItemType> cacheTypes = (List<ItemType>) redisService.getValue("cache_types");
        if (cacheTypes != null) {
            return cacheTypes;

        }

        List<ItemType> types = itemMapper.queryAllType();
        redisService.setValue("cache_types", types);

        return types;
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        itemMapper.deleteBatch(ids);
    }


}
