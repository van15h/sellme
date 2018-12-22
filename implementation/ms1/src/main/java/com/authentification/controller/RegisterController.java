package com.authentification.controller;

import com.authentification.authservice.IRepository;
import com.authentification.authservice.InMemoryRepository;
import com.authentification.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

  @RequestMapping(
      value = "/register"
  )
  @ResponseBody
  public ResponseEntity<String> registerUser(
     @RequestParam("email") String email,
     @RequestParam("password") String password,
     @RequestParam("name") String name) {
    IRepository inMemoryRepository = InMemoryRepository.getRepository();

    User user = new User(email,password, name);

    try {
      inMemoryRepository.addUser(user);
      return ResponseEntity.status(200).body(user.getEmail());
    } catch (Exception e) {
      return ResponseEntity.status(304).body(e.getMessage());
    }
  }


  }
