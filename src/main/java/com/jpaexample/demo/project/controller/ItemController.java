package com.jpaexample.demo.project.controller;

import com.jpaexample.demo.project.exception.UserNotFoundException;
import com.jpaexample.demo.project.model.Item;
import com.jpaexample.demo.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


    public ResponseEntity<Item> findByItemName(@PathVariable String itemName)
    {
        Item item = Optional.ofNullable(itemService.findByItemName(itemName))
                    .orElseThrow(()->(new UserNotFoundException("Item "+itemName+" does not exist")));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
