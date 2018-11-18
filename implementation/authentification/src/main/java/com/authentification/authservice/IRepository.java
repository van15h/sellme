package com.authentification.authservice;

import com.authentification.model.User;

public interface IRepository {
   public User retrieveUser(String username, String password);
}