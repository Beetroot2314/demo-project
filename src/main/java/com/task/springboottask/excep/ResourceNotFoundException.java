package com.task.springboottask.excep;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String exc) {
		super(exc);
	}
}
