package com.jpaexample.demo.project.repository;

import com.jpaexample.demo.project.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    public User findByUserName(String userName);

}
