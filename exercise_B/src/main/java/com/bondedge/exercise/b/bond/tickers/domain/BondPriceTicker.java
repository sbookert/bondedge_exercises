package com.bondedge.exercise.b.bond.tickers.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BondPriceTicker {

    private String cusip;
    private BigDecimal price;

    /**
     * Makes a an instance.
     * @param priceTicks where the first element is the CUSIP alphanumeric symbol and the last element is the final price
     * @return a new instance
     */
    public static BondPriceTicker make(List<String> priceTicks) {

        BondPriceTicker bp = new BondPriceTicker();

        try {

            bp.cusip = priceTicks.get(0);
            bp.price = new BigDecimal( priceTicks.get(priceTicks.size()-1) );

        } catch (Throwable t) {
            throw new RuntimeException("error on priceTick: " + priceTicks, t);
        }

        return bp;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BondPriceTicker)) return false;
        BondPriceTicker bondPrice = (BondPriceTicker) o;
        return getCusip().equals(bondPrice.getCusip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCusip());
    }
}
