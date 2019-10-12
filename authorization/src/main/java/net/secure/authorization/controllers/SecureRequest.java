package net.secure.authorization.controllers;

import lombok.Data;

@Data
public class SecureRequest {

	private String login;
	private String token;
	public SecureRequest(String login, String token) {
		super();
		this.login = login;
		this.token = token;
	}
	
}
