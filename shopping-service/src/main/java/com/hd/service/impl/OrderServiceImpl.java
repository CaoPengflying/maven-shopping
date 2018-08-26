package com.hd.service.impl;

import com.github.pagehelper.PageHelper;
import com.hd.entity.Order;
import com.hd.mapper.OrderMapper;
import com.hd.service.OrderService;
import com.hd.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;
    @Override
    public List<Order> getOrdersByUserId(Integer currentPage, Integer pageSize, Integer userId) {
        PageHelper.startPage(currentPage, pageSize);
        List<Order> orderList = orderMapper.selectByUserId(userId);
        return orderList;
    }

    @Override
    public List<Order> getAllOrders(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return orderMapper.selectAll();
    }
}
