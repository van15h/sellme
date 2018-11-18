package com.authentification.authservice;

import com.authentification.model.User;
import java.util.Arrays;
import java.util.List;

public class InMemoryRepository implements IRepository {
  private List<User> users;
  private static InMemoryRepository instance = null;

  InMemoryRepository() {
    User hamlet = new User(1, "hamlet@gmail.com", "qwerty", "Hamlet Mkrtchyan");
    User ivan = new User(2, "ivan@gmail.com", "qwerty", "Ivan Varabyeu");
    User tornike = new User(3, "genacvale@gmail.com", "qwerty", "Tornike Khachidze");

    this.users = Arrays.asList(hamlet, ivan, tornike);

  }

  public User retrieveUser(String username, String password) {
    User user  = this.users.stream()
        .filter(u -> u.getEmail().equals(username))
        .findFirst()
        .orElse(null);

    if (user == null || !user.getPassword().equals(password)) {
      return null;
    }

    return user;
  }

  public static InMemoryRepository getRepository() {
    if (instance == null) {
      instance = new InMemoryRepository();
    }

    return instance;
  }


}




