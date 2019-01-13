package com.jpaexample.demo.project.controller;

import com.jpaexample.demo.project.model.Cart;
import com.jpaexample.demo.project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingcart/cart/")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<Cart> CreateEmptyCart()
    {
        Cart cart = new Cart();
        Cart cartObj = cartService.createCart(cart);
        return new ResponseEntity<>(cartObj, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Cart>> getAllCart()
    {
        Iterable<Cart> cartList = cartService.findAll();
        return new ResponseEntity<> (cartList,HttpStatus.OK);
    }

}
