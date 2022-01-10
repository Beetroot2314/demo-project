package com.task.three.springboottask.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Message {
	
	public String action;
	public User user;
	
	public Message(String action, User user) {
		super();
		this.action = action;
		this.user = user;
	}

	public Message() {
		super();
		
	}
	

	
	
		
	
	
	
	
	

}
