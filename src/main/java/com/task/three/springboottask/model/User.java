package com.task.three.springboottask.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "email", nullable = false)
	private String email;

}
