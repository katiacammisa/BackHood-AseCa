package edu.austral.aseca.app;

import edu.austral.aseca.app.dtos.StockStatsDto;
import edu.austral.aseca.app.services.FakeApiServiceImpl;
import edu.austral.aseca.app.services.StockService;
import edu.austral.aseca.app.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StockStatsTests {

    private final FakeApiServiceImpl fakeApiService = new FakeApiServiceImpl();
    private final UserService userService = new UserService();
    private final StockService stockService = new StockService(fakeApiService, userService);

    @Test
    public void test001_GivenAnOpenPriceOf200ResponseOpenShouldBe200() throws IOException, InterruptedException {
        double open = 200;
        fakeApiService.setOpen(open);
        StockStatsDto dto = stockService.getStockStats("IBM");
        assertEquals(open, dto.getOpen());
    }

    @Test
    public void test002_GivenAnOpenPriceOf100ResponseDailyPricesShouldBe100() throws IOException, InterruptedException {
        double open = 100;
        fakeApiService.setOpen(open);
        StockStatsDto dto = stockService.getStockStats("IBM");
        assertEquals(open, (double) dto.getDailyPrices().get(0).getPrice());
    }

    @Test
    public void test003_GivenALowPriceOf50AndMinimumLowOf40ResponseLowPricesShouldBe50And52WeekLowShouldBe40() throws IOException, InterruptedException {
        double low = 50;
        double minimumLow = 40;
        fakeApiService.setLow(low);
        fakeApiService.setMinimumLow(minimumLow);
        StockStatsDto dto = stockService.getStockStats("IBM");
        assertEquals(low, dto.getLow());
        assertEquals(minimumLow, dto.getWeek52low());
    }

    @Test
    public void test004_GivenAHighPriceOf500AndMaximumHighOf600ResponseHighPricesShouldBe500And52WeekHighShouldBe600() throws IOException, InterruptedException {
        double high = 500;
        double maximumHigh = 600;
        fakeApiService.setHigh(high);
        fakeApiService.setMaximumHigh(maximumHigh);
        StockStatsDto dto = stockService.getStockStats("IBM");
        assertEquals(high, dto.getHigh());
        assertEquals(maximumHigh, dto.getWeek52high());
    }

    @Test
    public void test005_GivenAVolumeOf14000ResponseVolumeShouldBe14000AndAverageShouldBe2000() throws IOException, InterruptedException {
        int volume = 14000;
        int average = volume / 7;
        fakeApiService.setVolume(volume);
        StockStatsDto dto = stockService.getStockStats("IBM");
        assertEquals(volume, dto.getVolume());
        assertEquals(average, dto.getVolumeAverage());
    }

    @Test
    public void test006_GivenAPriceOf1400ResponsePriceShouldBe1400() throws IOException, InterruptedException {
        int price = 1400;
        fakeApiService.setPrice(price);
        StockStatsDto dto = stockService.getStockStats("IBM");
        assertEquals(price, dto.getPrice());
    }
}
