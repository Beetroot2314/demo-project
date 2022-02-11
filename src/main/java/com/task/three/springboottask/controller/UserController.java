package com.task.three.springboottask.controller;

import java.util.List;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.springboottask.converter.EntityDtoConverter;
import com.task.springboottask.dto.UserDto;
import com.task.springboottask.excep.MissingDataException;
import com.task.three.springboottask.model.KafkaConsumer;
import com.task.three.springboottask.model.Message;
import com.task.three.springboottask.model.User;
import com.task.three.springboottask.repository.UserRepo;
import com.task.three.springboottask.servicecons.UserConsumerService;
import com.task.three.springboottask.serviceprod.UserProducerService;

import ch.qos.logback.classic.Logger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import com.task.three.springboottask.constants.Constants;

@Slf4j
@RestController
public class UserController {

	@Autowired
	UserProducerService service;

	@PostMapping("/users")
	public ResponseEntity<Message> saveUser(@RequestBody UserDto data) {

		Message message = new Message();
		message.setData(data);
		service.saveUser(message);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@GetMapping("/users")
	Page<UserDto> getPageUsers(@RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int pageSize,
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = Integer.MIN_VALUE + "") int id) {

		return service.getUsersPage(pageNumber, pageSize, id);

	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {

		service.deleteUser(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@PutMapping("/users/{id}")
	public ResponseEntity updateUser(@PathVariable int id, @RequestBody UserDto data) {

		Message message = new Message();
		message.setData(data);
		service.updateUser(message);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

}

// kafka-commands
// .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic
// Kafka_Task6
// .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181
// --replication-factor 1 --partitions 1 --topic Kafka_Task6
// .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092
// --topic Kafka_Task6 --from-beginning
// {"id":13,"userName":"Amber","email":"Nillohit123@gmail.com"}

//@GetMapping("/users")
//public ResponseEntity<List<UserDto>> getUsers() {
//	log.info("Fetching all users");
//	return new ResponseEntity<List<UserDto>>(EntityDtoConverter.toDto(service.getAllUsers()), HttpStatus.ACCEPTED);
//}
//
//@GetMapping("/users/{id}")
//public ResponseEntity<UserDto> getUser(@PathVariable int id) {
//	log.info("Fetching User with ID " + id);
//	return new ResponseEntity<UserDto>(service.getUser(id), HttpStatus.ACCEPTED);
//}
