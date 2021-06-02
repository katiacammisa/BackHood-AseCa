package edu.austral.aseca.app.services;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.austral.aseca.app.dtos.DailyPrices;
import edu.austral.aseca.app.dtos.StockStatsDto;
import edu.austral.aseca.app.models.*;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final ApiService apiService;
    private final UserService userService;

    public StockService(ApiService apiService, UserService userService) {
        this.apiService = apiService;
        this.userService = userService;
    }

    public String getCurrentPrice(String symbol) throws IOException, InterruptedException {
        return apiService.getCurrentPrice(symbol);
    }

    public Optional<Receipt> buyStock(String symbol, double quantity, Long userId) throws IOException, InterruptedException {
        final double price = getPrice(symbol);
        final User user = userService.findById(userId);
        if (user.getFunds() < price * quantity) return Optional.empty();
        final Receipt r = new Receipt(userId, symbol, quantity, price);
        return Optional.of(r);
    }

    public StockStatsDto getStockStats(String symbol) throws IOException, InterruptedException {
        String response52week = apiService.getWeeklyStats(symbol);
        TimeSeriesWeekly timeSeriesWeekly = jsonTo52WeekTimeSeries(response52week);
        ArrayList<LocalDate> list = new ArrayList<>(timeSeriesWeekly.getTimeSeries().keySet());
        float week52High = timeSeriesWeekly.getTimeSeries().get(list.get(0)).getHigh();
        float week52Low = timeSeriesWeekly.getTimeSeries().get(list.get(0)).getLow();
        long volumeAverage = 0;
        for (LocalDate localDate : list) {
            StockValues values = timeSeriesWeekly.getTimeSeries().get(localDate);
            if (week52High < values.getHigh()) week52High = values.getHigh();
            if (week52Low > values.getLow()) week52Low = values.getLow();
            volumeAverage += values.getVolume();
        }
        volumeAverage = volumeAverage / (list.size()*7);

        String responseDaily = apiService.getCurrentStats(symbol);
        TimeSeriesDaily daily = jsonToTodayStats(responseDaily);
        ArrayList<LocalDateTime> list1 = new ArrayList<>(daily.getTimeSeries().keySet());
        list1.sort(LocalDateTime::compareTo);
        StockValues todayValue = daily.getTimeSeries().get(list1.get(list1.size()-1));

        float price = (float) getPrice(symbol);

        return new StockStatsDto(price, todayValue.getOpen(), todayValue.getHigh(), todayValue.getLow(), week52Low, week52High, todayValue.getVolume(), volumeAverage, getDailyPrices(symbol));
    }

    private double getPrice(String symbol) throws IOException, InterruptedException {
        final String currentPrice = getCurrentPrice(symbol);
        final String gQ = getFromJson(currentPrice, "Global Quote");
        final String stringPrice = getFromJson(gQ, "05. price");
        final double price = Double.parseDouble(stringPrice);
        return price;
    }

    private static String getFromJson(String json, String property) {
        try {
            JSONObject fieldsJson = new JSONObject(json);
            return fieldsJson.getString(property);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private TimeSeriesWeekly jsonTo52WeekTimeSeries(String response) {
        TimeSeriesWeekly timeSeriesWeekly = TimeSeriesWeekly.fromJSON(response);
        ArrayList<LocalDate> list = new ArrayList<>(timeSeriesWeekly.getTimeSeries().keySet());
        Comparator<LocalDate> c = Collections.reverseOrder();
        list.sort(c);
        int n = Math.min(list.size(), 52);
        TimeSeriesWeekly timeSeries52Week = new TimeSeriesWeekly();
        for (int i = 0; i < n; i++) {
            timeSeries52Week.add(list.get(i), timeSeriesWeekly.getTimeSeries().get(list.get(i)));
        }
        return timeSeriesWeekly;
    }

    private TimeSeriesDaily jsonToTodayStats(String response) {
        TimeSeriesDaily timeSeriesDaily = TimeSeriesDaily.fromJSON(response);
        ArrayList<LocalDateTime> list = new ArrayList<>(timeSeriesDaily.getTimeSeries().keySet());
        for (LocalDateTime s : list) {
            if (!(s.toLocalDate().equals(LocalDate.now()) || s.toLocalDate().equals(LocalDate.now().minusDays(1)))) {
                timeSeriesDaily.getTimeSeries().remove(s);
            }
        }
        return timeSeriesDaily;
    }

    private List<DailyPrices> getDailyPrices(String symbol) throws IOException, InterruptedException {
        List<DailyPrices> result = new ArrayList<>();
        try {
            String response = apiService.getDailyStats(symbol);
            ObjectMapper mapper = new ObjectMapper();
            String fromJson = getFromJson(response, "Time Series (Daily)");
            LinkedHashMap<String, LinkedHashMap<String, String>> apiResponse = (LinkedHashMap<String, LinkedHashMap<String, String>>) mapper.readValue(fromJson, Map.class);
            ArrayList<String> list = new ArrayList<>(apiResponse.keySet());
            List<LocalDate> localDates = list.stream().map(s -> LocalDate.parse(s)).collect(Collectors.toList());
            Comparator<LocalDate> c = Collections.reverseOrder();
            localDates.sort(c);
            for (LocalDate s : localDates) {
                float open = Float.parseFloat(apiResponse.get(s.toString()).get("1. open"));
                result.add(new DailyPrices(s.toString(),open));
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        }
        return result;
    }

}
