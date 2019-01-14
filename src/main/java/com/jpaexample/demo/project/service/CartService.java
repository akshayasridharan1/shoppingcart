package com.jpaexample.demo.project.service;

import com.jpaexample.demo.project.exception.ResourceNotFoundException;
import com.jpaexample.demo.project.model.Cart;
import com.jpaexample.demo.project.model.CartItem;
import com.jpaexample.demo.project.repository.CartRepository;
import com.jpaexample.demo.project.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TaxService taxService;

    public Cart createCart()
    {
        Cart cart = new Cart();
        cart.setTotalTaxAmount(BigDecimal.ZERO);
        cart.setTotalCartPrice(BigDecimal.ZERO);
        cart.setTotalCartQty(0);
        cart.setCartItemList(new ArrayList<>());
        cart.setTax(taxService.findByTaxType(Constants.SALES_TYPE));

        return cartRepository.save(cart);
    }

    public void updateCart(Cart cart)
    {

        BigDecimal taxRate=BigDecimal.ZERO;
        taxRate = cart.getTax().getTaxRate();
        cart.setTotalCartQty(cart.getCartItemList().stream().mapToInt(CartItem::getItemQty).sum());
        BigDecimal totalTax = cart.getCartItemList().stream().map(CartItem::getTotalPrice).reduce(taxRate, BigDecimal::multiply);
        cart.setTotalTaxAmount(totalTax);
        cart.setTotalCartPrice((cart.getCartItemList().stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add)).add(totalTax));

        cartRepository.save(cart);
    }

    public Cart findById(Long cartId)
    {
        Cart cart = Optional.ofNullable(cartRepository.findOne(cartId)).orElseThrow(()->new ResourceNotFoundException("Cart id "+cartId+"does not exists"));
        return cart;
    }

    public Iterable<Cart> findAll() { return cartRepository.findAll(); }

}
