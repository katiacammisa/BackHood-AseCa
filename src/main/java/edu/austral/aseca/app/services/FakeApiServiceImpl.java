package edu.austral.aseca.app.services;

import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
public class FakeApiServiceImpl implements ApiService {
  
  private double price = 32.46;
  private double high = 143.10;
  private double low = 123.10;
  private double minimumLow = 113.10;
  private double maximumHigh = 153.10;
  private double open = 144;
  private int volume = 30000;

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

  @Override
  public String getCurrentStats(String symbol) throws IOException, InterruptedException {
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    return "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Intraday (60min) open, high, low, close prices and volume\",\n" +
            "        \"2. Symbol\": \""+symbol+"\",\n" +
            "        \"3. Last Refreshed\": \"2021-05-26 20:00:00\",\n" +
            "        \"4. Interval\": \"60min\",\n" +
            "        \"5. Output Size\": \"Compact\",\n" +
            "        \"6. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Time Series (60min)\": {\n" +
            "        \""+ today +" 20:00:00\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+high+"\",\n" +
            "            \"3. low\": \""+low+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        },\n" +
            "        \""+today+" 18:00:00\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+high+"\",\n" +
            "            \"3. low\": \""+low+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        },\n" +
            "        \""+today+" 17:00:00\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+maximumHigh+"\",\n" +
            "            \"3. low\": \""+minimumLow+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
  }

  @Override
  public String getDailyStats(String symbol) throws IOException, InterruptedException {
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String beforeYesterday = LocalDate.now().minusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    return "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Daily Prices (open, high, low, close) and Volumes\",\n" +
            "        \"2. Symbol\": \""+symbol+"\",\n" +
            "        \"3. Last Refreshed\": \"2021-05-26\",\n" +
            "        \"4. Output Size\": \"Compact\",\n" +
            "        \"5. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Time Series (Daily)\": {\n" +
            "        \""+today+"\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+high+"\",\n" +
            "            \"3. low\": \""+low+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        },\n" +
            "        \""+yesterday+"\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+high+"\",\n" +
            "            \"3. low\": \""+low+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        },\n" +
            "        \""+beforeYesterday+"\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+maximumHigh+"\",\n" +
            "            \"3. low\": \""+minimumLow+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
  }

  @Override
  public String getWeeklyStats(String symbol) throws IOException, InterruptedException {
    return "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Weekly Prices (open, high, low, close) and Volumes\",\n" +
            "        \"2. Symbol\": \""+symbol+"\",\n" +
            "        \"3. Last Refreshed\": \"2021-05-26\",\n" +
            "        \"4. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Weekly Time Series\": {\n" +
            "        \"2021-05-26\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+high+"\",\n" +
            "            \"3. low\": \""+low+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        },\n" +
            "        \"2021-05-21\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+high+"\",\n" +
            "            \"3. low\": \""+low+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        },\n" +
            "        \"2021-05-14\": {\n" +
            "            \"1. open\": \""+open+"\",\n" +
            "            \"2. high\": \""+maximumHigh+"\",\n" +
            "            \"3. low\": \""+minimumLow+"\",\n" +
            "            \"4. close\": \"143.3101\",\n" +
            "            \"5. volume\": \""+volume+"\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
  }
}
