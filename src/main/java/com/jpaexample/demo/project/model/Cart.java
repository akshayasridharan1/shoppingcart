package com.jpaexample.demo.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @JsonManagedReference
    @OneToMany(mappedBy ="cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> cartItemList = new ArrayList<>();

    private int totalCartQty;

    private BigDecimal totalCartPrice;

    public Cart() {
    }

    public Cart(List<CartItem> cartItemList, int totalCartQty, BigDecimal totalCartPrice) {
        this.cartItemList = cartItemList;
        this.totalCartQty = totalCartQty;
        this.totalCartPrice = totalCartPrice;
    }

    public void addCartItem(CartItem cartItem) {
        cartItemList.add( cartItem );
        cartItem.setCart( this );
    }

    public void removeCartItem(CartItem cartItem) {
        cartItemList.remove( cartItem );
        cartItem.setCart( null );
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public int getTotalCartQty() {
        return cartItemList.stream().mapToInt(CartItem::getItemQty).sum();
    }

    public void setTotalCartQty(int totalCartQty) {
        this.totalCartQty = totalCartQty;
    }

    public BigDecimal getTotalCartPrice() {

       /* BigDecimal sum = BigDecimal.ZERO;
        List<CartItem> cartItem = getCartItemList();
        for(CartItem cartItemObj: cartItemList){
            sum.add(cartItemObj.getTotalPrice());
        }
        return sum;*/
    return cartItemList.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setTotalCartPrice(BigDecimal totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }
}
