package com.task.springboottask.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.task.springboottask.dto.UserDto;
import com.task.three.springboottask.model.User;

public class Convert {

	public static User ToUser(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setUserName(dto.getUserName());
		return user;
	}

	public static UserDto ToDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setUserName(user.getUserName());
		return dto;
	}

	public static List<UserDto> ToDto(List<User> user) {
		return user.stream().map(x -> ToDto(x)).collect(Collectors.toList());

	}

	
}
