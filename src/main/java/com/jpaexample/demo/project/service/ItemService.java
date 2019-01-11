package com.jpaexample.demo.project.service;

import com.jpaexample.demo.project.model.Item;
import com.jpaexample.demo.project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item findByItemName(String itemName)
    {
       return itemRepository.findByItemName(itemName);
    }
}
