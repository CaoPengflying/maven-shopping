package com.hd.service;

import com.hd.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserId(Integer currentPage, Integer pageSize, Integer userId);

    List<Order> getAllOrders(Integer currentPage, Integer pageSize);

}
