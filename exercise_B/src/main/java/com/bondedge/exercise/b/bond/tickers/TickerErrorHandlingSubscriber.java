package com.bondedge.exercise.b.bond.tickers;

import com.bondedge.exercise.b.bond.tickers.domain.BondPriceTicker;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class TickerErrorHandlingSubscriber implements TickerSubscriber {

    private Map<String, BondPriceTicker> prices = new HashMap<>();


    @Override
    public Map<String, BondPriceTicker> getPrices(Flux<String> ticks) {

        ticks.bufferUntil( s -> { return !s.contains("."); }, true)
                .doOnEach(list -> {

                    log.debug("list: {}", list);

                    BondPriceTicker bp = null;

                    //skip records that don't have prices
                    if(list.get() != null && list.get().size() > 1) {
                        bp = BondPriceTicker.make(list.get());

                        prices.put(bp.getCusip(), bp);
                    }

                })
                .subscribe(null, (throwable) -> log.error("could not process record", throwable));

        return prices;
    }

}
