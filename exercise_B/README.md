# Solution

## Assumptions
Every price has a decimal point in it.

## Constraints
Development and testing was done on a Mac running:

Gradle:       5.4.1;
JVM:          1.8.0_144 (Oracle Corporation 25.144-b01);
OS:           Mac OS X 10.14.4 x86_64

No testing has been done on Windows.

## Requirements

#### "Do not assume the entire file can fit in memory"

To address this a reactive solution was developed using Spring's Reactor Library. Lines are read one
by one and pushed by a [reactive stream publisher](https://github.com/sbookert/bondedge_exercises/blob/a448044545f45d512baf6569c08a7ed5b5bb2367/exercise_B/src/main/java/com/bondedge/exercise/b/bond/tickers/TickerFilePublisher.java#L23).
This stream is then [buffered to capture all prices for one CUSIP by the subscriber](https://github.com/sbookert/bondedge_exercises/blob/a448044545f45d512baf6569c08a7ed5b5bb2367/exercise_B/src/main/java/com/bondedge/exercise/b/bond/tickers/TickerErrorHandlingSubscriber.java#L21),
and then the next batch of CUSIP and prices is loaded.

#### "Java program that will print the closing (or latest) price for each CUSIP"

See the output and start command below.

```
(vpopescu@zpopescu.local) ~/_vlad/dev-almeu/repo/bondedge_exercises/exercise_B
$ ./gradlew bootRun -Pfile=./data/tickers.txt

> Task :bootRun

 :: Spring Boot ::        (v2.1.5.RELEASE)

2019-05-22 10:21:21.591  INFO 37213 --- [           main] com.bondedge.exercise.b.Application      : Starting Application on zpopescu.local with PID 37213 (/Users/vpopescu/_vlad/dev-almeu/repo/bondedge_exercises/exercise_B/exercise_B/build/classes/java/main started by vpopescu in /Users/vpopescu/_vlad/dev-almeu/repo/bondedge_exercises/exercise_B/exercise_B)
2019-05-22 10:21:21.594  INFO 37213 --- [           main] com.bondedge.exercise.b.Application      : No active profile set, falling back to default profiles: default
2019-05-22 10:21:28.148  INFO 37213 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port(s): 8080
2019-05-22 10:21:28.154  INFO 37213 --- [           main] com.bondedge.exercise.b.Application      : Started Application in 22.017 seconds (JVM running for 22.419)
2019-05-22 10:21:28.155  INFO 37213 --- [           main] c.b.e.b.b.tickers.TickerFilePublisher    : ./data/tickers.txt
2019-05-22 10:21:28.169  INFO 37213 --- [           main] com.bondedge.exercise.b.Application      : last price: BondPriceTicker(ticker=15189T107, price=0.1)
2019-05-22 10:21:28.171  INFO 37213 --- [           main] com.bondedge.exercise.b.Application      : last price: BondPriceTicker(ticker=0A206R1B, price=2.55)
2019-05-22 10:21:28.171  INFO 37213 --- [           main] com.bondedge.exercise.b.Application      : last price: BondPriceTicker(ticker=00206R103, price=36.69)
2019-05-22 10:21:28.171  INFO 37213 --- [           main] com.bondedge.exercise.b.Application      : number of tickers: 3

```

#### Tests

The application is configured to use Spring Boot's in memory integration test libraries. It also
makes use of Spring's dependency injection to [inject a publisher](https://github.com/sbookert/bondedge_exercises/blob/d490b020ef70ba38a3ecc74b89c20d32736a1213/exercise_B/src/test/java/com/bondedge/exercise/b/bond/tickers/test/utils/ConfigOverridesForTest.java#L14)
which pulls data from code for
testing as opposed to the publisher which pulls data from the file given in the command line
program argument.

```
(vpopescu@zpopescu.local) ~/_vlad/dev-almeu/repo/bondedge_exercises/exercise_B
$ ./gradlew test

  BUILD SUCCESSFUL in 0s
  4 actionable tasks: 4 up-to-date
```