package com.hd.service.impl;

import com.github.pagehelper.PageHelper;
import com.hd.entity.User;
import com.hd.mapper.UserMapper;
import com.hd.service.UserService;
import com.hd.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(String userName) {
        User user = userMapper.selectByUserName(userName);
        return user;
    }

    @Override
    public Integer addUser(User user) {
        User user1 = userMapper.selectByUserName(user.getLoginName());
        if (user1 != null)
            return 0;
        user.setPassword(SecurityUtil.getHex3Str(user.getPassword()));
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> getUserList(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return userMapper.selectUserList();
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return  userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


}
