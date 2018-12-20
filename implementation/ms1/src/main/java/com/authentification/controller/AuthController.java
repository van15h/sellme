package com.authentification.controller;

import com.authentification.authservice.IRepository;
import com.authentification.authservice.InMemoryRepository;
import com.authentification.model.Token;
import com.authentification.model.User;
import com.authentification.token.ITokenStorange;
import com.authentification.token.InMemoryTokenStorange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;






/**
 * Class AuthController
 */

@RestController
public class AuthController {
	/**
	 * Class method that return user name
	 */
	@RequestMapping(
			value = "/login"
	)
	@ResponseBody
	public ResponseEntity<String> login(
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password) {
		IRepository inMemoryRepository = InMemoryRepository.getRepository();
		ITokenStorange inMemoryTokenStorage = InMemoryTokenStorange.getStorage();

		try {
			User user = inMemoryRepository.retrieveUser(username, password);
			Token token = inMemoryTokenStorage.createdTokenFor(user);
			return ResponseEntity.status(200).body(token.getValue());
		}
		catch (Exception e ){
			return ResponseEntity.status(404).body(e.getMessage());
		}

	}

	@RequestMapping(
			value = "/token/{value}"
	)
	@ResponseBody
	public boolean token(
			@PathVariable("value") String value) {
		ITokenStorange tokenStorange = InMemoryTokenStorange.getStorage();
		return tokenStorange.isTokenActive(value);

	}
}

