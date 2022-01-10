package com.task.three.springboottask.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.springboottask.excep.MissingDataException;
import com.task.three.springboottask.model.KafkaConsumer;
import com.task.three.springboottask.model.Message;
import com.task.three.springboottask.model.User;
import com.task.three.springboottask.repository.UserRepo;

import ch.qos.logback.classic.Logger;

@RestController
public class UserController {

	@Autowired
	private UserRepo repository;

	@Autowired
	KafkaTemplate<String, Message> kafkaTemplate;

	private final String TOPIC = "Kafka_Task6";

	Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

	@PostMapping("/addUser")
	public ResponseEntity<Message> saveUser(@RequestBody Message message) {
		try {
			if ((message.getAction() == "") || (message.getUser().getEmail() == "") || (message.getUser().getId() == 0)
					|| (message.getUser().getUserName() == "") || (message.getUser() == null)) {
				throw new MissingDataException("Invalid message. Missing fields in the message received.");
			}
		

		log.info("Sending Paylod to Topic");
		kafkaTemplate.send(TOPIC, message);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		catch (MissingDataException exc) {
			System.out.println(exc);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		
	}

	@GetMapping("/findAll")
	public List<User> getUsers() {
		log.info("Fetching all users");
		return repository.findAll();
	}

	@GetMapping("/findUser/{id}")
	public Optional<User> getUser(@PathVariable int id) {
		log.info("Fetching User with ID " + id);
		return repository.findById(id);
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {

		log.info("Sending ID to Topic");
		Message message = new Message();
		User userid = new User();
		userid.setId(id);
		message.user = userid;
		message.setAction("DELETE");
		kafkaTemplate.send(TOPIC, message);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity updateUser(@PathVariable int id, @RequestBody Message message) {

		log.info("Sending Paylod to Topic");
		kafkaTemplate.send(TOPIC, message);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	// kafka-commands
	// .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic
	// Kafka_Task6
	// .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181
	// --replication-factor 1 --partitions 1 --topic Kafka_Task6
	// .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092
	// --topic Kafka_Task6 --from-beginning
	// {"id":13,"userName":"Amber","email":"Nillohit123@gmail.com"}

}
