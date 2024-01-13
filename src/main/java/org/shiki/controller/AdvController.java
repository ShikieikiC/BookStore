package org.shiki.controller;

import com.github.pagehelper.PageInfo;
import org.shiki.entity.Adv;
import org.shiki.exception.NoSelectException;
import org.shiki.service.AdvService;
import org.shiki.utils.PageData;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/adv")
public class AdvController {
    @Resource
    AdvService advService;

    @RequestMapping("/add")
    public ResData add(@RequestBody Adv adv) {
        advService.add(adv);
        return ResData.success("添加成功");
    }

    @RequestMapping("/update")
    public ResData update(@RequestBody Adv adv) {
        advService.update(adv);
        return ResData.success("修改成功");
    }

    @RequestMapping("/queryAll")
    public ResData queryAdv(@RequestBody PageData<Adv> pageData) {
        PageInfo<Adv> pageInfo = advService.queryAdv(pageData);
        return ResData.success(pageInfo);
    }

    @RequestMapping("/push")
    public ResData push(@RequestBody Adv adv) {
        advService.push(adv);
        return ResData.success("推送成功");
    }

    @RequestMapping("/changeStatus")
    public ResData changeStatus(Integer id) {
        advService.changeStatus(id);
        return ResData.success("状态修改成功");
    }

    @RequestMapping("/deleteBatch")
    public ResData deleteBatch(@RequestBody List<Integer> ids) {
        if (ids.isEmpty()) {
            throw new NoSelectException();
        }
        advService.deleteBatch(ids);
        return ResData.success("删除成功");
    }
}
