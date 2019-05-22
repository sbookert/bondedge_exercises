package com.bondedge.exercise.b.bond.tickers;

import com.bondedge.exercise.b.bond.tickers.domain.BondPriceTicker;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;

public interface TickerSubscriber {

    Map<String, BondPriceTicker> getPrices(Flux<String> ticks);

}
