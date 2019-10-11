package net.secure.authorization.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	private @Id String login;
	private String password;
	private String keyWord;
	private String name;

	User() {
		super();
	}

	public User(String login, String password, String keyWord, String name) {
		super();
		this.login = login;
		this.password = password;
		this.keyWord = keyWord;
		this.name = name;
	}

}
