package com.bondedge.exercise.b.bond.tickers;

import com.bondedge.exercise.b.bond.tickers.domain.BondPriceTicker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class ReadTickerService {

    @Autowired private TickerPublisher tickerPublisher;
    @Autowired private TickerSubscriber tickerSubscriber;

    public Map<String, BondPriceTicker> readTickers() throws IOException {

        final Map<String, BondPriceTicker> prices = tickerSubscriber.getPrices(tickerPublisher.getTicks());

        return prices;

    }
}
