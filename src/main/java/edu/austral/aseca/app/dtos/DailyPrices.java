package edu.austral.aseca.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyPrices {
    String day;
    float price;
}
