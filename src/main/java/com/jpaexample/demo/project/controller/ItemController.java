package com.jpaexample.demo.project.controller;

import com.jpaexample.demo.project.exception.ResourceNotFoundException;
import com.jpaexample.demo.project.model.Item;
import com.jpaexample.demo.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/shoppingcart/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody Item item)
    {
        Item itemObj = itemService.createItem(item);
        return new ResponseEntity<>(itemObj,HttpStatus.CREATED);
    }

    @GetMapping("/get/{itemName}")
    public ResponseEntity<Item> findByItemName(@Valid @PathVariable String itemName)
    {
        Item item = Optional.ofNullable(itemService.findByItemName(itemName))
                    .orElseThrow(()->(new ResourceNotFoundException("Item "+itemName+" does not exist")));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
