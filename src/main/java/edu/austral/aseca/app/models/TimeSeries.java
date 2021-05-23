package edu.austral.aseca.app.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class TimeSeries {
    private final Map<LocalDateTime,StockValues> timeSeries = new HashMap<>();
}
