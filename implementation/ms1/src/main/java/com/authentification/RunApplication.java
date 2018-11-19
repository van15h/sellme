package com.authentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * exicute application
 */

@SpringBootApplication
public class RunApplication {
	
	String hello()
	{
		return "HEllo hakan broo ";
	}

	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
	}
}

 