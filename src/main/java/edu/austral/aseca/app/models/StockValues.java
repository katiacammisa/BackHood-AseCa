package edu.austral.aseca.app.models;

import lombok.Data;

@Data
public class StockValues {
  private final float open, high, low, close, volume;
}