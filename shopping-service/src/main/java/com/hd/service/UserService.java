package com.hd.service;

import com.hd.entity.User;

import java.util.List;

public interface UserService {
    User getUserByUserName(String userName);

    Integer addUser(User user);

    List<User> getUserList(Integer currentPage, Integer pageSize);

    Integer deleteUserById(Integer id);

    Integer updateUser(User user);

    User getUserById(Integer id);
}
