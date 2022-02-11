package com.task.springboottask.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.task.springboottask.dto.UserDto;
import com.task.springboottask.excep.MissingDataException;
import com.task.three.springboottask.model.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntityDtoConverter {

	public static User dtoToEntity(UserDto dto) {
		return User.builder().id(dto.getId()).userName(dto.getUserName()).email(dto.getEmail()).build();

	}

	public static UserDto entityToDto(User user) {
		if (user == null) {
			throw new MissingDataException("Data not found in Database");
		}
		return UserDto.builder().id(user.getId()).userName(user.getUserName()).email(user.getEmail()).build();

	}

	public static List<UserDto> listToDto(List<User> user) {
		return user.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

	}

	public static Page<UserDto> pageToDto(Page<User> users) {
		Page<UserDto> dtos = users.map(EntityDtoConverter::entityToDto);
		return dtos;
	}

}
