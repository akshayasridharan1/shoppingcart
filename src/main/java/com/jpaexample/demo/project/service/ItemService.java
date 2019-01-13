package com.jpaexample.demo.project.service;

import com.jpaexample.demo.project.exception.ResourceNotFoundException;
import com.jpaexample.demo.project.model.Item;
import com.jpaexample.demo.project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item)
    {
        return itemRepository.save(item);
    }

    public Item findByItemName(String itemName)
    {
        Item item = Optional.ofNullable(itemRepository.findByItemName(itemName))
                    .orElseThrow(()-> new ResourceNotFoundException("Item : "+itemName+" doesn't exist"));
        return item;
    }
}
