package edu.austral.aseca.app.models;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class TimeSeriesDaily {
  private final Map<LocalDateTime, StockValues> timeSeries;

  public TimeSeriesDaily() {
    timeSeries = new HashMap<>();
  }

  public void add(LocalDateTime ld, StockValues sv){
    timeSeries.put(ld, sv);
  }

  public static TimeSeriesDaily fromJSON(String json){
    TimeSeriesDaily timeSeriesDaily = new TimeSeriesDaily();
    try {
      ObjectMapper mapper = new ObjectMapper();
      JSONObject fieldsJson = new JSONObject(json);
      String fromJson = fieldsJson.getString("Time Series (60min)");
      LinkedHashMap<String, LinkedHashMap<String, String>> apiResponse = (LinkedHashMap<String, LinkedHashMap<String, String>>) mapper.readValue(fromJson, Map.class);
      ArrayList<String> list = new ArrayList<>(apiResponse.keySet());
      for (String s : list) {
        LocalDateTime ld = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        float open = Float.parseFloat(apiResponse.get(s).get("1. open"));
        float high = Float.parseFloat(apiResponse.get(s).get("2. high"));
        float low = Float.parseFloat(apiResponse.get(s).get("3. low"));
        float close = Float.parseFloat(apiResponse.get(s).get("4. close"));
        int volume = Integer.parseInt(apiResponse.get(s).get("5. volume"));
        StockValues sv = new StockValues(open, high, low, close, volume);
        timeSeriesDaily.add(ld, sv);
      }
    } catch (JSONException | JsonProcessingException e) {
      e.printStackTrace();
    }
    return timeSeriesDaily;
  }
}
