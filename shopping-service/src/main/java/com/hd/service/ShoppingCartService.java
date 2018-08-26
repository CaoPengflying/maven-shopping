package com.hd.service;

import com.hd.entity.Order;
import com.hd.entity.User;
import com.hd.queryVo.CartVo;

public interface ShoppingCartService {
    CartVo addCart(CartVo cart, Integer entityId, Integer quantity);

    CartVo caluSum(CartVo cartVo);

    CartVo updateCart(Integer entityId, Integer quantity, CartVo cart);

    Order addOrder(Integer addressId, String newAddress, String newRemark, User user, CartVo cart);
}
