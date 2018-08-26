package com.hd.controller;

import com.github.pagehelper.PageInfo;
import com.hd.entity.Order;
import com.hd.entity.User;
import com.hd.service.OrderService;
import com.hd.util.Pager;
import com.hd.util.StaticUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 查询自己的订单
     * @param currentPage
     * @param userId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/sys/getOrdersByUserId")
    public String getOrdersById(Integer currentPage, Integer userId, Model model, HttpSession session){
        if (null == currentPage){
            currentPage = 1;
        }
        if(null == userId){
            User u = (User) session.getAttribute("loginUser");
            userId = u.getId();
        }
        Integer pageSize = StaticUtil.ORDER_PAGESIZE;

        List<Order> orderList = orderService.getOrdersByUserId(currentPage, pageSize, userId);
        PageInfo<Order>pageInfo = new PageInfo<>(orderList);
        String url = "sys/getOrdersByUserId.action?1=1";
        Pager pager = new Pager(currentPage,pageInfo.getPages(),url);
        model.addAttribute("pager",pager);
        model.addAttribute("orderList",orderList);
        model.addAttribute("menu",1);
        return "jsp/backend/order/orderList";
    }

    @RequestMapping("/sys/getAllOrders")
    public String getAllOrders(Integer currentPage, Model model){
        if (null == currentPage){
            currentPage = 1;
        }
        List<Order>orderList = orderService.getAllOrders(currentPage, StaticUtil.ORDER_PAGESIZE);
        PageInfo<Order>pageInfo = new PageInfo<>(orderList);
        String url = "sys/getAllOrders.action?1=1";
        Pager pager = new Pager(currentPage,pageInfo.getPages(),url);
        model.addAttribute("pager",pager);
        model.addAttribute("menu",9);
        model.addAttribute("orderList",orderList);
        return "jsp/backend/order/orderList";
    }
}
