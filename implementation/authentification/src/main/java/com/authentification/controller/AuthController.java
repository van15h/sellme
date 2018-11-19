package com.authentification.controller;

import com.authentification.authservice.IRepository;
import com.authentification.authservice.InMemoryRepository;
import com.authentification.model.User;
import org.springframework.web.bind.annotation.*;


/**
 * Class AuthController
 */

@RestController
public class AuthController {
	@RequestMapping(
			value = "/hello",
			params = { "username", "password"},
			method = RequestMethod.GET
	)

	/**
	 * Class method that return name
	 */
	@ResponseBody
	public String hello(@RequestParam(value="name") String name) {

		return String.format("Hello %s", name);
	}

	@RequestMapping(
			value = "/login"
	)

	/**
	 * Class method that return user name
	 */
	@ResponseBody
	public String login(
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password) {
		IRepository inMemoryRepository = InMemoryRepository.getRepository();

		User user = inMemoryRepository.retrieveUser(username, password);
		//return String.format("Hello from user %s", user.getName());
		return "Hello from user %s" + user.getName();

	}

}

