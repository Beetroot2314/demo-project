package com.task.springboottask.excep;

public class ResourceExistsException extends RuntimeException {

	public ResourceExistsException(String exc) {
		super(exc);
	}
}
