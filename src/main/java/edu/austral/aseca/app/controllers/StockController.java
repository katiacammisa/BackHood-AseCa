package edu.austral.aseca.app.controllers;


import edu.austral.aseca.app.dtos.StockStatsDto;
import edu.austral.aseca.app.models.Receipt;
import edu.austral.aseca.app.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

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

  @GetMapping(path = "/stats/{symbol}")
  public StockStatsDto getStockStats(@PathVariable String symbol) throws IOException, InterruptedException {
    return service.getStockStats(symbol);
  }
  
  @PutMapping(path = "/{userId}/{symbol}/{quantity}")
  public Receipt buy(@PathVariable Long userId, @PathVariable String symbol, @PathVariable Long quantity) {
    try {
      final Optional<Receipt> receipt = service.buyStock(symbol, quantity, userId);
      if (receipt.isPresent()) return receipt.get();
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough funds");
    } catch (IOException | InterruptedException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
