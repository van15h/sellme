package com.authentification.authservice;

import com.authentification.model.User;
import java.util.Arrays;
import java.util.List;

/**
 * Class InMemoryRepository
 */

public class InMemoryRepository implements IRepository {
  private List<User> users;
  private static InMemoryRepository instance = null;

  /**
   * Class Constructor
   */

  InMemoryRepository() {
    User hamlet = new User(1, "hamlet@gmail.com", "qwerty", "Hamlet Mkrtchyan");
    User ivan = new User(2, "ivan@gmail.com", "qwerty", "Ivan Varabyeu");
    User tornike = new User(3, "tornike@gmail.com", "qwerty", "Tornike Khachidze");

    this.users = Arrays.asList(hamlet, ivan, tornike);

  }

  /**
   * Filter elements in list
   * @param username
   * @param password
   * @return one objekt when exist
   */
  public User retrieveUser(String username, String password) throws Exception {
    User user  = this.users.stream()
        .filter(u -> u.getEmail().equals(username))
        .findFirst()
        .orElse(null);

    if (user == null || !user.getPassword().equals(password)) {
      throw new Exception("User not found");
    }

    return user;
  }

  /**
   *  createt objekt wenn not exist
   * @return created objekt
   */

  public static InMemoryRepository getRepository() {
    if (instance == null) {
      instance = new InMemoryRepository();
    }

    return instance;
  }

}




