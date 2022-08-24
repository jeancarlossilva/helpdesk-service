package com.jean.helpdesk.resources.exceptions;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jean.helpdesk.services.exceptions.ObjectNotFounException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFounException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFounException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object Not Found", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}

}
