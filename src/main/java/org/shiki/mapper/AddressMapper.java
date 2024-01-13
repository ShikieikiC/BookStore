package org.shiki.mapper;

import org.shiki.entity.Address;

import java.util.List;

public interface AddressMapper {
    List<Address> queryByUserId(Integer userId);

    Address queryByOrderNum(String orderNum);
}
