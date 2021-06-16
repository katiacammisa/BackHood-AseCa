package edu.austral.aseca.app;

import edu.austral.aseca.app.dtos.StockStatsDto;
import edu.austral.aseca.app.respository.EmptyReceiptRepository;
import edu.austral.aseca.app.respository.ReceiptRepository;
import edu.austral.aseca.app.services.FakeApiServiceImpl;
import edu.austral.aseca.app.services.StockService;
import edu.austral.aseca.app.services.UserService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockStatsTests {

    private final ReceiptRepository emptyReceiptRepository = new EmptyReceiptRepository();

    public static final String SYMBOL = "TEST_SYMBOL";
    private final FakeApiServiceImpl fakeApiService = new FakeApiServiceImpl();
    private final UserService userService = new UserService();
    private final StockService stockService = new StockService(fakeApiService, userService, emptyReceiptRepository);

    @Test
    public void test001_GivenAnOpenPriceOf200ResponseOpenShouldBe200() throws IOException, InterruptedException {
        double open = 200;
        fakeApiService.setOpen(open);
        StockStatsDto dto = stockService.getStockStats(SYMBOL);
        assertEquals(open, dto.getOpen());
    }

    @Test
    public void test002_GivenAnOpenPriceOf100ResponseDailyPricesShouldBe100() throws IOException, InterruptedException {
        double open = 100;
        fakeApiService.setOpen(open);
        StockStatsDto dto = stockService.getStockStats(SYMBOL);
        assertEquals(open, (double) dto.getDailyPrices().get(0).getPrice());
    }

    @Test
    public void test003_GivenALowPriceOf50AndMinimumLowOf40ResponseLowPricesShouldBe50And52WeekLowShouldBe40() throws IOException, InterruptedException {
        double low = 50;
        double minimumLow = 40;
        fakeApiService.setLow(low);
        fakeApiService.setMinimumLow(minimumLow);
        StockStatsDto dto = stockService.getStockStats(SYMBOL);
        assertEquals(low, dto.getLow());
        assertEquals(minimumLow, dto.getWeek52low());
    }

    @Test
    public void test004_GivenAHighPriceOf500AndMaximumHighOf600ResponseHighPricesShouldBe500And52WeekHighShouldBe600() throws IOException, InterruptedException {
        double high = 500;
        double maximumHigh = 600;
        fakeApiService.setHigh(high);
        fakeApiService.setMaximumHigh(maximumHigh);
        StockStatsDto dto = stockService.getStockStats(SYMBOL);
        assertEquals(high, dto.getHigh());
        assertEquals(maximumHigh, dto.getWeek52high());
    }

    @Test
    public void test005_GivenAVolumeOf14000ResponseVolumeShouldBe14000AndAverageShouldBe2000() throws IOException, InterruptedException {
        int volume = 14000;
        int average = volume / 7;
        fakeApiService.setVolume(volume);
        StockStatsDto dto = stockService.getStockStats(SYMBOL);
        assertEquals(volume, dto.getVolume());
        assertEquals(average, dto.getVolumeAverage());
    }

    @Test
    public void test006_GivenAPriceOf1400ResponsePriceShouldBe1400() throws IOException, InterruptedException {
        int price = 1400;
        fakeApiService.setPrice(price);
        StockStatsDto dto = stockService.getStockStats(SYMBOL);
        assertEquals(price, dto.getPrice());
    }
}
