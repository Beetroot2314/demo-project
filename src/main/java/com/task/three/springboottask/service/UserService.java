package com.task.three.springboottask.service;

import java.util.List;
import java.util.Optional;

import com.task.three.springboottask.model.Message;
import com.task.three.springboottask.model.User;



public interface UserService {

	User CreateUser(User user);

	User DeleteUser(User user);

	User UpdateUser(User user);

	List<User> GetAll();

	Optional<User> GetUser(int id);
}
