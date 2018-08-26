package com.hd.mapper;

import com.hd.entity.UserAddress;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    List<UserAddress> selectAll(Integer userId);
}