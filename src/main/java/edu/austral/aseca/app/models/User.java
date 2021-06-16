package edu.austral.aseca.app.models;

import lombok.Data;

@Data
public class User {
  private Long id;
  private long funds = 10000;
  
  public User(Long id, long funds) {
    this.id = id;
    this.funds = funds;
  }
  public User(Long id) {
    this.id = id;
  }
  public User(long funds) {
    this.funds = funds;
  }
  public User() {}
}
