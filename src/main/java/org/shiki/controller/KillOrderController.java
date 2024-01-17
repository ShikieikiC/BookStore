package org.shiki.controller;

import org.shiki.entity.KillOrder;
import org.shiki.service.KillOrderService;
import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/killOrder")
public class KillOrderController {
    @Resource
    KillOrderService killOrderService;

    @RequestMapping("/queryByOrderNum")
    public ResData queryKillOrder(String orderNum) {
        KillOrder killOrder = killOrderService.queryByOrderNum(orderNum);
        return ResData.success(killOrder);
    }
}
