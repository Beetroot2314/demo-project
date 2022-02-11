package com.task.three.springboottask.servicecons;

import java.util.List;
import java.util.Optional;

import com.task.springboottask.dto.UserDto;
import com.task.three.springboottask.model.Message;
import com.task.three.springboottask.model.User;



public interface UserConsumerService {

	void createUser(User user);

	void deleteUser(User user);

	void updateUser(User user);

}
