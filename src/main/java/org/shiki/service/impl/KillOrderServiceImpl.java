package org.shiki.service.impl;

import org.shiki.entity.KillOrder;
import org.shiki.mapper.KillOrderMapper;
import org.shiki.service.KillOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KillOrderServiceImpl implements KillOrderService {
    @Resource
    KillOrderMapper killOrderMapper;

    @Override
    public void add(KillOrder killOrder) {
        killOrderMapper.add(killOrder);
    }

    @Override
    public KillOrder queryByOrderNum(String orderNum) {
        return killOrderMapper.queryByOrderNum(orderNum);
    }
}
