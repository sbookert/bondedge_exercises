package com.bondedge.exercise.b.bond.tickers.test.utils;

import com.bondedge.exercise.b.bond.tickers.TickerPublisher;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("test")
public class ConfigOverridesForTest {

    @Bean
    TickerPublisher tickPublisher() {
        return new TickerStringArrayPublisherForTest();
    }

}
