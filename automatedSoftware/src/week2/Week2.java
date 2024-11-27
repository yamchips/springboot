package week2;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Week2 {
  public static void main(String[] args) {
    Instant t = Instant.now();
    System.out.println(t.toString());
    Instant s = Instant.MAX;
    System.out.println(s);
    long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(),
        ChronoUnit.SECONDS);
    System.out.println(secondsFromEpoch);
    //Instant.ofEpochMilli(t);
    System.out.printf("%1d. %d (no status heartbeat since %s)%n", 5, 6, 4);
  }
}
