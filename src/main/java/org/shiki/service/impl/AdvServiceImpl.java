package org.shiki.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shiki.entity.Adv;
import org.shiki.mapper.AdvMapper;
import org.shiki.service.AdvService;
import org.shiki.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdvServiceImpl implements AdvService {
    @Resource
    AdvMapper advMapper;

    @Override
    public void add(Adv adv) {
        advMapper.add(adv);
    }

    @Override
    public PageInfo<Adv> queryAdv(PageData<Adv> pageData) {
        PageHelper.startPage(pageData.getPageNum(), pageData.getPageSize());
        return new PageInfo<>(advMapper.queryAdv(pageData.getParams()));
    }

    @Override
    public void push(Adv adv) {
        advMapper.setPushTime(adv);
    }

    @Override
    public void changeStatus(Integer id) {
        advMapper.setStatus(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        advMapper.deleteBatch(ids);
    }

    @Override
    public void update(Adv adv) {
        advMapper.update(adv);
    }
}
