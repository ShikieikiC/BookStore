package org.shiki.service;

import org.shiki.entity.Address;

import java.text.ParseException;
import java.util.List;

public interface AddressService {

    List<Address> queryAll(String token) throws ParseException;

    Address queryByOrderNum(String orderNum);
}
