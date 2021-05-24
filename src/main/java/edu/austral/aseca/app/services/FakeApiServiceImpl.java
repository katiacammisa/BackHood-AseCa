package edu.austral.aseca.app.services;

import java.io.IOException;

public class FakeApiServiceImpl implements ApiService {
  
  private double price = 32.46;
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  @Override
  public String getCurrentPrice(String symbol) throws IOException, InterruptedException {
    return "{\n" +
            "    \"Global Quote\": {\n" +
            "        \"01. symbol\": \""+ symbol +"\",\n" +
            "        \"02. open\": \"32.5600\",\n" +
            "        \"03. high\": \"32.5700\",\n" +
            "        \"04. low\": \"32.4400\",\n" +
            "        \"05. price\": \""+ price +"\",\n" +
            "        \"06. volume\": \"223035\",\n" +
            "        \"07. latest trading day\": \"2021-05-24\",\n" +
            "        \"08. previous close\": \"32.4900\",\n" +
            "        \"09. change\": \"-0.0300\",\n" +
            "        \"10. change percent\": \"-0.0923%\"\n" +
            "    }\n" +
            "}";
  }
}
