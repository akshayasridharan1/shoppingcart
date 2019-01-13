package com.jpaexample.demo.project.repository;

import com.jpaexample.demo.project.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {

}
