package com.authentification.token;

import com.authentification.model.Token;
import com.authentification.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryTokenStorange implements ITokenStorange {

  private List<Token> activeTokens;
  private static InMemoryTokenStorange instance = null;

  public InMemoryTokenStorange() {
    this.activeTokens = new ArrayList<>();
  }

  public static ITokenStorange getStorage() {
    if (instance == null) {
      instance = new InMemoryTokenStorange();
    }

    return instance;
  }

  @Override
  public Token createdTokenFor(User user) {
    Token activeToken = this.activeTokens
        .stream()
        .filter(
            token -> token.getUser().getEmail().equals(user.getEmail()) && token.isValid()
        )
        .findFirst()
        .orElse(null);

    if (activeToken == null) {
      activeToken = new Token(this.generateToken(user), user);
      this.activeTokens.add(activeToken);

    }
    return activeToken;
  }


  private String generateToken(User user) {

    return user.getEmail() + new Date().getTime();
  }


}


