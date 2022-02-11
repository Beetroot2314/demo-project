package com.task.three.springboottask.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.task.springboottask.converter.EntityDtoConverter;
import com.task.three.springboottask.constants.Constants;
import com.task.three.springboottask.servicecons.UserConsumerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(prefix ="consumer",name="enable",havingValue="true") 
public class KafkaConsumer {

	@Autowired
	UserConsumerService service;

	
	@KafkaListener(topics = "Kafka_Task8", groupId = "group1")
	public void consume(Message message) {
		
		
		if (message.getAction().equals(Constants.CREATE)) { 
			log.info("Payload received");
			create(EntityDtoConverter.dtoToEntity(message.getData()));

		}
		else if (message.getAction().equals(Constants.DELETE)) {
			log.info("ID/Payload received");
			delete(EntityDtoConverter.dtoToEntity(message.getData()));

		}
		else if (message.getAction().equals(Constants.UPDATE)) {
			log.info("Payload received");
			update(EntityDtoConverter.dtoToEntity(message.getData()));

		}
	}

	public void create(User user) {
		service.createUser(user);  

	}

	public void update(User user) {
		service.updateUser(user);

	}

	public void delete(User user) {
		service.deleteUser(user);

	}

}
