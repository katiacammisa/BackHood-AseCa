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
    public void test001_GivenAValidSymbolShouldReturnStockStatsDto(){
        String symbol = "IBM";
        try {
            stockService.getStockStats(symbol);
        } catch (IOException | InterruptedException e) {
            fail();
        }
    }

    @Test
    public void test002_GivenAnOpenPriceOf200ResponseOpenShouldBe200(){
        double open = 200;
        fakeApiService.setOpen(open);
        try {
            StockStatsDto dto = stockService.getStockStats("IBM");
            assertEquals(open,dto.getOpen());
        } catch (IOException | InterruptedException e) {
            fail();
        }
    }

    @Test
    public void test003_GivenAnOpenPriceOf100ResponseDailyPricesShouldBe100(){
        double open = 100;
        fakeApiService.setOpen(open);
        try {
            StockStatsDto dto = stockService.getStockStats("IBM");
            assertEquals(open,(double)dto.getDailyPrices().get(LocalDate.now()));
        } catch (IOException | InterruptedException e) {
            fail();
        }
    }

    @Test
    public void test004_GivenALowPriceOf50AndMinimumLowOf40ResponseLowPricesShouldBe50And52WeekLowShouldBe40(){
        double low = 50;
        double minimumLow = 40;
        fakeApiService.setLow(low);
        fakeApiService.setMinimumLow(minimumLow);
        try {
            StockStatsDto dto = stockService.getStockStats("IBM");
            assertEquals(low,dto.getLow());
            assertEquals(minimumLow,dto.getWeek52low());
        } catch (IOException | InterruptedException e) {
            fail();
        }
    }

    @Test
    public void test005_GivenAHighPriceOf500AndMaximumHighOf600ResponseHighPricesShouldBe500And52WeekHighShouldBe600(){
        double high = 500;
        double maximumHigh = 600;
        fakeApiService.setHigh(high);
        fakeApiService.setMaximumHigh(maximumHigh);
        try {
            StockStatsDto dto = stockService.getStockStats("IBM");
            assertEquals(high,dto.getHigh());
            assertEquals(maximumHigh,dto.getWeek52high());
        } catch (IOException | InterruptedException e) {
            fail();
        }
    }

    @Test
    public void test006_GivenAVolumeOf14000ResponseVolumeShouldBe14000AndAverageShouldBe2000(){
        int volume = 14000;
        int average = volume/7;
        fakeApiService.setVolume(volume);
        try {
            StockStatsDto dto = stockService.getStockStats("IBM");
            assertEquals(volume,dto.getVolume());
            assertEquals(average,dto.getVolumeAverage());
        } catch (IOException | InterruptedException e) {
            fail();
        }
    }

    @Test
    public void test007_GivenAPriceOf1400ResponsePriceShouldBe1400(){
        int price = 1400;
        fakeApiService.setPrice(price);
        try {
            StockStatsDto dto = stockService.getStockStats("IBM");
            assertEquals(price,dto.getPrice());
        } catch (IOException | InterruptedException e) {
            fail();
        }
    }
}
