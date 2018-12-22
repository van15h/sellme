package com.authentification.authservice;

import com.authentification.model.User;

/**
 * Interface of InMemoryRepository
 */

public interface IRepository {
   public User retrieveUser(String username, String password) throws Exception;

   public User addUser(User user) throws Exception;
}