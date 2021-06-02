package edu.austral.aseca.app.models;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Stock {
  private final String symbol, name, type, region, timezone, currency;
  private final LocalTime marketOpen, marketClose;

  public Stock(String symbol, String name, String type, String region, String timezone, String currency, LocalTime marketOpen, LocalTime marketClose) {
    this.symbol = symbol;
    this.name = name;
    this.type = type;
    this.region = region;
    this.timezone = timezone;
    this.currency = currency;
    this.marketOpen = marketOpen;
    this.marketClose = marketClose;
  }
}
