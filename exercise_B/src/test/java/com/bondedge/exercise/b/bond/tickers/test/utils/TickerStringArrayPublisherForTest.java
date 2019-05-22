package com.bondedge.exercise.b.bond.tickers.test.utils;

import com.bondedge.exercise.b.bond.tickers.TickerPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
public class TickerStringArrayPublisherForTest implements TickerPublisher {

    @Override
    public Flux<String> getTicks() throws IOException {

        log.info("loading from ");

        return Flux.fromStream( Stream.of( TestData.tickerStream ) );

    }

}
