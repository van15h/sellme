package com.authentification.token;

import com.authentification.model.Token;
import com.authentification.model.User;

public interface ITokenStorange {

    public Token createdTokenFor(User user);

}
