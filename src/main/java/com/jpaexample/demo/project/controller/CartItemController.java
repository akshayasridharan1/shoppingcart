package com.jpaexample.demo.project.controller;

import com.jpaexample.demo.project.model.Cart;
import com.jpaexample.demo.project.model.CartItem;
import com.jpaexample.demo.project.model.Item;
import com.jpaexample.demo.project.service.CartItemService;
import com.jpaexample.demo.project.service.CartService;
import com.jpaexample.demo.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/shoppingcart/cartItem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    @PostMapping("/create/{cartId}/{itemName}/add")
    public ResponseEntity<CartItem> addItemsToCart(@PathVariable Long cartId,@PathVariable String itemName,@Valid @RequestBody CartItem cartItem)
    {

       Cart cart = cartService.findById(cartId);
       Item item = itemService.findByItemName(itemName);
      /* CartItem cartItem1 = checkIfItemAlreadyExistsInCart(item.getItemId(),cartId);
       if(Optional.ofNullable(cartItem1).isPresent())
       {
           cartItem.setCartItemId(cartId);
           cartItem.setItemQty(cartItem1.getItemQty() + cartItem.getItemQty());
       }
*/
       cartItem.setCart(cart);
       cartItem.setItem(item);
       CartItem cartItemObj= cartItemService.addItemsToCart(cartItem);

       cartService.updateCart(cart);

       return new ResponseEntity<>(cartItemObj, HttpStatus.CREATED);
    }

    /*public CartItem checkIfItemAlreadyExistsInCart (Long itemId,Long cartId)
    {

       return cartItemService.findByItemIdAndCartId(itemId,cartId);

    }*/
}
