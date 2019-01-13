package com.jpaexample.demo.project.repository;

import com.jpaexample.demo.project.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem,Long> {

    public CartItem findByItemItemIdAndCartCartId(Long itemId,Long cartId);
}
