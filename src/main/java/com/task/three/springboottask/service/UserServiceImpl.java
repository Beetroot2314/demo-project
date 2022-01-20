package com.task.three.springboottask.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.springboottask.converter.Convert;
import com.task.springboottask.dto.UserDto;
import com.task.three.springboottask.model.KafkaConsumer;
import com.task.three.springboottask.model.User;
import com.task.three.springboottask.repository.UserRepo;

import ch.qos.logback.classic.Logger;
import lombok.NoArgsConstructor;


@Service
public class UserServiceImpl implements UserService{
	
	User user = new User();
	
	
	private UserRepo repository;
	
	public UserServiceImpl(UserRepo repository) {
		super();
		this.repository=repository;
	}
	
	
	
	
	Logger log = (Logger) LoggerFactory.getLogger(UserService.class);
	
	@Override
	public User CreateUser(User user)
	{
		repository.save(user);
		log.info("Created "+user);
		return user;
	}
	
	@Override
	public User DeleteUser(User user)
	{
		repository.deleteById(user.getId());
		log.info("Deleted "+user);
		return user;
	}
	
	@Override
	public User UpdateUser(User user)
	{
		repository.save(user);
		log.info("Updated "+user);
		return user;
	}
	
	@Override
	public List<User> GetAll()
	{
		return repository.findAll(); 
	}

	@Override
	public UserDto GetUser(int id)
	{
		return Convert.ToDto(repository.findById(id).orElse(null));
		
	}
	

}
