package edu.austral.aseca.app;

import edu.austral.aseca.app.models.Receipt;
import edu.austral.aseca.app.models.User;
import edu.austral.aseca.app.services.FakeApiServiceImpl;
import edu.austral.aseca.app.services.StockService;
import edu.austral.aseca.app.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BuyStockTests {
  
  private final FakeApiServiceImpl fakeApiService = new FakeApiServiceImpl();
  private final UserService userService = new UserService();
  private StockService stockService = new StockService(fakeApiService, userService);
  
  @Test
  void contextLoads() {
  }
  
  @Test
  public void test000_WhenCreateUserShouldHaveFunds() {
    final User aUser = new User();
    assertEquals(10000, aUser.getFunds());
  }
  
  @Test
  public void test000_WhenBuyStockAndUserHasFundsShouldReturnReceipt() throws IOException, InterruptedException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    final Optional<Receipt> receipt = stockService.buyStock(symbol, quantity, userId);
    assertTrue(receipt.isPresent());
  }
  
  @Test
  public void test000_WhenBuyStockAndUserHasNoFundsShouldReturnEmptyOptional() throws IOException, InterruptedException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    userService.save(new User(userId, 0));
    final Optional<Receipt> receipt = stockService.buyStock(symbol, quantity, userId);
    assertTrue(receipt.isEmpty());
  }
  
  @Test
  public void test000_WhenBuyStockAndUserHasJustEnoughFundsShouldReturnReceipt() throws IOException, InterruptedException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    userService.save(new User(userId, 10));
    fakeApiService.setPrice(10);
    final Optional<Receipt> receipt = stockService.buyStock(symbol, quantity, userId);
    assertTrue(receipt.isPresent());
  }
  
  @Test
  public void test000_WhenBuyStockAndUserHasJustEnoughFundsPlus1ShouldReturnReceipt() throws IOException, InterruptedException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    userService.save(new User(userId, 11));
    fakeApiService.setPrice(10);
    final Optional<Receipt> receipt = stockService.buyStock(symbol, quantity, userId);
    assertTrue(receipt.isPresent());
  }
  
  @Test
  public void test000_WhenBuyStockAndUserHasJustEnoughFundsMinus1ShouldReturnEmptyReceipt() throws IOException, InterruptedException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    userService.save(new User(userId, 9));
    fakeApiService.setPrice(10);
    final Optional<Receipt> receipt = stockService.buyStock(symbol, quantity, userId);
    assertTrue(receipt.isEmpty());
  }
  
  @Test
  public void test000_WhenBuyStockAndEverythingOkShouldReturnReceiptWithAllTheInformation() throws IOException, InterruptedException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    final double price = 123;
    userService.save(new User(userId));
    fakeApiService.setPrice(price);
    final Receipt receipt = stockService.buyStock(symbol, quantity, userId).get();
    assertEquals(symbol, receipt.getStockSymbol());
    assertEquals(userId, receipt.getUserId());
    assertEquals(quantity, receipt.getQuantity());
    assertEquals(price, receipt.getPrice());
  }
}

