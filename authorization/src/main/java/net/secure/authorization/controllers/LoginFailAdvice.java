package net.secure.authorization.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.secure.authorization.exceptions.LoginException;

@ControllerAdvice
public class LoginFailAdvice {
	  @ResponseBody
	  @ExceptionHandler(LoginException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String loginFailHandler(LoginException ex) {
	    return ex.getMessage();
	  }

}
