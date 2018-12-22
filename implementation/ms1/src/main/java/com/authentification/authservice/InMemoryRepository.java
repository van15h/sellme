package com.authentification.authservice;

import com.authentification.model.User;
import java.util.ArrayList;
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
    this.users = new ArrayList<>();

    User hamlet = new User("hamlet@gmail.com", "qwerty", "Hamlet Mkrtchyan");
    User ivan = new User("ivan@gmail.com", "qwerty", "Ivan Varabyeu");
    User tornike = new User("tornike@gmail.com", "qwerty", "Tornike Khachidze");

    this.users.addAll(Arrays.asList(hamlet, ivan, tornike));
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


  public User addUser(User user) throws Exception {
    User reguser  = this.users.stream()
        .filter(u -> u.getEmail().equals(user.getEmail()))
        .findFirst()
        .orElse(null);

    if (reguser != null){
      throw new Exception(String.format("User with email %s already exists", reguser.getEmail()));
    }

    this.users.add(user);
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




