package com.jpaexample.demo.project.controller;

import com.jpaexample.demo.project.exception.ResourceNotFoundException;
import com.jpaexample.demo.project.model.User;
import com.jpaexample.demo.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shoppingcart/user")
public class UserController {

    @Autowired
    UserRepository  userRepository;

    @PostMapping("/create")
    ResponseEntity<User> createUser(@RequestBody User user)
    {
        User userObj = userRepository.save(user);
        return new ResponseEntity<> (userObj,HttpStatus.CREATED);
    }

    @GetMapping("/get/{userName}")
    ResponseEntity<User> findUserByName(@PathVariable String userName)
    {
        User userObj = Optional.ofNullable(userRepository.findByUserName(userName))
                .orElseThrow(()-> new ResourceNotFoundException("User " +userName +"is not available"));
        return new ResponseEntity<> (userObj,HttpStatus.OK);
    }
}

