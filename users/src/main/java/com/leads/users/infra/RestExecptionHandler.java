
package com.leads.users.infra;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leads.users.exceptions.UserNotFoundException;

@ControllerAdvice
public class RestExecptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	private ResponseEntity<String> userNotFoundException(UserNotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não Encontrado");
		
	}
}
