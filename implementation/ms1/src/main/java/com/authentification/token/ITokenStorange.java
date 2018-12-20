package com.authentification.token;

import com.authentification.model.Token;
import com.authentification.model.User;
import org.springframework.http.ResponseEntity;

public interface ITokenStorange {

    public Token createdTokenFor(User user);

    public boolean isTokenActive(String token);
}
