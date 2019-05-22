package com.bondedge.exercise.b;

import com.bondedge.exercise.b.bond.tickers.TickerErrorHandlingSubscriber;
import com.bondedge.exercise.b.bond.tickers.TickerFilePublisher;
import com.bondedge.exercise.b.bond.tickers.TickerPublisher;
import com.bondedge.exercise.b.bond.tickers.TickerSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    ApplicationArguments appArgs;

    @Bean
    TickerSubscriber tickSubscriber() {
        return new TickerErrorHandlingSubscriber();
    }

    @Bean
    TickerPublisher tickPublisher() {
        return new TickerFilePublisher(appArgs.getSourceArgs().length == 0 ? "" : appArgs.getSourceArgs()[0]);
    }

}
