package com.suelen.helpdesk.services.exceptions;



/*
 * RuntimeException 
 */
public class ObjectNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	/*
	 * string e causa da exceção
	 */
	ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}
}