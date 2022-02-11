package com.task.three.springboottask.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.task.springboottask.excep.MissingDataException;
import com.task.springboottask.excep.ResourceExistsException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ControllerAdviceClass {

	@ExceptionHandler(MissingDataException.class)
	public ResponseEntity<String> handleMissingInput(MissingDataException missingDataException) {
		log.error(missingDataException + "");
		return new ResponseEntity<String>(missingDataException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceExistsException.class)
	public ResponseEntity<String> handleResourceExists(ResourceExistsException resourceExistsException) {
		log.error(resourceExistsException + "");
		return new ResponseEntity<String>(resourceExistsException.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
