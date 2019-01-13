package com.jpaexample.demo.project.service;

import com.jpaexample.demo.project.exception.ResourceNotFoundException;
import com.jpaexample.demo.project.model.CartItem;
import com.jpaexample.demo.project.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    public CartItem addItemsToCart(CartItem cartItem)
    {
        return cartItemRepository.save(cartItem);
    }

    public CartItem findByItemIdAndCartId(Long itemId,Long cartId)
    {
        CartItem cartItem = Optional.ofNullable(cartItemRepository.findByItemItemIdAndCartCartId(itemId,cartId))
                            .orElseThrow(()-> new ResourceNotFoundException("Cart Item not found for Item ID:"+itemId +"and Cart ID:"+cartId));
        return cartItem;
    }


}
