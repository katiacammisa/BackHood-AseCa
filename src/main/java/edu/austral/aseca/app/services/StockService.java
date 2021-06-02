package edu.austral.aseca.app.services;

import edu.austral.aseca.app.models.Receipt;
import edu.austral.aseca.app.models.Stock;
import edu.austral.aseca.app.models.User;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    final String currentPrice = getCurrentPrice(symbol);
    final String gQ = getFromJson(currentPrice, "Global Quote");
    final String stringPrice = getFromJson(gQ, "05. price");
    final double price = Double.parseDouble(stringPrice);
    final User user = userService.findById(userId);
    if (user.getFunds() < price * quantity) return Optional.empty();
    final Receipt r = new Receipt(userId, symbol, quantity, price);
    return Optional.of(r);
  }

  public List<Stock> getStocks(String keyword) throws IOException, InterruptedException {
    List<Stock> stocks = new ArrayList<>();
    String stocksString = keyword.isEmpty() ? apiService.getStocks("A") : apiService.getStocks(keyword);
    String[] stockStringArray = getFromJson(stocksString, "bestMatches").split("},\\{");
    if (stockStringArray[0].equals("[]")) return stocks;
    if (stockStringArray.length < 20)
      for (String s : clean(stockStringArray)) stocks.add(getStockFromString(s));
    else {
      String[] s = clean(stockStringArray);
      for (int i = 0; i < 20; i++) stocks.add(getStockFromString(s[i]));
    }
    return stocks;
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

  private Stock getStockFromString(String s) {
    String symbol = getFromJson(s, "1. symbol");
    String name = getFromJson(s, "2. name");
    String type = getFromJson(s, "3. type");
    String region = getFromJson(s, "4. region");
    LocalTime marketOpen = LocalTime.parse(getFromJson(s, "5. marketOpen"));
    LocalTime marketClose = LocalTime.parse(getFromJson(s, "6. marketClose"));
    String timezone = getFromJson(s, "7. timezone");
    String currency = getFromJson(s, "8. currency");
    return new Stock(symbol, name, type, region, timezone, currency, marketOpen, marketClose);
  }

  private String[] clean(String[] jsons) {
    if (jsons.length <= 1) return jsons;
    jsons[0] = jsons[0].substring(1) + "}";
    if (jsons.length > 2) {
      for (int i = 1; i < jsons.length-1; i++) {
        jsons[i] = "{" + jsons[i] + "}";
      }
    }
    jsons[jsons.length-1] = "{" + jsons[jsons.length-1].substring(0, jsons[jsons.length-1].length()-1);
    return jsons;
  }
}
