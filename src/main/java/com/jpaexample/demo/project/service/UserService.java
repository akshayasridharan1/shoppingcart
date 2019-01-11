package com.jpaexample.demo.project.service;

import com.jpaexample.demo.project.model.User;
import com.jpaexample.demo.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

  public User findUserByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }
}
