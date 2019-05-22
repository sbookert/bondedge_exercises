package com.bondedge.exercise.b.bond.tickers.test.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.stream.Stream;

public class TestData {

    public static final String CUSIP_Charles_Schwab = "15189T107";
    public static final String CUSIP_ABIO = "00762W107";
    public static final String CUSIP_ABCO = "15189T107";

    public static final String[] tickerStream = {TestData.CUSIP_Charles_Schwab,
            "95.752",
            "112.2",
            "90.",
            TestData.CUSIP_ABCO,
            TestData.CUSIP_ABIO,
            "99.8",
            TestData.CUSIP_Charles_Schwab,
            "800.00"};

    public static void main(String[] args) throws IOException {
        int times = 1;
        while (times-- > 0){
            makeData();
        }
    }

    private static void makeData() throws IOException {
        final Stream<String> lines = Files.lines(Paths.get("./data/tickers_large.txt"));

        StringBuffer sb = new StringBuffer();

        lines.forEach( l -> {
            try {
                sb.append(l);
                sb.append("\n");
                int randInt = new Random().nextInt(10);
                new Random().doubles(randInt).forEach( d ->
                        sb.append(d*randInt*10).append("\n")
                );
                Files.write(Paths.get("./data/tickers_largest.txt"), sb.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

                sb.setLength(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
