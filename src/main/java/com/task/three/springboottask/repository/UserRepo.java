package com.task.three.springboottask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.task.three.springboottask.model.User;

public interface UserRepo extends MongoRepository<User, Integer> {

}
