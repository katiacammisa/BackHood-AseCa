package edu.austral.aseca.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class StockStatsDto {
    private float price, open, high, low, week52low, week52high;
    private int volume;
    private long volumeAverage;
    private List<DailyPrices> dailyPrices;
}
