package com.task.three.springboottask.model;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.task.three.springboottask.controller.UserController;
import com.task.three.springboottask.repository.UserRepo;

import ch.qos.logback.classic.Logger;

@Service
@Component
public class KafkaConsumer {

	
	@Autowired
	private UserRepo repository;

	@KafkaListener(topics = "Kafka_Task6", groupId = "group1")
	public void consume(Message message) {
		Logger log = (Logger) LoggerFactory.getLogger(KafkaConsumer.class);

		
		if (message.getAction().equals("CREATE")) {
			log.info("Payload received");
			repository.save(message.user);
			log.info("Created "+message.user);
		}
		if (message.getAction().equals("DELETE")) {
			log.info("ID/Payload received");
			repository.deleteById(message.user.getId());
			log.info("Deleted "+message.getUser());

		}
		if (message.getAction().equals("UPDATE")) {
			log.info("Payload received");
			repository.save(message.user);
			log.info("Updated "+message.user);

		}

	}

}
