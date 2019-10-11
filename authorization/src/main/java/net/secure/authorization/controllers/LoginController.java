package net.secure.authorization.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.secure.authorization.entities.User;
import net.secure.authorization.exceptions.AuthorizationException;
import net.secure.authorization.exceptions.LoginException;
import net.secure.authorization.exceptions.UserNotFoundException;
import net.secure.authorization.repositories.UserRepository;
import static net.secure.authorization.utils.PasswordUtils.*;
import static net.secure.authorization.utils.TokenUtils.*;

@RestController
public class LoginController {

	UserRepository userRep;

	LoginController(UserRepository userRep) {
		super();
		this.userRep = userRep;
	}

	@RequestMapping("/login")
	String login(@RequestParam String login, @RequestParam String password) {
		User user = userRep.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		Date expirationDate = new Date(System.currentTimeMillis() + 30000);
		if (verifyUserPassword(password, user.getPassword(), user.getKeyWord()))
			return getToken(login, expirationDate);
		else
			throw new LoginException();
	}

	@RequestMapping("/name")
	String name(@RequestParam String login, @RequestParam String token) {
		User user = userRep.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		if (checkToken(token))
			return user.getName();
		else
			throw new AuthorizationException();
	}

}
