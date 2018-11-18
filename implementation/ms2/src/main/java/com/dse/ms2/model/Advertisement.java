package com.dse.ms2.model;

public class Advertisement {

  private int id;
  private int userId;
  private String subject;
  private int price;
  private String description;
  private String contactInfo;

  public Advertisement(int id, int userId, String subject, int price, String description,
      String contactInfo) {
    this.id = id;
    this.userId = userId;
    this.subject = subject;
    this.price = price;
    this.description = description;
    this.contactInfo = contactInfo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
  }

  @Override
  public String toString() {
    return "Advertisement{" +
        "id=" + id +
        ", userId=" + userId +
        ", subject='" + subject + '\'' +
        ", price=" + price +
        ", description='" + description + '\'' +
        ", contactInfo='" + contactInfo + '\'' +
        '}';
  }
}
