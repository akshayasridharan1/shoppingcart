package com.jpaexample.demo.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxId")
    private Tax tax;

    private int totalCartQty;

    private BigDecimal totalTaxAmount;

    private BigDecimal totalCartPrice;

    public Cart() {
    }

    public Cart(List<CartItem> cartItemList, Tax tax, int totalCartQty, BigDecimal totalTaxAmount, BigDecimal totalCartPrice) {
        this.cartItemList = cartItemList;
        this.tax = tax;
        this.totalCartQty = totalCartQty;
        this.totalTaxAmount = totalTaxAmount;
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

        BigDecimal cartTotalPrice =  cartItemList.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        return cartTotalPrice.add(getTotalTaxAmount());

    }

    public void setTotalCartPrice(BigDecimal totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

  /*  public BigDecimal getTotalTaxAmount() {
        return cartItemList.stream().map(CartItem::getTotalPrice).reduce(new BigDecimal(0.125), BigDecimal::multiply);
    }

    */

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalTaxAmount() {
        //return totalTaxAmount;
        return cartItemList.stream().map(CartItem::getTotalPrice).reduce(getTax().getTaxRate(), BigDecimal::multiply);

    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }
}




