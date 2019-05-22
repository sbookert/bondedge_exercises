package com.bondedge.exercise.b.bond.tickers;

import reactor.core.publisher.Flux;

import java.io.IOException;

public interface TickerPublisher {

    /**
     * Publishes bond prices.
     * @return a stream of ticks with CUSIP followed by prices, where the last price is the most recent price
     */
    Flux<String> getTicks() throws IOException;

}
