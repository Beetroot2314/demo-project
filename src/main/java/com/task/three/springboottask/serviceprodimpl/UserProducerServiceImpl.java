package com.task.three.springboottask.serviceprodimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.task.springboottask.converter.EntityDtoConverter;
import com.task.springboottask.dto.UserDto;
import com.task.springboottask.excep.MissingDataException;
import com.task.springboottask.excep.ResourceExistsException;
import com.task.three.springboottask.constants.Constants;
import com.task.three.springboottask.controller.UserController;
import com.task.three.springboottask.model.KafkaConsumer;
import com.task.three.springboottask.model.Message;
import com.task.three.springboottask.model.User;
import com.task.three.springboottask.repository.UserRepo;
import com.task.three.springboottask.serviceprod.UserProducerService;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.pattern.Converter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserProducerServiceImpl implements UserProducerService {

	@Autowired
	KafkaTemplate<String, Message> kafkaTemplate;

	@Autowired
	private UserRepo repository;

	private final String TOPIC = "Kafka_Task8";

	@Override
	public void saveUser(Message message) {

		if (isMessageInvalid(message)) {
			throw new MissingDataException("Invalid message. Missing fields in the message received.");
		}
		if (isResourceExist(message) == true) {
			throw new ResourceExistsException("Data with given ID already exists.");
		}
		log.info("Sending Paylod to Topic");
		message.setAction(Constants.CREATE);
		kafkaTemplate.send(TOPIC, message);
	}

	@Override
	public void updateUser(Message message) {

		if (isMessageInvalid(message)) {
			throw new MissingDataException("Invalid message. Missing fields in the message received.");
		}

		log.info("Sending Paylod to Topic");
		message.setAction(Constants.UPDATE);
		kafkaTemplate.send(TOPIC, message);

	}

	@Override
	public Page<UserDto> getUsersPage(int pageNumber, int pageSize, int id) {

		log.info("Fetching requested number of User(s)");
		if (id > 0) {
			List<UserDto> dto = new ArrayList<UserDto>();
			dto.add(EntityDtoConverter.entityToDto(repository.findById(id).orElse(null)));
			Page<UserDto> pageGetId = new PageImpl<>(dto);
			return pageGetId;
		}
		Sort sort = Sort.by(Sort.Direction.ASC, "id");
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		return EntityDtoConverter.pageToDto(repository.findAll(page));
	}

	@Override
	public void deleteUser(int id) {
		if (id <= 0) {
			throw new MissingDataException("Invalid message. Missing fields in the message received.");
		}
		if (repository.existsById(id) == false) {
			throw new MissingDataException("Data with requested ID not found");
		}
		log.info("Sending ID to Topic");
		Message message = new Message();
		UserDto userId = new UserDto();
		userId.setId(id);
		message.setData(userId);
		message.setAction(Constants.DELETE);
		kafkaTemplate.send(TOPIC, message);
	}

	public boolean isMessageInvalid(Message message) {
		if ((message.getData().getEmail() == "") || (message.getData().getId() <= 0)
				|| (message.getData().getUserName() == "") || (message.getData() == null))
			return true;
		return false;

	}

	public boolean isResourceExist(Message message) {
		if (repository.existsById(message.getData().getId()))
			return true;
		return false;
	}

}

//@Override
//public List<User> getAllUsers() // better function names
//{
//	return repository.findAll();
//}
//
//@Override
//public UserDto getUser(int id) {
//	return EntityDtoConverter.toDto(repository.findById(id).orElse(null));
//	// null check in Convert class
//}
