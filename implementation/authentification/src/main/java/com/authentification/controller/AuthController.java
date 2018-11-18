package com.authentification.controller;

import com.authentification.authservice.IRepository;
import com.authentification.authservice.InMemoryRepository;
import com.authentification.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
	@RequestMapping(
			value = "/hello",
			params = { "username", "password"},
			method = RequestMethod.GET
	)
	@ResponseBody
	public String hello(@RequestParam(value="name") String name) {

		return String.format("Hello %s", name);
	}

	@RequestMapping(
			value = "/login"
	)
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

