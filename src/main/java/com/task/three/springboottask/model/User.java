package com.task.three.springboottask.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "User")
public class User {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "email", nullable = false)
	private String email;
	

	public User(int id, String userName, String email) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		
	}

	public User() {
		super();
	}

}
