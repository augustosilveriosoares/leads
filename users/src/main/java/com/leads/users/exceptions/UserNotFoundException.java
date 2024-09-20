package com.leads.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Usuário não encontrado")
public class UserNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public UserNotFoundException() { super("Usuário não encontrado");}
	public UserNotFoundException(String msg) {super(msg);}

}
