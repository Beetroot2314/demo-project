package com.task.three.springboottask.serviceprod;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.task.springboottask.dto.UserDto;
import com.task.three.springboottask.model.Message;
import com.task.three.springboottask.model.User;

public interface UserProducerService {

	void saveUser(Message message);

	void updateUser(Message message);

//	List<User> getAllUsers();

	boolean isMessageInvalid(Message message);

//	UserDto getUser(int id);

	void deleteUser(int id);

	Page<UserDto> getUsersPage(int pageNumber, int pageSize, int id);
}
