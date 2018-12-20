package com.authentification.model;

import java.util.Date;

public class Token {

  private final int timeToLive;
  private final Date createdAt;
  private String value;
  private User user;


  public Token( String value, User user)
  {

    this.value = value;
    this.user = user;
    this.timeToLive = 60;
    this.createdAt = new Date();
  }


  public String getValue() {
    return value;
  }

  public int getTimeToLive() {
    return timeToLive;
  }

  public boolean isValid(){
    long currentTime = new Date().getTime();

    if(currentTime - this.createdAt.getTime()  > timeToLive * 60 * 1000){
      return false;
    }

    return true;
  }

  public User getUser() {
    return user;
  }
}
