package org.shiki.service.impl;

import org.shiki.entity.Address;
import org.shiki.mapper.AddressMapper;
import org.shiki.service.AddressService;
import org.shiki.utils.UserContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressMapper addressMapper;


    @Override
    public List<Address> queryAll(String token) throws ParseException {
        Integer userId = UserContext.getUserId();
        return addressMapper.queryByUserId(userId);
    }

    @Override
    public Address queryByOrderNum(String orderNum) {
        return addressMapper.queryByOrderNum(orderNum);
    }
}
