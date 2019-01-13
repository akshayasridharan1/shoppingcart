package com.jpaexample.demo.project.service;

import com.jpaexample.demo.project.exception.ResourceNotFoundException;
import com.jpaexample.demo.project.model.Cart;
import com.jpaexample.demo.project.model.CartItem;
import com.jpaexample.demo.project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(Cart cart)
    {
        return cartRepository.save(cart);
    }

    public void updateCart(Cart cart)
    {
        cart.setTotalCartQty(cart.getCartItemList().stream().mapToInt(CartItem::getItemQty).sum());
        cart.setTotalCartPrice(cart.getCartItemList().stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

         cartRepository.save(cart);
    }

    public Cart findById(Long cartId)
    {
        Cart cart = Optional.ofNullable(cartRepository.findOne(cartId)).orElseThrow(()->new ResourceNotFoundException("Cart id "+cartId+"does not exists"));
        return cart;
    }

    public Iterable<Cart> findAll() { return cartRepository.findAll(); }

}
