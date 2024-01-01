package com.suelen.helpdesk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
