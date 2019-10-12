package net.secure.authorization.controllers;

import static net.secure.authorization.utils.PasswordUtils.generateSecurePassword;
import static net.secure.authorization.utils.PasswordUtils.getSalt;
import static net.secure.authorization.utils.PasswordUtils.verifyUserPassword;
import static net.secure.authorization.utils.TokenUtils.checkToken;
import static net.secure.authorization.utils.TokenUtils.getToken;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.secure.authorization.entities.User;
import net.secure.authorization.exceptions.AuthorizationException;
import net.secure.authorization.exceptions.LoginException;
import net.secure.authorization.exceptions.UserNotFoundException;
import net.secure.authorization.repositories.UserRepository;

@RestController
public class LoginController {

	UserRepository userRep;

	LoginController(UserRepository userRep) {
		super();
		this.userRep = userRep;
	}

	@PostMapping("/users")
	User register(@RequestBody User newUser) {
		String salt = getSalt(10);
		String encryptedPassword = generateSecurePassword(newUser.getPassword(), salt);
		newUser.setPassword(encryptedPassword);
		newUser.setKeyWord(salt);
		return userRep.save(newUser);
	}

	@PostMapping("/login")
	String login(@RequestBody User newUser) {
		User user = userRep.findById(newUser.getEmail())
				.orElseThrow(() -> new UserNotFoundException(newUser.getEmail()));
		Date expirationDate = new Date(System.currentTimeMillis() + 60000);
		if (verifyUserPassword(newUser.getPassword(), user.getPassword(), user.getKeyWord()))
			return getToken(newUser.getEmail(), expirationDate);
		else
			throw new LoginException();
	}

	@PostMapping("/name")
	String name(@RequestBody SecureRequest secureRequest) {
		User user = userRep.findById(secureRequest.getLogin())
				.orElseThrow(() -> new UserNotFoundException(secureRequest.getLogin()));
		if (checkToken(secureRequest.getToken()))
			return user.getName();
		else
			throw new AuthorizationException();
	}

}
