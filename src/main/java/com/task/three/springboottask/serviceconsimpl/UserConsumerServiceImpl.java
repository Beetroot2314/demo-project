package com.task.three.springboottask.serviceconsimpl;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.springboottask.converter.EntityDtoConverter;
import com.task.springboottask.dto.UserDto;
import com.task.three.springboottask.model.KafkaConsumer;
import com.task.three.springboottask.model.User;
import com.task.three.springboottask.repository.UserRepo;
import com.task.three.springboottask.servicecons.UserConsumerService;

//import ch.qos.logback.classic.Logger;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserConsumerServiceImpl implements UserConsumerService {


	private UserRepo repository;

	public UserConsumerServiceImpl(UserRepo repository) {
		super();
		this.repository = repository;
	}

	@Override
	public void createUser(User user) {
		repository.save(user);
		log.info("Created " + user);
	}

	@Override
	public void deleteUser(User user) {
		repository.deleteById(user.getId());
		log.info("Deleted User With Id " + user.getId());
	}

	@Override
	public void updateUser(User user) {
		repository.save(user);
		log.info("Updated " + user);
	}

}
