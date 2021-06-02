package edu.austral.aseca.app.services;

import java.io.IOException;

public interface ApiService {

  String getCurrentPrice(String symbol) throws IOException, InterruptedException;

  String getStocks(String keyword) throws IOException, InterruptedException;

  String getWeeklyStats(String symbol) throws IOException, InterruptedException;

  String getCurrentStats(String symbol) throws IOException, InterruptedException;

  String getDailyStats(String symbol) throws IOException, InterruptedException;

  }
