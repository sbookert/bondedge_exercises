package com.bondedge.exercise.b.bond.tickers.test;

import com.bondedge.exercise.b.bond.tickers.domain.BondPriceTicker;
import com.bondedge.exercise.b.bond.tickers.test.utils.TestData;
import com.bondedge.exercise.b.bond.tickers.test.utils.ConfigOverridesForTest;
import com.bondedge.exercise.b.bond.tickers.ReadTickerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ConfigOverridesForTest.class)
public class TestBondPriceTicker {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TestBondPriceTicker.class);

	@Autowired
	ReadTickerService readTickerService;

	@Test
	public void test_reading_tickers() throws Exception {

		//GIVEN this data
		log.info("test data: {}", Arrays.asList( TestData.tickerStream) );

		//WHEN
		final Map<String, BondPriceTicker> bondPrices = readTickerService.readTickers();

		bondPrices.forEach( (s, bp) -> {log.info("{}", bp);} );

		//THEN
        Assert.assertEquals("check that bond CUSIP is present and has the last price", true, bondPrices.values().stream().anyMatch(bondPrice -> bondPrice.getCusip().equals(TestData.CUSIP_Charles_Schwab) && bondPrice.getPrice().equals(new BigDecimal("800.00"))));
        Assert.assertEquals("check that bond CUSIP is present and has the last price", true, bondPrices.values().stream().anyMatch(bondPrice -> bondPrice.getCusip().equals(TestData.CUSIP_ABIO) && bondPrice.getPrice().equals(new BigDecimal("99.8"))));

        Assert.assertEquals("check size", 2, bondPrices.size());

	}

	@Test
	public void test_making_bond_price_objects() {

		//GIVEN
		List<String> tickerData = Arrays.asList( TestData.CUSIP_Charles_Schwab, "95.752" );

		//WHEN
		BondPriceTicker bp = BondPriceTicker.make(tickerData);

		//THEN
		Assert.assertEquals("bond prices objects must match",
				new BondPriceTicker(
						TestData.CUSIP_Charles_Schwab,
						new BigDecimal( tickerData.get(1) ) ),
				bp );


		//GIVEN
		tickerData = Arrays.asList( TestData.CUSIP_Charles_Schwab, "95.752", "112.2" );

		//WHEN
		bp = BondPriceTicker.make(tickerData);

		//THEN
		Assert.assertEquals("bond prices objects must match",
				new BondPriceTicker(
						TestData.CUSIP_Charles_Schwab,
						new BigDecimal( tickerData.get(2) ) ),
				bp);


		//GIVEN
		tickerData = Arrays.asList( TestData.CUSIP_Charles_Schwab, "95.752", "112.2", "90.", "99.8" );

		//WHEN
		bp = BondPriceTicker.make(tickerData);

		//THEN
		Assert.assertEquals("bond prices objects must match",
				new BondPriceTicker(
						TestData.CUSIP_Charles_Schwab,
						new BigDecimal( tickerData.get(4) ) ),
				bp);

	}

}
