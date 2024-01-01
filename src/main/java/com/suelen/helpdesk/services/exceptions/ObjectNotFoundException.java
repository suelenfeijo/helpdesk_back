package com.suelen.helpdesk.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}
}