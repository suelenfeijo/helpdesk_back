package com.suelen.helpdesk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.suelen.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.suelen.helpdesk.services.exceptions.ObjectNotFoundException;

/*
 * manipulador de esceções do nosso recurso
 */
@ControllerAdvice	
public class ResourceExceptionHandler {
	
	/*
	 * ele é um manipulador de exceção de qual classe? a Classe ObjectNotFoundException.class
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectnotFoundException(ObjectNotFoundException ex,
			HttpServletRequest request) {

		/*
		 * Montando o corpo da resposta , standar error (primeiro parametro = o momento que ocorreu
		 * a exceção , já vem em milesegundos, segundo parametro é o status, hhtpStatus.notfound (padrão) .value
		 * , o terceiro parametro é o error que está como string "Object Not Found" , temos no 4 parametro a message
		 * vamos pegar do ex (Classe ObjectNotFoundException), que tem esse método que trás a messagem, no 5 parametro temos o path, que é o endereço onde aconteceu
		 * a exceção, e pra isso temos no standarError por padrão o request.getRequestURI.
		 */
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Object Not Found", ex.getMessage(), request.getRequestURI());

		/*
		 * agora retornamos o responseEntity, que é o status http.notfound, e no corpo da resposta lançamos o erro.
		 */
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	/*
	 * .class sempre do nosso pacote.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request) {

		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Violação de dados a nível de banco de dados", ex.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	/*
	 * herdando o standarderror = erro padrão.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
				"Validation error", "Erro na validação dos campos", request.getRequestURI());
		
		/*Trazendo o nome do campo que falta, e a messagem padrão
		 * para cada  FieldError x -> teremos um MethodArgumentNotValidException.getBindingResult trazendo
		 * erro por erro o fieldName , e o messageDefault , que é setado no value da validation nos domains
		 */
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			errors.addError(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
