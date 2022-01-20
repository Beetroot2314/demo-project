package com.task.springboottask.excep;

public class MissingDataException extends RuntimeException {

	public MissingDataException(String exc) {
		super(exc);
	}

}
