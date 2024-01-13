package org.shiki.controller;

import org.shiki.entity.KillBook;
import org.shiki.service.KillBookService;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/killBook")
public class KillBookController {
    @Resource
    KillBookService killBookService;

    @RequestMapping("/add")
    public ResData add(@RequestBody KillBook killBook) {
        killBookService.add(killBook);
        return ResData.success("添加成功");
    }

    @RequestMapping("/queryAll")
    public ResData queryAll() {
        List<KillBook> killBooks = killBookService.queryAll();
        return ResData.success(killBooks);
    }

    @RequestMapping("/startKill")
    public ResData startKill(Integer bookId) {
        killBookService.startKill(bookId);
        return ResData.success("抢购成功");
    }


}
