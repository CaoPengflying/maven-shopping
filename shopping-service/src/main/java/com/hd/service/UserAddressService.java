package com.hd.service;

import com.hd.entity.User;
import com.hd.entity.UserAddress;

import java.util.List;

public interface UserAddressService {


    UserAddress confirmAddress(Integer addressId, String newAddress, String newRemark, User user);

    List<UserAddress> getList(Integer userId);
}
