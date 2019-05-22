package com.bondedge.exercise.b.bond.tickers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Slf4j
public class TickerFilePublisher implements TickerPublisher {

    @NonNull private String pathToFile;

    @Override
    public Flux<String> getTicks() throws IOException {

        log.info(pathToFile);

        return Flux.fromStream( Files.lines(Paths.get(pathToFile)) );

    }

}
