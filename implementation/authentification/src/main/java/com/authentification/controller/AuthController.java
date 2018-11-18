package com.authentification.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
	@RequestMapping("/hello")
	public String hello(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format("Hello %s", name);
	}

}





