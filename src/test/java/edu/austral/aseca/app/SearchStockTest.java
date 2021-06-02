package edu.austral.aseca.app;

import edu.austral.aseca.app.models.Stock;
import edu.austral.aseca.app.services.FakeApiServiceImpl;
import edu.austral.aseca.app.services.StockService;
import edu.austral.aseca.app.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SearchStockTest {

  private final FakeApiServiceImpl fakeApiService = new FakeApiServiceImpl();
  private final UserService userService = new UserService();
  private final StockService stockService = new StockService(fakeApiService, userService);

  @Test
  void contextLoads() {
  }

  @Test
  public void test000_WhenSearchIsNotEmptyShouldReturnStocksThatMatch() throws IOException, InterruptedException {
    final List<Stock> actual = stockService.getStocks("Agilent");
    List<Stock> expected = new ArrayList<>();
    expected.add(new Stock("AG8.FRK", "Agilent Technologies", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    expected.add(new Stock("A", "Agilent Technologies Inc", "Equity", "United States", "UTC-04", "USD" , LocalTime.parse("09:30"), LocalTime.parse("16:00")));
    assertTrue(compareStockList(expected, actual));
  }

  @Test
  public void test001_WhenSearchIsEmptyShouldReturn10FirstStocks() throws IOException, InterruptedException {
    final List<Stock> actual = stockService.getStocks("");
    List<Stock> expected = new ArrayList<>();
    expected.add(new Stock("A", "Agilent Technologies Inc", "Equity", "United States", "UTC-04", "USD" , LocalTime.parse("09:30"), LocalTime.parse("16:00")));
    expected.add(new Stock("A.TRV", "Armor Minerals Inc", "Equity", "Toronto Venture", "UTC-05", "CAD", LocalTime.parse("09:30"), LocalTime.parse("16:00")));
    expected.add(new Stock("A0I.FRK", "A0I", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    expected.add(new Stock("A02.FRK", "Adways Inc", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    expected.add(new Stock("A04.FRK", "Ascential plc", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    expected.add(new Stock("A08.FRK", "At Home Group Inc", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    expected.add(new Stock("A0T.FRK", "American Tower Corporation (REIT)", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    expected.add(new Stock("A0W.FRK", "Arrow Global Group PLC", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    expected.add(new Stock("A16.FRK", "ASR Nederland N.V", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    expected.add(new Stock("A0U2.FRK", "Azincourt Energy Corp", "Equity", "Frankfurt", "UTC+02", "EUR" , LocalTime.parse("08:00"), LocalTime.parse("20:00")));
    assertTrue(compareStockList(expected, actual));
  }

  @Test
  public void test002_WhenSearchIsNotEmptyButDoesntExistShouldReturnEmptyList() throws IOException, InterruptedException {
    final List<Stock> actual = stockService.getStocks("lalala");
    assertEquals(0, actual.size());
  }

  private boolean compareStockList(List<Stock> expected, List<Stock> actual) {
    if (expected.size() != actual.size()) return false;
    for (int i = 0; i < expected.size(); i++) {
      if (!expected.get(i).getSymbol().equals(actual.get(i).getSymbol())) return false;
      if (!expected.get(i).getName().equals(actual.get(i).getName())) return false;
      if (!expected.get(i).getType().equals(actual.get(i).getType())) return false;
      if (!expected.get(i).getRegion().equals(actual.get(i).getRegion())) return false;
      if (!expected.get(i).getMarketOpen().equals(actual.get(i).getMarketOpen())) return false;
      if (!expected.get(i).getMarketClose().equals(actual.get(i).getMarketClose())) return false;
      if (!expected.get(i).getTimezone().equals(actual.get(i).getTimezone())) return false;
      if (!expected.get(i).getCurrency().equals(actual.get(i).getCurrency())) return false;
    }
    return true;
  }
}
