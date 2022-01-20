package com.task.three.springboottask.model;

import com.task.springboottask.dto.UserDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Message {
	
	public String action;
	public UserDto userDto;
	
	public Message(String action, UserDto user) {
		super();
		this.action = action;
		this.userDto = user;
	}

	public Message() {
		super();
		
	}
	

	
	
		
	
	
	
	
	

}
