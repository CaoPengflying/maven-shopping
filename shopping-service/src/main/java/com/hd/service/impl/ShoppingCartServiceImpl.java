package com.hd.service.impl;

import com.hd.entity.*;
import com.hd.mapper.OrderDetailMapper;
import com.hd.mapper.OrderMapper;
import com.hd.queryVo.CartVo;
import com.hd.queryVo.ProductVo;
import com.hd.service.ProductService;
import com.hd.service.ShoppingCartService;
import com.hd.service.UserAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource(name = "productService")
    ProductService productService;

    @Override
    public CartVo addCart(CartVo cart, Integer entityId, Integer quantity) {
        if (cart == null){
            cart = new CartVo();
        }
        Product p = productService.getProductById(entityId);
        for (ProductVo vo : cart.getItems()   ) {
            if (p.getId().equals(vo.getProduct().getId())){
                vo.setQuantity(vo.getQuantity()+1);
                vo.setCost(vo.getCost()+p.getPrice());
                return cart;
            }

        }
        ProductVo productVo = new ProductVo();
        productVo.setProduct(p);
        productVo.setQuantity(quantity);
        productVo.setCost(p.getPrice()*quantity);
        cart.getItems().add(productVo);
        return cart;
    }

    @Override
    public CartVo caluSum(CartVo cartVo) {
        Float sum = 0.0f;
        for (ProductVo productVo : cartVo.getItems()) {
            sum += productVo.getProduct().getPrice()*productVo.getQuantity();
        }
        cartVo.setSum(sum);
        return cartVo;
    }

    @Override
    public CartVo updateCart(Integer entityId, Integer quantity, CartVo cart) {
        if (quantity == 0){
            for (ProductVo vo : cart.getItems()) {
                if (vo.getProduct().getId().equals(entityId)){
                    cart.getItems().remove(vo);
                    cart = caluSum(cart);
                    return cart;
                }
            }
        }
        for (ProductVo vo : cart.getItems()){
            if (vo.getProduct().getId().equals(entityId)){
                vo.setQuantity(quantity);
                vo.setCost(vo.getProduct().getPrice()*quantity);
                cart = caluSum(cart);
                return cart;
            }
        }
        return cart;
    }
    @Resource(name = "userAddressService")
    private UserAddressService userAddressService;
    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;
    @Resource(name =  "orderDetailMapper")
    private OrderDetailMapper orderDetailMapper;
    @Override
    public Order addOrder(Integer addressId, String newAddress, String newRemark, User user, CartVo cart) {
        UserAddress userAddress = userAddressService.confirmAddress(addressId, newAddress, newRemark, user);

        Order order = new Order();
        order.setCost(cart.getSum());
        order.setUserId(user.getId());
        Date date = new Date();
        order.setCreateTime(date);
        order.setLoginName(user.getLoginName());
        order.setUserAddress(userAddress.getAddress());
        UUID uuid = UUID.randomUUID();
        order.setSerialnumber(uuid.toString());
        orderMapper.insertSelective(order);

        for (ProductVo vo : cart.getItems()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCost(vo.getCost());
            orderDetail.setQuantity(vo.getQuantity());
            orderDetail.setOrderId(order.getId());
            orderDetail.setProductId(vo.getProduct().getId());
            orderDetailMapper.insertSelective(orderDetail);
        }
        return order;

    }
}
