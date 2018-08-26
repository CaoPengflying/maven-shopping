package com.hd.service.impl;

import com.hd.entity.User;
import com.hd.entity.UserAddress;
import com.hd.mapper.UserAddressMapper;
import com.hd.service.UserAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {
    @Resource(name = "userAddressMapper")
    private UserAddressMapper userAddressMapper;
    @Override
    public UserAddress confirmAddress(Integer addressId, String newAddress, String newRemark, User user) {
        UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
        if (userAddress == null){
            userAddress = new UserAddress();
            userAddress.setAddress(newAddress);
            userAddress.setRemark(newRemark);
            userAddress.setUserId(user.getId());
            userAddressMapper.insertSelective(userAddress);
        }
        return userAddress;
    }

    @Override
    public List<UserAddress> getList(Integer userId) {
        return userAddressMapper.selectAll(userId);
    }
}
