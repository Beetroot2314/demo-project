package com.task.three.springboottask.model;

import com.task.springboottask.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class Message {
	
	private String action;
	private UserDto data;		
}
