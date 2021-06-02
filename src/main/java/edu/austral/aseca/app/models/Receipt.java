package edu.austral.aseca.app.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Receipt {
  @Id @GeneratedValue private Long id;
  private Long userId;
  private String stockSymbol;
  private double quantity, price, totalPrice;
  
  public Receipt() {}
  
  public Receipt(Long userId, String symbol, double quantity, double price) {
    this.userId = userId;
    this.stockSymbol = symbol;
    this.quantity = quantity;
    this.price = price;
    totalPrice = quantity * price;
  }
}
