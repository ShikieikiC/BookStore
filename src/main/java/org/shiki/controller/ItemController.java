package org.shiki.controller;

import com.github.pagehelper.PageInfo;
import org.shiki.entity.Item;
import org.shiki.entity.ItemType;
import org.shiki.exception.NoSelectException;
import org.shiki.service.ItemService;
import org.shiki.utils.PageData;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Resource
    ItemService itemService;

    @RequestMapping("/queryAllType")
    public ResData queryAllType() {
        List<ItemType> types = itemService.queryAllType();
        return ResData.success(types);
    }

    @RequestMapping("/add")
    public ResData add(@RequestBody Item item) {
        itemService.add(item);
        return ResData.success("添加成功");
    }

    @RequestMapping("/queryAll")
    public ResData queryAll(@RequestBody PageData<Item> pageData) {
        PageInfo<Item> items = itemService.queryAll(pageData);
        return ResData.success(items);
    }

    @RequestMapping("/deleteBatch")
    public ResData deleteBatch(@RequestBody List<Integer> ids) {
        if (ids.isEmpty()) {
            throw new NoSelectException();
        }
        itemService.deleteBatch(ids);
        return ResData.success("删除成功");
    }

    @RequestMapping("/delete")
    public ResData delete(Integer id) {
        itemService.deleteBatch(Collections.singletonList(id));
        return ResData.success("删除成功");
    }


}
