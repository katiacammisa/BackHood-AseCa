package edu.austral.aseca.app.services;

import lombok.Setter;
import org.springframework.stereotype.Service;

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
  public String getCurrentPrice(String symbol) {
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
  public String getStocks(String keyword) {
    if (keyword.equals("A")) {
      return "{\n" +
              "    \"bestMatches\": [\n" +
              "        {\n" +
              "            \"1. symbol\": \"A\",\n" +
              "            \"2. name\": \"Agilent Technologies Inc\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"United States\",\n" +
              "            \"5. marketOpen\": \"09:30\",\n" +
              "            \"6. marketClose\": \"16:00\",\n" +
              "            \"7. timezone\": \"UTC-04\",\n" +
              "            \"8. currency\": \"USD\",\n" +
              "            \"9. matchScore\": \"1.0000\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A.TRV\",\n" +
              "            \"2. name\": \"Armor Minerals Inc\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Toronto Venture\",\n" +
              "            \"5. marketOpen\": \"09:30\",\n" +
              "            \"6. marketClose\": \"16:00\",\n" +
              "            \"7. timezone\": \"UTC-05\",\n" +
              "            \"8. currency\": \"CAD\",\n" +
              "            \"9. matchScore\": \"0.5000\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A0I.FRK\",\n" +
              "            \"2. name\": \"A0I\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.5000\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A02.FRK\",\n" +
              "            \"2. name\": \"Adways Inc\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.3333\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A04.FRK\",\n" +
              "            \"2. name\": \"Ascential plc\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.3333\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A08.FRK\",\n" +
              "            \"2. name\": \"At Home Group Inc\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.3333\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A0T.FRK\",\n" +
              "            \"2. name\": \"American Tower Corporation (REIT)\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.3333\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A0W.FRK\",\n" +
              "            \"2. name\": \"Arrow Global Group PLC\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.3333\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A16.FRK\",\n" +
              "            \"2. name\": \"ASR Nederland N.V\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.3333\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A0U2.FRK\",\n" +
              "            \"2. name\": \"Azincourt Energy Corp\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.2857\"\n" +
              "        }\n" +
              "    ]\n" +
              "}";
    } else if(keyword.equals("lalala")) {
      return "{\n" +
              "    \"bestMatches\": []" +
              "}";
    } else {
      return "{\n" +
              "    \"bestMatches\": [\n" +
              "        {\n" +
              "            \"1. symbol\": \"AG8.FRK\",\n" +
              "            \"2. name\": \"Agilent Technologies\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"Frankfurt\",\n" +
              "            \"5. marketOpen\": \"08:00\",\n" +
              "            \"6. marketClose\": \"20:00\",\n" +
              "            \"7. timezone\": \"UTC+02\",\n" +
              "            \"8. currency\": \"EUR\",\n" +
              "            \"9. matchScore\": \"0.5185\"\n" +
              "        },\n" +
              "        {\n" +
              "            \"1. symbol\": \"A\",\n" +
              "            \"2. name\": \"Agilent Technologies Inc\",\n" +
              "            \"3. type\": \"Equity\",\n" +
              "            \"4. region\": \"United States\",\n" +
              "            \"5. marketOpen\": \"09:30\",\n" +
              "            \"6. marketClose\": \"16:00\",\n" +
              "            \"7. timezone\": \"UTC-04\",\n" +
              "            \"8. currency\": \"USD\",\n" +
              "            \"9. matchScore\": \"0.4516\"\n" +
              "        }\n" +
              "    ]\n" +
              "}";
    }
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
