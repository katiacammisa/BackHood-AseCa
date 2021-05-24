package edu.austral.aseca.app.services;

import edu.austral.aseca.app.models.Receipt;
import edu.austral.aseca.app.models.User;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
  
  private static String getFromJson(String json, String property) {
    try {
      JSONObject fieldsJson = new JSONObject(json);
      return fieldsJson.getString(property);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return "";
  }
}
