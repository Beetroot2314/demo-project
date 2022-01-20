package com.task.springboottask.dto;

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
public class UserDto {

	private int id;

	private String userName;

	private String email;

	public UserDto(int id, String userName, String email) {
		this.id = id;
		this.userName = userName;
		this.email = email;

	}

	public UserDto() {
		super();
	}

}
