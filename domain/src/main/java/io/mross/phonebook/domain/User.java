package io.mross.phonebook.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

@Region("Users")
public class User {


  @Id
  private String id;
  private String name;
  private String phoneNumber;
  private String location;
  


  @PersistenceConstructor
  public User() {

  }

  @PersistenceConstructor
  public User(String id, String name, String phoneNumber, String location) {
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.location = location;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override public String toString() {
    return "User{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", location='" + location + '\'' +
        '}';
  }
}
