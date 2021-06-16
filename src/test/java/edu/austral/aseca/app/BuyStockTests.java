package edu.austral.aseca.app;

import edu.austral.aseca.app.exceptions.NoFundsException;
import edu.austral.aseca.app.models.User;
import edu.austral.aseca.app.respository.EmptyReceiptRepository;
import edu.austral.aseca.app.respository.ReceiptRepository;
import edu.austral.aseca.app.services.FakeApiServiceImpl;
import edu.austral.aseca.app.services.StockService;
import edu.austral.aseca.app.services.UserService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BuyStockTests {
  
  private final ReceiptRepository emptyReceiptRepository = new EmptyReceiptRepository();
  private final FakeApiServiceImpl fakeApiService = new FakeApiServiceImpl();
  private final UserService userService = new UserService();
  private final StockService stockService = new StockService(fakeApiService, userService, emptyReceiptRepository);
  
  @Test
  public void test001_WhenCreateUserShouldHaveFunds() {
    final User aUser = new User();
    assertEquals(10000, aUser.getFunds());
  }
  
  @Test
  public void test002_WhenBuyStockAndUserHasFundsShouldReturnReceipt() throws IOException, InterruptedException, NoFundsException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    assertDoesNotThrow(() -> stockService.buyStock(symbol, quantity, userId));
  }
  
  @Test
  public void test003_WhenBuyStockAndUserHasNoFundsShouldThrowNoFundsException() throws IOException, InterruptedException, NoFundsException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    userService.save(new User(userId, 0));
    assertThrows(NoFundsException.class, () -> stockService.buyStock(symbol, quantity, userId));
  }
  
  @Test
  public void test004_WhenBuyStockAndUserHasJustEnoughFundsShouldReturnReceipt() throws IOException, InterruptedException, NoFundsException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    userService.save(new User(userId, 10));
    fakeApiService.setPrice(10);
    assertDoesNotThrow(() -> stockService.buyStock(symbol, quantity, userId));
  }
  
  @Test
  public void test005_WhenBuyStockAndUserHasJustEnoughFundsPlus1ShouldReturnReceipt() throws IOException, InterruptedException, NoFundsException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    userService.save(new User(userId, 11));
    fakeApiService.setPrice(10);
    assertDoesNotThrow(() -> stockService.buyStock(symbol, quantity, userId));
  }
  
  @Test
  public void test006_WhenBuyStockAndUserHasJustEnoughFundsMinus1ShouldReturnEmptyReceipt() throws IOException, InterruptedException, NoFundsException {
    final String symbol = "AMC";
    final Long userId = 1L;
    final double quantity = 1;
    userService.save(new User(userId, 9));
    fakeApiService.setPrice(10);
    assertThrows(NoFundsException.class, () -> stockService.buyStock(symbol, quantity, userId));
  }
}

