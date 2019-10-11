package net.secure.authorization.exceptions;

public class UserNotFoundException extends RuntimeException {
	public final String userLogin;

	public UserNotFoundException(String userLogin) {
		super();
		this.userLogin = userLogin;
	}

	@Override
	public String getMessage() {
		return userLogin + " not found";
	}
	
	
	
}
