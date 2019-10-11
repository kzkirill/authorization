package net.secure.authorization.exceptions;

public class LoginException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Login exception";
	}

}
