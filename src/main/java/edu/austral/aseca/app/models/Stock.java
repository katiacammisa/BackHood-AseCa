package edu.austral.aseca.app.models;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Stock {
    private final String symbol, name, type, region, timezone, currency;
    private final LocalTime marketOpen, marketClose;
}