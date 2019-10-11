package net.secure.authorization.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.secure.authorization.exceptions.AuthorizationException;

@ControllerAdvice
public class AuthorizationAdvice {
	
	@ResponseBody
	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String notAuthorized(AuthorizationException exc) {
		return exc.getMessage();
	}

}
