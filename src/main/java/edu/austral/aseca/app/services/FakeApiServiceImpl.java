package edu.austral.aseca.app.services;

public class FakeApiServiceImpl implements ApiService {
  
  private double price = 32.46;
  
  public void setPrice(double price) {
    this.price = price;
  }
  
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
}
