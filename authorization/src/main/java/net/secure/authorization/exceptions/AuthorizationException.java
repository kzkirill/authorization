package net.secure.authorization.exceptions;

public class AuthorizationException extends RuntimeException {

	@Override
	public String getMessage() {
		return "You are not authorized";
	}
}
