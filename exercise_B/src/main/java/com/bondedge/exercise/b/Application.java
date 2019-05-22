package com.bondedge.exercise.b;

import com.bondedge.exercise.b.bond.tickers.ReadTickerService;
import com.bondedge.exercise.b.bond.tickers.domain.BondPriceTicker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) throws IOException {

        final ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        final Map<String, BondPriceTicker> bondPriceTickers = ctx.getBean(ReadTickerService.class).readTickers();

        bondPriceTickers.forEach( (ticker, bpt) -> {
            log.info("last price: {}", bpt);
        });

        log.info("number of tickers: {}", bondPriceTickers.size());
        SpringApplication.exit(ctx, () -> 0);

    }

}
