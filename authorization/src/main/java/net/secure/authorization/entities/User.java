package net.secure.authorization.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	private @Id String email;
	private String password;
	private String keyWord;
	private String name;

	User() {
		super();
	}

	public User(String email, String password, String keyWord, String name) {
		super();
		this.email = email;
		this.password = password;
		this.keyWord = keyWord;
		this.name = name;
	}

}
