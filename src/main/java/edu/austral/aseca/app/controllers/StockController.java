package edu.austral.aseca.app.controllers;


import edu.austral.aseca.app.dtos.StockStatsDto;
import edu.austral.aseca.app.exceptions.NoFundsException;
import edu.austral.aseca.app.models.Receipt;
import edu.austral.aseca.app.models.Stock;
import edu.austral.aseca.app.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class StockController {
  
  final StockService service;
  
  public StockController(StockService service) {
    this.service = service;
  }
  
  @GetMapping(path = "/{symbol}")
  public String getCurrentPrice(@PathVariable String symbol) throws IOException, InterruptedException {
    return service.getCurrentPrice(symbol);
  }

  @GetMapping(path = "/list")
  public List<Stock> getStocks(@RequestParam(defaultValue = "") String keyword) throws IOException, InterruptedException {
    return service.getStocks(keyword);
  }

  @GetMapping(path = "/stats/{symbol}")
  public StockStatsDto getStockStats(@PathVariable String symbol) throws IOException, InterruptedException {
    return service.getStockStats(symbol);
  }

  @GetMapping(path = "/qualifications")
  public String getQualifications() {
    return service.getQualifications();
  }

  @PutMapping(path = "/{userId}/{symbol}/{quantity}")
  public Receipt buy(@PathVariable Long userId, @PathVariable String symbol, @PathVariable Long quantity) {
    try {
      return service.buyStock(symbol, quantity, userId);
    } catch (IOException | InterruptedException | NoFundsException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough funds");
    }
  }
}
