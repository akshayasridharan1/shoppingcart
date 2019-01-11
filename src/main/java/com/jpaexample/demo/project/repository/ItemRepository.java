package com.jpaexample.demo.project.repository;

import com.jpaexample.demo.project.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {

   public Item findByItemName(String ItemName);
}
