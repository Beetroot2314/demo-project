package com.task.three.springboottask.model;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.task.three.springboottask.controller.UserController;
import com.task.three.springboottask.repository.UserRepo;
import com.task.three.springboottask.service.UserService;

import ch.qos.logback.classic.Logger;

@Service
@Component
@RestController
public class KafkaConsumer {

	@Autowired
	UserService service;

	@KafkaListener(topics = "Kafka_Task6", groupId = "group1")
	public void consume(Message message) {
		Logger log = (Logger) LoggerFactory.getLogger(KafkaConsumer.class);

		if (message.getAction().equals("CREATE")) {
			log.info("Payload received");
			Create(message.user);

		}
		if (message.getAction().equals("DELETE")) {
			log.info("ID/Payload received");
			Delete(message.user);

		}
		if (message.getAction().equals("UPDATE")) {
			log.info("Payload received");
			Update(message.user);

		}
	}

	public void Create(User user) {
		service.CreateUser(user);

	}

	public void Update(User user) {
		service.UpdateUser(user);

	}

	public void Delete(User user) {
		service.DeleteUser(user);

	}

}
