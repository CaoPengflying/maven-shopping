package com.hd.controller;

import com.hd.entity.Order;
import com.hd.entity.User;
import com.hd.entity.UserAddress;
import com.hd.queryVo.CartVo;
import com.hd.service.ShoppingCartService;
import com.hd.service.UserAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {
@Resource(name = "shoppingCartService")
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/cart/addCart")
    public @ResponseBody Object addCart(Integer entityId, Integer quantity, HttpSession session){
        Map map = new HashMap();
        CartVo cart = (CartVo) session.getAttribute("cart");
        cart = shoppingCartService.addCart(cart, entityId, quantity);
        session.setAttribute("cart",cart);
        map.put("status",1);
        return map;
    }

    @RequestMapping("/cart/refreshCart")
    public String refreshCart(HttpSession session){
        CartVo cart = (CartVo) session.getAttribute("cart");
        cart = shoppingCartService.caluSum(cart);
        session.setAttribute("cart",cart);
        return "/jsp/common/pre/searchBar";
    }

    @RequestMapping("/sys/cart/toSettlement")
    public String toSettlement(){
        return "jsp/pre/settlement/toSettlement";
    }

    @RequestMapping("/sys/cart/settlement1")
    public String settlement1(){
        return "jsp/pre/settlement/settlement1";
    }

    @Resource(name = "userAddressService")
    private UserAddressService userAddressService;

    /**
     * 核对订单 获得用户的地址 订单等信息
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("sys/cart/settlement2")
    public String settlement2(Model model, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        List<UserAddress>userAddressList = userAddressService.getList(user.getId());
        model.addAttribute("userAddressList",userAddressList);
        return "jsp/pre/settlement/settlement2";
    }

    /**
     * 生成订单
     * @param addressId
     * @param newAddress
     * @param newRemark
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("sys/cart/settlement3")
    public String settlement3(Integer addressId, String newAddress, String newRemark, HttpSession session, Model model){
        try {
            User user = (User) session.getAttribute("loginUser");
            CartVo cart = (CartVo) session.getAttribute("cart");

            Order order = shoppingCartService.addOrder(addressId, newAddress, newRemark, user, cart);
            model.addAttribute("currentOrder",order);
            return "jsp/pre/settlement/settlement3";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "jsp/pre/settlement/settlement3";
    }

    /**
     * 修改购物车商品的数量
     * @param entityId
     * @param quantity
     * @param session
     * @return
     */
    @RequestMapping("/sys/cart/removeCart")
    public @ResponseBody Object removeCart(Integer entityId, Integer quantity, HttpSession session){
        Map map = new HashMap();
        CartVo cart = (CartVo) session.getAttribute("cart");
        try {
            cart = shoppingCartService.updateCart(entityId, quantity, cart);
            session.setAttribute("cart",cart);
            map.put("status",1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","操作失败");

        }
        return map;
    }

}
